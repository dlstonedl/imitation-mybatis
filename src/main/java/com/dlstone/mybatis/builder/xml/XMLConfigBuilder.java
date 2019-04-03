package com.dlstone.mybatis.builder.xml;

import com.dlstone.mybatis.builder.BaseBuilder;
import com.dlstone.mybatis.parsing.XNode;
import com.dlstone.mybatis.parsing.XPathParser;
import com.dlstone.mybatis.session.Configuration;

import java.io.InputStream;

public class XMLConfigBuilder extends BaseBuilder {
    private XPathParser parser;

    public XMLConfigBuilder(InputStream inputStream) {
        this(new XPathParser(inputStream));
    }

    private XMLConfigBuilder(XPathParser parser) {
        super(new Configuration());
        this.parser = parser;
    }

    public Configuration parse() {
        this.parseConfiguration(this.parser.evalNode("/configuration"));
        return this.configuration;
    }

    private void parseConfiguration(XNode root) {


    }
}
