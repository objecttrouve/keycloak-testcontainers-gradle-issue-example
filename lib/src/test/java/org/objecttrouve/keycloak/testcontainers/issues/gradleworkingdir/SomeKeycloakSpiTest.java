package org.objecttrouve.keycloak.testcontainers.issues.gradleworkingdir;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.info.ProviderRepresentation;
import static org.objecttrouve.testing.matchers.ConvenientMatchers.a;
import static org.objecttrouve.testing.matchers.ConvenientMatchers.aMapLike;
import org.testcontainers.junit.jupiter.Container;

/* Tests in this class fail intentionally. It's about reproducing an issue. */
class SomeKeycloakSpiTest {

    @Container
    private KeycloakContainer keycloak;

    @DisplayName("Test adding classed from `build/libs`")
    @Test
    void testSomeSpiFromBuildClasses(){
        keycloak = keycloakContainer()
                .withProviderClassesFrom("build/classes");

        keycloak.start();

        checkSpiAdded();
    }

    @DisplayName("Test adding classed from `classes`")
    @Test
    void testSomeSpiFromClasses(){
        keycloak = keycloakContainer()
                .withProviderClassesFrom("classes");

        keycloak.start();

        checkSpiAdded();
    }

    @DisplayName("Test adding classed from `../classes` (which should actually work)")
    @Test
    void testSomeSpiFromDotDotClasses(){
        keycloak = keycloakContainer()
                .withProviderClassesFrom("../classes");

        keycloak.start();

        checkSpiAdded();
    }

    @DisplayName("Test adding classed from ``")
    @Test
    void testSomeSpiFromModuleDir(){
        keycloak = keycloakContainer()
                .withProviderClassesFrom("");

        keycloak.start();

        checkSpiAdded();
    }

    @DisplayName("Test adding classed from `build`")
    @Test
    void testSomeSpiFromBuild(){
        keycloak = keycloakContainer()
                .withProviderClassesFrom("build");

        keycloak.start();

        checkSpiAdded();
    }

    private void checkSpiAdded() {
        try(Keycloak keycloakAdminClient = keycloak.getKeycloakAdminClient()){

            Map<String, ProviderRepresentation> restSpis = keycloakAdminClient.serverInfo().getInfo().getProviders().get("realm-restapi-extension").getProviders();

            assertThat(restSpis, is(aMapLike(restSpis)
                    // Keycloak 22.0.1 defaults plus our test SPI
                    .ofSize(2)
                    .withKeyValMatching(equalTo("device"), a(ProviderRepresentation.class))
                    .withKeyValMatching(equalTo("some-keycloak-spi"), a(ProviderRepresentation.class))
            ));
        }
    }

    @SuppressWarnings("resource")
    private static KeycloakContainer keycloakContainer() {
        return new KeycloakContainer()
                // Not working for some reason.
                // .withAdminUsername("admin")
                // .withAdminPassword("admin")
                .withEnv("KEYCLOAK_ADMIN", "admin")
                .withEnv("KEYCLOAK_ADMIN_PASSWORD", "admin");
    }

}