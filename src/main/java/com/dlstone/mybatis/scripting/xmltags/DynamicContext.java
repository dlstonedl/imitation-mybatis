package com.dlstone.mybatis.scripting.xmltags;

import java.util.HashMap;
import java.util.Map;

public class DynamicContext {
    private final Map<String,Object> bindings = new HashMap<>();
    private final StringBuilder sqlBuilder = new StringBuilder();

    public void appendSql(String sql) {
        this.sqlBuilder.append(sql);
        this.sqlBuilder.append(" ");
    }

    public String getSql() {
        return this.sqlBuilder.toString().trim();
    }
}
