import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.hilt() {
    implementation(Dependecies.Hilt.hilt)
    kapt(Dependecies.Hilt.kapt)
}

fun DependencyHandler.hiltNavigation() {
    implementation(Dependecies.Hilt.navigation)
}