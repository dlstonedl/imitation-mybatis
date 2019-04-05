package com.dlstone.mybatis.session;

import com.dlstone.mybatis.mapping.Environment;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
public class Configuration {
    private Environment environment;
    protected final Set<String> loadedResources;

    public Configuration() {
        this.loadedResources = new HashSet<>();
    }

    public boolean isResourceLoaded(String resource) {
        return this.loadedResources.contains(resource);
    }
}
