package com.dlstone.mybatis;

import com.dlstone.mybatis.builder.xml.XMLConfigBuilder;
import org.junit.Test;

import java.io.InputStream;

public class XMLConfigBuilderTest {

    @Test
    public void should_parse_xml_happy_path() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream);
        xmlConfigBuilder.parse();
    }
}
