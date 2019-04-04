package com.dlstone.mybatis.builder.xml;

import com.dlstone.mybatis.builder.BaseBuilder;
import com.dlstone.mybatis.parsing.XPathParser;
import com.dlstone.mybatis.session.Configuration;

import java.io.InputStream;

public class XMLMapperBuilder extends BaseBuilder {
    private final XPathParser xPathParser;
    private final String resource;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
        this.xPathParser = new XPathParser(inputStream);
    }

    public void parse() {

    }
}
