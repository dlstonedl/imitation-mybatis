package com.dlstone.mybatis.builder.xml;

import com.dlstone.mybatis.builder.BaseBuilder;
import com.dlstone.mybatis.builder.MapperBuilderAssistant;
import com.dlstone.mybatis.parsing.XNode;
import com.dlstone.mybatis.parsing.XPathParser;
import com.dlstone.mybatis.session.Configuration;

import java.io.InputStream;

public class XMLMapperBuilder extends BaseBuilder {
    private final XPathParser xPathParser;
    private final MapperBuilderAssistant builderAssistant;
    private final String resource;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
        this.xPathParser = new XPathParser(inputStream);
        this.builderAssistant = new MapperBuilderAssistant(configuration, resource);
    }

    public void parse() {
        if (this.configuration.isResourceLoaded(this.resource)) {
            this.configurationElement(this.xPathParser.evalNode("/mapper"));
        }
    }

    private void configurationElement(XNode context) {
        String namespace = context.getStringAttribute("namespace");
        if (namespace != null && !namespace.equals("")) {
            this.builderAssistant.setCurrentNamespace(namespace);
            this.buildStatementFromContext(context.evalNode("select"));
        }
    }

    private void buildStatementFromContext(XNode context) {
        XMLStatementBuilder statementParser = new XMLStatementBuilder(this.configuration, this.builderAssistant, context);
        statementParser.parseStatementNode();
    }
}
