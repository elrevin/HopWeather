import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.hilt() {
    implementation(Dependecies.Hilt.hilt)
    kapt(Dependecies.Hilt.kapt)
}