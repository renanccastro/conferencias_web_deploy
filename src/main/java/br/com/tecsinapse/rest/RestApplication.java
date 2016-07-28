package br.com.tecsinapse.rest;


import br.com.tecsinapse.rest.api.BaseEndpoint;
import br.com.tecsinapse.rest.api.ContentEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        resources.add(ContentEndpoint.class);

        return resources;
    }

}