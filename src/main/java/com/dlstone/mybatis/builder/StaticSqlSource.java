package com.dlstone.mybatis.builder;

import com.dlstone.mybatis.mapping.BoundSql;
import com.dlstone.mybatis.mapping.ParameterMapping;
import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.session.Configuration;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class StaticSqlSource implements SqlSource {
    private final Configuration configuration;
    private final String sql;
    private final List<ParameterMapping> parameterMappings;

    @Override
    public BoundSql getBoundSql(Object var1) {
        return null;
    }
}
