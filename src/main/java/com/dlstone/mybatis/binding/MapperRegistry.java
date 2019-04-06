package com.dlstone.mybatis.binding;

import com.dlstone.mybatis.session.Configuration;

public class MapperRegistry {
    private Configuration config;

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    public <T> void addMapper(Class<T> type) {

    }


}
