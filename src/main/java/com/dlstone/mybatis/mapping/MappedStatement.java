package com.dlstone.mybatis.mapping;

import com.dlstone.mybatis.session.Configuration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MappedStatement {
    private String resource;
    private Configuration configuration;
    private String id ;
    private StatementType statementType;
    private SqlSource sqlSource;
    private SqlCommandType sqlCommandType;
    private ResultMap resultMap;
}
