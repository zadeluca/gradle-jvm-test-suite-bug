plugins {
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
                implementation(project())
                implementation(sourceSets.main.get().output)
            }
        }
    }
}
