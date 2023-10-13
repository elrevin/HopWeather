import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.locationService() {
    implementation(Dependecies.Other.locationServeice)
}