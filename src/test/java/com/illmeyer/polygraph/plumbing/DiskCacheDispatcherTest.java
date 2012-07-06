package com.illmeyer.polygraph.plumbing;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import com.illmeyer.polygraph.core.data.Message;
import com.illmeyer.polygraph.core.data.MessagePart;

// TODO: Find a better way to test this platform independently
public class DiskCacheDispatcherTest {
	@Test
	public void testDispatcher() {
		DiskCacheDispatcher dct = new DiskCacheDispatcher();
		dct.setCacheDirectory(new File(System.getProperty("java.io.tmpdir")));
		dct.initialize();
		dct.dispatchMessage(getExampleMessage());
		dct.destroy();
	}
	
	private Message getExampleMessage() {
		Message m = new Message("testmessage");
		m.getProperties().put("test-prop", "test-value");
		MessagePart mp = new MessagePart();
		mp.getProperties().put("test-msg-prop", "test-value2");
		mp.setStringMessage("Dies ist ein Test");
		m.getParts().put("main", mp);
		return m;
	}
}
