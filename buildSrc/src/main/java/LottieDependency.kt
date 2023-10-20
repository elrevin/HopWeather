import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.lottie() {
    implementation(Dependecies.Lottie.lottie)
}