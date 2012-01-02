package com.illmeyer.polygraph.plumbing;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

import com.illmeyer.polygraph.core.MessageDispatcher;

public class DiskCacheDispatcher implements MessageDispatcher {

	@Getter @Setter private File cacheDirectory; 

	private Random r = new Random();
	
	@Override
	public void dispatchMessage(String message) {
		StringBuilder fnString = new StringBuilder();
		fnString.append(Long.toHexString(System.currentTimeMillis()))
			.append('-')
			.append(Integer.toHexString(message.hashCode()))
			.append('-')
			.append(Long.toHexString(r.nextLong()));
		String filename=fnString.toString();
		String addition="";
		File targetFile = new File(cacheDirectory,filename);
		while (targetFile.exists()) {
			addition=Integer.toHexString(r.nextInt());
			targetFile = new File(cacheDirectory,filename+addition);
		} 
		try {
			PrintWriter pw;
			pw = new PrintWriter(targetFile, "UTF-8");
			pw.print(message);
			pw.close();
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