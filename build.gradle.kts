plugins {
    id("org.springframework.boot") version "2.6.3"
    `java-library`
    `jvm-test-suite`
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.3"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    jar {
        enabled = false
    }
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
        val testIntegration by registering(JvmTestSuite::class) {
            dependencies {
                implementation(project)
                implementation("org.springframework.boot:spring-boot-starter-test:2.6.3")
            }
        }
    }
}