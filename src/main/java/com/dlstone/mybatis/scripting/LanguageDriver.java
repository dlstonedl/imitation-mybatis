package com.dlstone.mybatis.scripting;

import com.dlstone.mybatis.mapping.SqlSource;
import com.dlstone.mybatis.parsing.XNode;
import com.dlstone.mybatis.session.Configuration;

public interface LanguageDriver {
    SqlSource createSqlSource(Configuration configuration, XNode node);
}
