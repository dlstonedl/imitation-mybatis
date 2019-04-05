package com.dlstone.mybatis.scripting.xmltags;

import com.dlstone.mybatis.builder.BaseBuilder;
import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.parsing.XNode;
import com.dlstone.mybatis.scripting.defaults.RawSqlSource;
import com.dlstone.mybatis.session.Configuration;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class XMLScriptBuilder extends BaseBuilder {
    private final XNode context;

    public XMLScriptBuilder(Configuration configuration, XNode context) {
        super(configuration);
        this.context = context;
    }

    public SqlSource parseScriptNode() {
        List<SqlNode> contents = this.parseDynamicTags(this.context);
        MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
        return new RawSqlSource(this.configuration, rootSqlNode);
    }

    List<SqlNode> parseDynamicTags(XNode node) {
        List<SqlNode> contents = new ArrayList<>();
        NodeList children = node.getNode().getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            XNode child = node.newXNode(children.item(i));
            String nodeName = child.getStringBody("");
            contents.add(new StaticTextSqlNode(nodeName));
        }
        return contents;
    }
}
