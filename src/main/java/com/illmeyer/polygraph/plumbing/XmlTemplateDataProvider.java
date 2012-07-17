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

import java.net.URL;

import org.xml.sax.InputSource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import com.illmeyer.polygraph.core.spi.TemplateDataProvider;

import freemarker.ext.dom.NodeModel;

@Data
public class XmlTemplateDataProvider implements TemplateDataProvider{

	private URL xmlInput;
	@Setter(AccessLevel.PRIVATE)
	private NodeModel xmlModel;
	
	@Override
	public void initialize() {
		try {
			InputSource ins = new InputSource(xmlInput.openStream());
			xmlModel = NodeModel.parse(ins);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object getTemplateData() {
		return xmlModel;
	}

	@Override
	public void destroy() {
	}

}
