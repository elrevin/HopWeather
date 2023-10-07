import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.compose() {
    implementation(platform(Dependecies.Compose.bom))
    implementation(Dependecies.Compose.ui)
    implementation(Dependecies.Compose.graphics)
    implementation(Dependecies.Compose.preview)
    implementation(Dependecies.Compose.navigation)
    implementation(Dependecies.Compose.material)
    implementation(Dependecies.Coil.compose)
}