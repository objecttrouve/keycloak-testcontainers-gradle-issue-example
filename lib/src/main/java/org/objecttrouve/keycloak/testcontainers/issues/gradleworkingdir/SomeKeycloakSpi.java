package org.objecttrouve.keycloak.testcontainers.issues.gradleworkingdir;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.keycloak.services.resource.RealmResourceProvider;

public class SomeKeycloakSpi implements RealmResourceProvider {

    @Override
    public Object getResource() {
        return this;
    }

    @Override
    public void close() {}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "Hello world!";
    }

}
