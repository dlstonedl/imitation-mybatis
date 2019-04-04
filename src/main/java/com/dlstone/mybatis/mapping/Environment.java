package com.dlstone.mybatis.mapping;

import lombok.Builder;

import javax.sql.DataSource;

@Builder
public class Environment {
    private final String id;
    private final DataSource dataSource;
}
