package com.illmeyer.polygraph.plumbing;

import java.net.URL;

import org.xml.sax.InputSource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import com.illmeyer.polygraph.core.spi.TemplateDataProvider;

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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object getTemplateData() {
		return xmlModel;
	}

	@Override
	public void destroy() {
	}

}
