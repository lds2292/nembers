import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
    kotlin("jvm")
    kotlin("plugin.spring") apply false
}

val projectGroup : String by project
val applicationVersion : String by project

allprojects{
    group = projectGroup
    version = applicationVersion

    repositories {
        maven{
            url = uri("http://localhost:8082/repository/maven-central")
            isAllowInsecureProtocol = true
            credentials{
                username = ""
                password = ""
            }
        }
        mavenCentral()
    }
}

java.sourceCompatibility = JavaVersion.VERSION_11

subprojects{

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    tasks.getByName("bootJar"){
        enabled = false
    }

    tasks.getByName("jar"){
        enabled = true
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
