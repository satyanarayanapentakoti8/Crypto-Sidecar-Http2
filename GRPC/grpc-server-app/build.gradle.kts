plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.adcb.grpc-server"
version = "0.0.1-SNAPSHOT"
description = "Demo project for grpc-server-app"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}



repositories {
	mavenCentral()
}

dependencies {

    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    testImplementation("io.projectreactor:reactor-test")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("io.projectreactor.netty:reactor-netty-http")              // HTTP client / server via Reactor Netty
    implementation("io.projectreactor.netty:reactor-netty-core")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
