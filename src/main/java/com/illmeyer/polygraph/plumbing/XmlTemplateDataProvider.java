package com.illmeyer.polygraph.plumbing;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import com.illmeyer.polygraph.core.TemplateDataProvider;

import freemarker.ext.dom.NodeModel;

@Data
public class XmlTemplateDataProvider implements TemplateDataProvider{

	private URL xmlInput;
	@Setter(AccessLevel.PRIVATE)
	private NodeModel xmlModel;
	
	@Override
	public void initialize() {
		try {
			InputSource ins = new InputSource(xmlInput.openStream());
			xmlModel = NodeModel.parse(ins);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object getTemplateData() {
		return xmlModel;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
