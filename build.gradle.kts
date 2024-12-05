plugins {
    `java`
}

group = "net.citizensnpcs"
version = "2.0.36-SNAPSHOT"

val BUILD_NUMBER: String by extra("Unknown")
val CITIZENS_VERSION: String by extra("2.0.36")
val mavenJavadocPluginVersion: String by extra("3.6.3")
val mavenAssemblyPluginVersion: String by extra("3.7.1")
val mavenDeployPluginVersion: String by extra("3.1.1")
val mavenCompilerPluginVersion: String by extra("3.13.0")
val mavenJarPluginVersion: String by extra("3.4.1")
val mavenShadePluginVersion: String by extra("3.5.2")
val specialSourcePluginVersion: String by extra("2.0.3")
val allJar by tasks.registering(Jar::class) {
    archiveClassifier.set("all-jar") // Matches the Maven configuration ID.

    // Unpack and include all runtime dependencies.
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })

    includeEmptyDirs = false // Equivalent to Maven's "includeBaseDirectory=false".
}

repositories {
    maven {
        url = uri("https://maven.citizensnpcs.co/repo")
    }
    maven {
        url = uri("https://repo.codemc.io/repository/nms/")
    }
}

tasks {
    val clean by getting
    val packageTask by creating { dependsOn(clean) }
    val install by creating { dependsOn(packageTask) }

    defaultTasks("clean", "packageTask", "install")
}

// Ensure the new all-jar task is part of the build lifecycle.
tasks.named("assemble") {
    dependsOn(allJar)
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }
}

val spigotReleaseModules = listOf(
    ":main",
    ":v1_21_R2",
    ":dist"
)

val fullModules = listOf(
    ":main",
    ":v1_21_R2",
    ":dist"
)

val devModules = listOf(
    ":main",
    ":v1_21_R2",
    ":dist"
)

tasks.register("spigotRelease") {
    dependsOn(spigotReleaseModules)
}

tasks.register("full") {
    dependsOn(fullModules)
}

tasks.register("dev") {
    dependsOn(devModules)
}