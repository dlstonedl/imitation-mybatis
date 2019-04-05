package com.dlstone.mybatis.scripting.xmltags;

import java.util.Iterator;
import java.util.List;

public class MixedSqlNode implements SqlNode {
    private final List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

    @Override
    public boolean apply(DynamicContext context) {
        Iterator<SqlNode> iterator = this.contents.iterator();
        while(iterator.hasNext()) {
            SqlNode sqlNode = iterator.next();
            sqlNode.apply(context);
        }
        return true;
    }
}
