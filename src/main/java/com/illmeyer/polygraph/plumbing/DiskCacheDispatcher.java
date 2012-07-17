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

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

import com.illmeyer.polygraph.core.data.Message;
import com.illmeyer.polygraph.core.spi.MessageDispatcher;

public class DiskCacheDispatcher implements MessageDispatcher {

	@Getter @Setter private File cacheDirectory; 

	private Random r = new Random();
	
	@Override
	public void dispatchMessage(Message message) {
		StringBuilder fnString = new StringBuilder();
		fnString.append(Long.toHexString(System.currentTimeMillis()))
			.append('-')
			.append(Integer.toHexString(message.hashCode()))
			.append('-')
			.append(Long.toHexString(r.nextLong()));
		String filename=fnString.toString();
		String addition="";
		File targetFile = new File(cacheDirectory,filename);
		// TODO check for race conditions
		while (targetFile.exists()) {
			addition=Integer.toHexString(r.nextInt());
			targetFile = new File(cacheDirectory,filename+addition);
		} 
		try {
			ObjectOutputStream ow;
			ow = new ObjectOutputStream(new FileOutputStream(targetFile));
			ow.writeObject(message);
			ow.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize() {
	}

	@Override
	public void destroy() {
	}
}