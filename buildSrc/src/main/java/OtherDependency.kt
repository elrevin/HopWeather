import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.other() {
    implementation(Dependecies.Other.core)
    implementation(Dependecies.Other.appcompat)
}