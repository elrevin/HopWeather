import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.retrofit() {
    implementation(Dependecies.Retrofit.retrofit)
    implementation(Dependecies.Retrofit.gsonConverter)
    testImplementation(Dependecies.Retrofit.mockwebserver)
}