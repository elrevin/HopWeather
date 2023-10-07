import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.room() {
    implementation(Dependecies.Room.runtime)
    kapt(Dependecies.Room.compiler)
    implementation(Dependecies.Room.ktx)
}