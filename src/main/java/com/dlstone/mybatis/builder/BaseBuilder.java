package com.dlstone.mybatis.builder;

import com.dlstone.mybatis.session.Configuration;

public abstract class BaseBuilder {
    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    protected Class<?> resolveClass(String alias){
        if (alias == null) {
            return null;
        } else {
            try {
                return Class.forName(alias, true, Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
