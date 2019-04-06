package com.dlstone.mybatis.session;

import com.dlstone.mybatis.binding.MapperRegistry;
import com.dlstone.mybatis.mapping.Environment;
import com.dlstone.mybatis.mapping.MappedStatement;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Setter
public class Configuration {
    private Environment environment;
    protected final Set<String> loadedResources;
    protected final Map<String, MappedStatement> mappedStatements;
    protected final MapperRegistry mapperRegistry;

    public Configuration() {
        this.loadedResources = new HashSet<>();
        this.mappedStatements = new HashMap<>();
        this.mapperRegistry = new MapperRegistry(this);
    }

    public boolean isResourceLoaded(String resource) {
        return this.loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        this.loadedResources.add(resource);
    }

    public void addMappedStatement(MappedStatement statement) {
        mappedStatements.put(statement.getId(), statement);
    }

    public void addMapper(Class<?> type) {
        this.mapperRegistry.addMapper(type);
    }
}
