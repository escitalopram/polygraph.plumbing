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
		String filename=System.currentTimeMillis()+"-"+message.hashCode();
		String addition="";
		File targetFile;
		do {
			targetFile = new File(cacheDirectory,filename+addition);
			addition="-"+r.nextInt();
		} while (targetFile.exists());
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