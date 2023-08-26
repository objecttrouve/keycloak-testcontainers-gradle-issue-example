This project shows a missing feature in the [Keycloak Testcontainer]: 
[On-the-fly adding of extension classes to the container] doesn't work in Gradle projects. 

The logic in the [`createKeycloakExtensionDeployment`]- and [`resolveExtensionClassLocation`]-methods is assuming a directory structure as in the Maven `target`-folder. 
However, with Gradle projects, the directory structure is different. 

⚠️ Unit tests in this project are failing intentionally! <br>
⚠️ It's not going to be maintained in any way.

👀 See [PR 112] for further details. 

Run `./gradlew clean test` to execute the (failing) tests.


[`createKeycloakExtensionDeployment`]: https://github.com/dasniko/testcontainers-keycloak/blob/c03f9718cf48356bdc3ae485c09da344d01724ba/src/main/java/dasniko/testcontainers/keycloak/ExtendableKeycloakContainer.java#L218-L249
[`resolveExtensionClassLocation`]: https://github.com/dasniko/testcontainers-keycloak/blob/c03f9718cf48356bdc3ae485c09da344d01724ba/src/main/java/dasniko/testcontainers/keycloak/ExtendableKeycloakContainer.java#L251-L257
[Keycloak Testcontainer]: https://github.com/dasniko/testcontainers-keycloak/
[On-the-fly adding of extension classes to the container]: https://github.com/dasniko/testcontainers-keycloak/#testing-custom-extensions
[PR 112]: https://github.com/dasniko/testcontainers-keycloak/pull/112