// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(Dependencies.BuildPlugins.androidGradle)
        classpath(Dependencies.BuildPlugins.kotlinGradlePlugin)
        classpath(Dependencies.BuildPlugins.safeArgs)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }
}

tasks.register("clean").configure {
    delete("build")
}