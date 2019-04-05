package com.dlstone.mybatis.builder;

import com.dlstone.mybatis.session.Configuration;

public class MapperBuilderAssistant extends BaseBuilder {
    private String currentNamespace;
    private final String resource;

    public MapperBuilderAssistant(Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
    }

    public void setCurrentNamespace(String currentNamespace) {
        this.currentNamespace = currentNamespace;
    }
}
