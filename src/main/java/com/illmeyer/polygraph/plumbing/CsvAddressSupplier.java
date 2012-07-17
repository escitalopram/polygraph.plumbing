/*
This file is part of the Polygraph bulk messaging framework
Copyright (C) 2012 Wolfgang Illmeyer

The Polygraph framework is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.illmeyer.polygraph.plumbing;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;

import com.illmeyer.polygraph.core.data.Address;
import com.illmeyer.polygraph.core.spi.AddressSupplier;

public class CsvAddressSupplier implements AddressSupplier {
	
	@Getter @Setter private URL input;
	@Getter @Setter private boolean firstRowTitles=true;
	@Getter @Setter private char columnSeparator=';';
	@Getter @Setter private char quoteCharacter='"';
	@Getter @Setter private String inputEncoding="UTF-8";
	@Getter @Setter private List<String> overrideColumnNames = new ArrayList<String>();
	@Getter @Setter private Set<String> addressColumns = new HashSet<String>();

	private CsvMapReader reader;
	private String[] columnNames;
	private Address currentLine;
	private boolean lineRead=true;
	private boolean eof=false;

	
	private boolean readNextLine() {
		try {
			Map<String,String> entries = reader.read(columnNames);
			if (entries==null) {
				eof=true;
				return false;
			}
			currentLine=new Address();
			for (Entry<String, String> e : entries.entrySet())
				if (addressColumns.contains(e.getKey()))
					currentLine.getAddrs().put(e.getKey(), e.getValue());
				else
					currentLine.getFields().put(e.getKey(), e.getValue());
			lineRead=false;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
	@Override
	public boolean hasMoreElements() {
		if (eof) return false;
		if (!lineRead) return true;
		return readNextLine();
	}

	@Override
	public Address nextElement() {
		if (lineRead) readNextLine();
		lineRead=true;
		return currentLine;
	}

	@Override
	public void initialize() {
		lineRead=true;
		eof=false;
		CsvPreference pref = new CsvPreference(quoteCharacter, columnSeparator, "\n");
		try {
			reader=new CsvMapReader(new InputStreamReader(input.openStream(),inputEncoding), pref);
			if(firstRowTitles) {
				columnNames=reader.getCSVHeader(false);
				for (int i=0;i<overrideColumnNames.size()&&i<columnNames.length;++i) columnNames[i]=overrideColumnNames.get(i);
			} else {
				columnNames=overrideColumnNames.toArray(new String[]{});
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void destroy() {
		try {
			reader.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void reset() {
		destroy();
		initialize();
	}
}