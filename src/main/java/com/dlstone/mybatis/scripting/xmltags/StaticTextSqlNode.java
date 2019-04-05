package com.dlstone.mybatis.scripting.xmltags;

public class StaticTextSqlNode implements SqlNode {
    private final String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        context.appendSql(this.text);
        return true;
    }
}
