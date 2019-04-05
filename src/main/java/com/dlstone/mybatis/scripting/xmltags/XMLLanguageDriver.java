package com.dlstone.mybatis.scripting.xmltags;

import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.parsing.XNode;
import com.dlstone.mybatis.scripting.LanguageDriver;
import com.dlstone.mybatis.session.Configuration;

public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, XNode node) {
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, node);
        return builder.parseScriptNode();
    }
}
