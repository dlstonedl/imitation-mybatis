package com.dlstone.mybatis.builder.xml;

import com.dlstone.mybatis.builder.BaseBuilder;
import com.dlstone.mybatis.builder.MapperBuilderAssistant;
import com.dlstone.mybatis.mapping.SqlCommandType;
import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.mapping.StatementType;
import com.dlstone.mybatis.parsing.XNode;
import com.dlstone.mybatis.scripting.LanguageDriver;
import com.dlstone.mybatis.scripting.xmltags.XMLLanguageDriver;
import com.dlstone.mybatis.session.Configuration;

import java.util.Locale;

public class XMLStatementBuilder extends BaseBuilder {
    private final XNode context;
    private final MapperBuilderAssistant builderAssistant;

    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, XNode context) {
        super(configuration);
        this.context = context;
        this.builderAssistant = builderAssistant;
    }

    public void parseStatementNode() {
        String id = this.context.getStringAttribute("id");
        String resultType = this.context.getStringAttribute("resultType");
        Class<?> resultTypeClass = this.resolveClass(resultType);
        StatementType statementType = StatementType.PREPARED;
        String nodeName = this.context.getNode().getNodeName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
        LanguageDriver langDriver = new XMLLanguageDriver();
        SqlSource sqlSource = langDriver.createSqlSource(this.configuration, this.context);
        this.builderAssistant.addMappedStatement(id, sqlSource, statementType, sqlCommandType, resultTypeClass, langDriver);
    }

}
