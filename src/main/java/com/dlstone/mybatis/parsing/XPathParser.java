package com.dlstone.mybatis.parsing;

import org.w3c.dom.Document;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public class XPathParser {
    private final Document document;
    private XPath xPath;

    public XPathParser(InputStream inputStream) {
        this.document = createDocument(new InputSource(inputStream));
        this.xPath = XPathFactory.newInstance().newXPath();
    }

    private Document createDocument(InputSource inputSource) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputSource);
        } catch (Exception e) {
            throw new RuntimeException("createDocument");
        }
    }
}
