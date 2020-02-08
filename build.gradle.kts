import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "2.2.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	kotlin("plugin.jpa") version "1.3.61"
}

group = "org.solai"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
//	maven { url = uri("../sol_champ") }
	maven { url = uri("https://jitpack.io") }
	maven { url = uri("file://${projectDir}/../solai_maven_repo") }

}

dependencies {
//	implementation(project(":sol_game", configuration = "default"))
//	implementation("org.solai:sol_engine:1.0")
//	runtimeOnly("org.solai:sol_game:1.0")
//	implementation(project(":sol_champ:sol_game"))
//	implementation("com.github.sol-ai:sol_champ:-SNAPSHOT")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	implementation("org.solai:sol_game:1.0")

	val lwjglNatives = "natives-windows"
	val lwjgl_version = "3.2.3"
	val natives by configurations.creating
	listOf("", "-glfw", "-opengl", "-stb").forEach {
		println("Loading $lwjglNatives for lib: $it")
		natives("org.lwjgl:lwjgl$it:${lwjgl_version}:$lwjglNatives")
	}
	configurations["runtimeOnly"].extendsFrom(natives)
}
java {
	disableAutoTargetJvm()
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
