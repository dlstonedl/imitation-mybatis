package com.dlstone.mybatis.scripting.defaults;

import com.dlstone.mybatis.builder.SqlSourceBuilder;
import com.dlstone.mybatis.mapping.BoundSql;
import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.scripting.xmltags.DynamicContext;
import com.dlstone.mybatis.scripting.xmltags.SqlNode;
import com.dlstone.mybatis.session.Configuration;

public class RawSqlSource implements SqlSource {
    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode) {
        this(configuration, getSql(rootSqlNode));
    }

    public RawSqlSource(Configuration configuration, String sql) {
        SqlSourceBuilder builder = new SqlSourceBuilder(configuration);
        this.sqlSource = builder.parse(sql, Object.class);
    }

    private static String getSql(SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext();
        rootSqlNode.apply(context);
        return context.getSql();
    }

    @Override
    public BoundSql getBoundSql(Object var1) {
        return null;
    }
}
