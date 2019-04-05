package com.dlstone.mybatis.mapping;

import com.dlstone.mybatis.session.Configuration;

public class ParameterMapping {
    private Configuration configuration;
    private String property;
    private Class<?> javaType = Object.class;

    public ParameterMapping(Configuration configuration, String property, Class<?> javaType) {
        this.configuration = configuration;
        this.property = property;
        this.javaType = javaType;
    }
}
