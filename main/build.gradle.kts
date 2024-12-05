plugins {
    java
    id("buildlogic.java-conventions")
    id("com.gradleup.shadow") version "8.3.5"
}

group = "net.citizensnpcs"
version = "2.0.36-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigot-repo"
    }
    maven("https://jitpack.io") {
        name = "jitpack.io"
        //content { snapshotsOnly() }
    }
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/") {
        name = "placeholderapi"
    }
    maven("https://maven.enginehub.org/repo/") {
        name = "sk89q-repo"
    }
    maven("https://repo.dmulloy2.net/repository/public/") {
        name = "dmulloy2-repo"
    }
    maven("https://repo.alessiodp.com/releases/") {
        name = "AlessioDP"
    }
    maven("https://maven.citizensnpcs.co/repo") {
        name = "citizens-repo"
    }
}

dependencies {
    api(libs.net.citizensnpcs.citizensapi)
    testImplementation(libs.org.reflections.reflections)
    compileOnly(libs.org.spigotmc.spigot.x1)
    compileOnly(libs.it.unimi.dsi.fastutil)
    compileOnly(libs.com.comphenix.protocol.protocollib)
    compileOnly(libs.ch.ethz.globis.phtree.phtree)
    compileOnly(libs.org.joml.joml)
    compileOnly(libs.me.clip.placeholderapi)
    compileOnly(libs.com.github.milkbowl.vaultapi)
    compileOnly(libs.com.sk89q.worldguard.worldguard.bukkit)
    compileOnly(libs.net.kyori.adventure.text.minimessage)
    compileOnly(libs.net.kyori.adventure.platform.bukkit)
    //implementation("net.citizensnpcs:citizensapi:2.0.36-SNAPSHOT")

    //compileOnly("net.citizensnpcs:citizensapi:2.0.36-SNAPSHOT")
}

description = "citizens-main"

tasks {
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest {
            attributes["Main-Class"] = "YourMainClass" // Replace with your actual main class
        }
        from("src/main/resources") {
            include("plugin.yml", "*.json", "LICENSE")
        }
    }

    compileJava {
        options.encoding = "UTF-8"
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    javadoc {
        source = fileTree("src/main/java")
    }

    shadowJar {
        minimize()
        dependencies {
            exclude(dependency("net.citizensnpcs:citizensapi"))
        }
        // Uncomment and configure if relocations are needed
        /*
        relocate("it.unimi.dsi", "clib.fastutil")
        relocate("net.kyori", "clib.net.kyori")
        relocate("net.byteflux.libby", "clib.net.byteflux.libby")
        */
    }
}