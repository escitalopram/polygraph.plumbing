package com.illmeyer.polygraph.plumbing;

import lombok.Data;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import com.illmeyer.polygraph.core.Address;
import com.illmeyer.polygraph.core.AddressSupplier;

@Data

public class CsvAddressSupplier implements AddressSupplier {
	
	private URL input;
	private boolean firstRowTitles=true;
	private char columnSeparator=';';
	private char quoteCharacter='"';
	private boolean overrideColumnNames;
	private List<String> columnNames = new ArrayList<String>();
	private List<String> addressColumns = new ArrayList<String>();

	@Override
	public boolean hasMoreElements() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address nextElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
