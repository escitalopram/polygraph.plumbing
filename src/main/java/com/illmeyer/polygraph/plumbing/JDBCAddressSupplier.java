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

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

import com.illmeyer.polygraph.core.data.Address;
import com.illmeyer.polygraph.core.spi.AddressSupplier;

@Data

public class JDBCAddressSupplier implements AddressSupplier {

	private String connectionString;
	private String query;
	private String driver;
	private List<Object> queryParameters = new ArrayList<Object>();
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
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
