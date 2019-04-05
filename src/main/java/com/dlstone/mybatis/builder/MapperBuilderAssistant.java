package com.dlstone.mybatis.builder;

import com.dlstone.mybatis.mapping.SqlCommandType;
import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.mapping.StatementType;
import com.dlstone.mybatis.scripting.LanguageDriver;
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

    public String applyCurrentNamespace(String base, boolean isReference) {
        if(base == null) {
            return null;
        } else {
            if (isReference) {
                if (base.contains(".")) {
                    return base;
                }
            } else {
                if (base.startsWith(this.currentNamespace + ".")) {
                    return base;
                }

                if (base.contains(".")) {
                    throw new BuilderException("Dots are not allowed in element names, please remove it from " + base);
                }
            }
        }
        return this.currentNamespace + "." + base;
    }

    public void addMappedStatement(String id, SqlSource sqlSource, StatementType statementType, SqlCommandType sqlCommandType, Class<?> resultTypeClass, LanguageDriver langDriver) {

    }
}
