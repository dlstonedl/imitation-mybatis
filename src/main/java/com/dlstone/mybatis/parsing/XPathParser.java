package com.dlstone.mybatis.parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.*;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public class XPathParser {
    private final Document document;
    private XPath xPath;

    public XPathParser(InputStream inputStream) {
        this.document = createDocument(new InputSource(inputStream));
        this.xPath = XPathFactory.newInstance().newXPath();
    }

    public XNode evalNode(String expression) {
        return this.evalNode(this.document, expression);
    }

    private XNode evalNode(Object root, String expression) {
        Node node = (Node) this.evaluate(expression, root, XPathConstants.NODE);
        return node == null ? null : new XNode(this, node);
    }

    private Object evaluate(String expression, Object root, QName returnType) {
        try {
            return this.xPath.evaluate(expression, root, returnType);
        } catch (XPathExpressionException e) {
            throw new RuntimeException();
        }
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
