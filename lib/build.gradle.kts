/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.2.1/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    mavenLocal()
}

dependencies {

    compileOnly("org.keycloak:keycloak-core:22.0.1")
    compileOnly("org.keycloak:keycloak-server-spi:22.0.1")
    compileOnly("org.keycloak:keycloak-server-spi-private:22.0.1")
    compileOnly("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")


    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.testcontainers:junit-jupiter:1.18.3")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.objecttrouve:convenience-matchers:1.2.0")
    testImplementation("org.keycloak:keycloak-core:22.0.1")
    testImplementation("org.keycloak:keycloak-server-spi:22.0.1")
    testImplementation("org.keycloak:keycloak-server-spi-private:22.0.1")

    // Last release
//    testImplementation("com.github.dasniko:testcontainers-keycloak:3.0.0")
    // Local version with Gradle extension deployment support
     testImplementation("com.github.dasniko:testcontainers-keycloak:3.0.1-SNAPSHOT")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
