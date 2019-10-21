import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}
tasks.compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(Dependencies.Libs.kotlinJdk)
    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.mockito)
    testImplementation(Dependencies.TestLibs.coroutines)
}