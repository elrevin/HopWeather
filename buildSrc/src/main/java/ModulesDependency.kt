import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.coreModule() {
    implementation(project(":core"))
}

fun DependencyHandler.domainModule() {
    implementation(project(":domain"))
}

fun DependencyHandler.dataModule() {
    implementation(project(":data"))
}

fun DependencyHandler.presentationModule() {
    implementation(project(":presentation"))
}
