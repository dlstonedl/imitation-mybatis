package com.dlstone.mybatis.builder;

import com.dlstone.mybatis.mapping.ParameterMapping;
import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.parsing.GenericTokenParser;
import com.dlstone.mybatis.parsing.TokenHandler;
import com.dlstone.mybatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;

public class SqlSourceBuilder extends BaseBuilder {

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }


    public SqlSource parse(String originalSql, Class<Object> parameterType) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(this.configuration, parameterType);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originalSql);
        return new StaticSqlSource(configuration, sql, handler.getParameterMappings());
    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {
        private List<ParameterMapping> parameterMappings = new ArrayList<>();
        private Class<?> parameterType;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType) {
            super(configuration);
            this.parameterType = parameterType;
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }


        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        private ParameterMapping buildParameterMapping(String content) {
            return new ParameterMapping(configuration, content, Object.class);
        }


    }
}
