plugins {
    java
    `maven-publish`
}

group = "net.citizensnpcs"
version = "2.0.36-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Define dependencies from profiles below
    implementation(project(":citizens-main"))
    implementation(project(":citizens-v1_21_R2"))
}

tasks {
    register<Jar>("packageAll") {
        archiveBaseName.set("Citizens")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("b${System.getenv("BUILD_NUMBER") ?: "SNAPSHOT"}")

        // Assembly configuration equivalent to Maven's all-jar.xml
        from(zipTree(projectDir.resolve("src/main/assembly/all-jar.xml")))
        manifest {
            attributes(
                "paperweight-mappings-namespace" to "spigot"
            )
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

java {
    withSourcesJar()
}

// Gradle profiles equivalent (using project properties or command-line flags)
if (project.hasProperty("spigotRelease")) {
    dependencies {
        implementation(project(":citizens-main"))
        implementation(project(":citizens-v1_19_R3"))
        implementation(project(":citizens-v1_20_R4"))
        implementation(project(":citizens-v1_21_R2"))
    }
}

if (project.hasProperty("full")) {
    dependencies {
        implementation(project(":citizens-main"))
        implementation(project(":citizens-v1_8_R3"))
        implementation(project(":citizens-v1_10_R1"))
        implementation(project(":citizens-v1_11_R1"))
        implementation(project(":citizens-v1_12_R1"))
        implementation(project(":citizens-v1_13_R2"))
        implementation(project(":citizens-v1_14_R1"))
        implementation(project(":citizens-v1_15_R1"))
        implementation(project(":citizens-v1_16_R3"))
        implementation(project(":citizens-v1_17_R1"))
        implementation(project(":citizens-v1_18_R2"))
        implementation(project(":citizens-v1_19_R3"))
        implementation(project(":citizens-v1_20_R4"))
        implementation(project(":citizens-v1_21_R2"))
    }
}

if (project.hasProperty("dev")) {
    dependencies {
        implementation(project(":citizens-main"))
        implementation(project(":citizens-v1_21_R2"))
    }
}