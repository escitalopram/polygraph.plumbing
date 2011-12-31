package com.illmeyer.polygraph.plumbing;

import java.io.File;

import org.junit.Test;

// TODO: Find a better way to test this platform independently
public class DiskCacheDispatcherTest {
	@Test
	public void testDispatcher() {
		DiskCacheDispatcher dct = new DiskCacheDispatcher();
		dct.setCacheDirectory(new File("/tmp/loldir/"));
		dct.initialize();
		dct.dispatchMessage("Dies ist ein Test");
		dct.dispatchMessage("Das auch");
		dct.destroy();
	}
}
