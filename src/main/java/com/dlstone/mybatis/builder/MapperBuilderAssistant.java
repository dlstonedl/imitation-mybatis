package com.dlstone.mybatis.builder;

import com.dlstone.mybatis.mapping.*;
import com.dlstone.mybatis.session.Configuration;
import lombok.Getter;


public class MapperBuilderAssistant extends BaseBuilder {
    @Getter
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

    public MappedStatement addMappedStatement(String id, SqlSource sqlSource, StatementType statementType, SqlCommandType sqlCommandType, Class<?> resultTypeClass) {
        MappedStatement statement = MappedStatement.builder()
                .id(id)
                .sqlSource(sqlSource)
                .statementType(statementType)
                .sqlCommandType(sqlCommandType)
                .resultMap(ResultMap.builder().type(resultTypeClass).build())
                .build();
        this.configuration.addMappedStatement(statement);
        return statement;
    }
}
