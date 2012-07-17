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

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.illmeyer.polygraph.core.data.Address;

// TODO: remove wackyness of relative, platform dependent URL
@Ignore
public class CsvAddressSupplierTest {
	@Test
	public void testCsvImport() throws MalformedURLException {
		CsvAddressSupplier sup = new CsvAddressSupplier();
		sup.getAddressColumns().add("email");
		sup.setFirstRowTitles(true);
		sup.setColumnSeparator('\t');
		sup.setInput(new URL("file:src/test/resources/test.csv"));
		sup.initialize();
		while (sup.hasMoreElements()) {
			Address a = sup.nextElement();
			Assert.assertEquals("Example", a.getFields().get("lastname"));
			Assert.assertTrue(a.getAddrs().containsKey("email"));
			Assert.assertTrue(a.getFields().containsKey("firstname"));
		}
		sup.destroy();
	}
}
