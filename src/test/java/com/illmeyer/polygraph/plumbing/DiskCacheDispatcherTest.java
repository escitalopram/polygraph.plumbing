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
