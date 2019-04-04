package com.dlstone.mybatis.builder.xml;

import com.dlstone.mybatis.builder.BaseBuilder;
import com.dlstone.mybatis.builder.BuilderException;
import com.dlstone.mybatis.mapping.Environment;
import com.dlstone.mybatis.parsing.XNode;
import com.dlstone.mybatis.parsing.XPathParser;
import com.dlstone.mybatis.session.Configuration;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class XMLConfigBuilder extends BaseBuilder {
    private XPathParser parser;
    private String environment;

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
        try {
            this.environmentsElement(root.evalNode("environments"));
            this.mapperElement(root.evalNode("mappers"));
        } catch (Exception e) {
            throw new BuilderException("parseConfiguration error");
        }
    }

    private void mapperElement(XNode parent) throws Exception {
        if (parent != null) {
            Iterator<XNode> iterator = parent.getChildren().iterator();
            while (iterator.hasNext()) {
                XNode child = iterator.next();
                String resource = child.getStringAttribute("resource");
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
                XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, this.configuration, resource);
                mapperParser.parse();
            }
        }
    }

    private void environmentsElement(XNode context) throws Exception {
        if (context != null) {
            this.environment = context.getStringAttribute("default");

            Iterator<XNode> iterator = context.getChildren().iterator();
            while (iterator.hasNext()) {
                XNode children = iterator.next();
                String id = children.getStringAttribute("id");
                if (this.isSpecifiedEnvironment(id)) {
                    DataSource dataSource = this.dataSourceElement(children.evalNode("dataSource"));
                    this.configuration.setEnvironment(Environment.builder()
                            .id(id).dataSource(dataSource).build());
                }
            }

        }
    }

    private DataSource dataSourceElement(XNode context) throws Exception {
        if (context != null) {
            Properties properties = context.getChildrenAsProperties();
            return BasicDataSourceFactory.createDataSource(properties);
        } else {
            throw new BuilderException("Environment declaration requires a DataSourceFactory.");
        }

    }

    private boolean isSpecifiedEnvironment(String id) {
        if (this.environment == null) {
            throw new BuilderException("no environment specified");
        } else if (id == null) {
            throw new BuilderException("Environment requires an id attribute.");
        } else {
            return this.environment.equals(id);
        }

    }
}
