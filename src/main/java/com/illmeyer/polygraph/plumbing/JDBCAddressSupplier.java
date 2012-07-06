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
