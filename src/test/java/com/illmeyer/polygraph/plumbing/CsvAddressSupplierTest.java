package com.illmeyer.polygraph.plumbing;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.illmeyer.polygraph.core.Address;

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
