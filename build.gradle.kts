plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.serialization") version "2.3.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion = "3.5.0"
val orgJsonVersion = "20260522"

dependencies {
    testImplementation(kotlin("test"))
    // ---- .env support ----
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")

    // ---- Ktor server ----
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:${ktorVersion}")
    implementation("ch.qos.logback:logback-classic:1.5.25")

    // ---- SQLite (JDBC driver, works directly with Ktor via a DataSource) ----
    implementation("org.xerial:sqlite-jdbc:3.53.2.0")

    // Optional but common with Ktor + SQLite: a lightweight SQL layer
    // (skip if you're writing raw JDBC yourself)
    implementation("org.jetbrains.exposed:exposed-core:0.61.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.61.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.61.0")

    // ---- Apache POI (XSSF = .xlsx writer, supports embedding images) ----
    implementation("org.apache.poi:poi:5.5.1")
    implementation("org.apache.poi:poi-ooxml:5.5.1")

    // ---- JSON
    implementation("org.json:json:$orgJsonVersion")

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("org.example.MainKt")
}

tasks.test {
    useJUnitPlatform()
}