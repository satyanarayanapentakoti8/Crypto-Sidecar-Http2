plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.adcb.grpc-client"
version = "0.0.1-SNAPSHOT"
description = "Demo project for grpc-client-app"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

    testImplementation("io.projectreactor:reactor-test")
    implementation("org.springframework.boot:spring-boot-starter-webflux")   // includes reactive web support + Reactor Netty
    implementation("com.fasterxml.jackson.core:jackson-databind")            // JSON (de)serialization
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("io.projectreactor.netty:reactor-netty-http")             // HTTP client / server via Reactor Netty
    implementation("io.projectreactor.netty:reactor-netty-core")
	implementation("org.springframework.boot:spring-boot-starter")
    //implementation("org.apache.httpcomponents:httpclient:4.5.14")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
