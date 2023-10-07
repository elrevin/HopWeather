import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.test() {
    testImplementation(Dependecies.Test.junit)
    androidTestImplementation(Dependecies.Test.androidxTestJunit)
    androidTestImplementation(Dependecies.Test.espressoCore)
}

fun DependencyHandler.composeTest() {
    testImplementation(Dependecies.Test.junit)
    androidTestImplementation(Dependecies.Test.androidxTestJunit)
    androidTestImplementation(Dependecies.Test.espressoCore)
}