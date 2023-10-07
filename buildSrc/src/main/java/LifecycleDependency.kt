import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.lifecycle() {
    implementation(Dependecies.Lifecycle.lifecycleRuntime)
    implementation(Dependecies.Lifecycle.activity)
}