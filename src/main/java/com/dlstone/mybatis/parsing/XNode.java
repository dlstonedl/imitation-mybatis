package com.dlstone.mybatis.parsing;

import org.w3c.dom.CharacterData;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Properties;

public class XNode {
    private final Node node;
    private final String name;
    private final String body;
    private Properties attributes;
    private final XPathParser xPathParser;

    public XNode(XPathParser xPathParser, Node node) {
        this.node = node;
        this.name = node.getNodeName();
        this.body = this.parseBody(node);
        this.attributes = this.parseAttributes(node);
        this.xPathParser = xPathParser;
    }

    private String parseBody(Node node) {
        String data = this.getBodyData(node);
        if (data == null) {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                data = this.getBodyData(child);
                if (data != null) {
                    break;
                }
            }
        }
        return data;
    }

    private String getBodyData(Node child) {
        if (child.getNodeType() != 4 && child.getNodeType() != 3) {
            return null;
        } else {
            String data = ((CharacterData) child).getData();
            data = PropertyParser.parse(data);
            return data;
        }
    }

    private Properties parseAttributes(Node node) {
        Properties attributes = new Properties();
        NamedNodeMap attributesNode = node.getAttributes();
        if (attributesNode != null) {
            for (int i = 0; i < attributesNode.getLength(); i++) {
                Node attribute = attributesNode.item(i);
                String value = PropertyParser.parse(attribute.getNodeValue());
                attributes.put(attribute.getNodeName(), value);
            }
        }
        return attributes;
    }


}
