import java.util.Date

plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.dio"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

var mapStructVersion = "1.6.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.mapstruct:mapstruct:$mapStructVersion")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")


	compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.h2database:h2")

	annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.named("build") {
	doLast{
		val trigger =file("src/main/resources/trigger.txt")
		if (!trigger.exists()){ //se o arquivo nao existir ele ira criar
			trigger.createNewFile()
		}
		trigger.writeText(Date().time.toString()) //se existir ira sobrescrever colocando a data e hora atual
	}
}

tasks.named<JavaExec>("bootRun"){

	jvmArgs = listOf("-Xrunjdwp:tranport=dt_socket,server=y,suspend=n,address=*:5005")

	jvmArgs = listOf("-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
