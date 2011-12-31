package com.illmeyer.polygraph.plumbing;

import java.io.File;

import lombok.Data;

import com.illmeyer.polygraph.core.MessageDispatcher;

@Data

public class DiskCacheDispatcher implements MessageDispatcher {

	private File cacheDirectory; 
	
	@Override
	public void dispatchMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
