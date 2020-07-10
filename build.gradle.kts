// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    addRepos(repositories)
    dependencies {
        classpath(ClassPath.android_gradle)
        classpath(ClassPath.kotlin_gradle)
        classpath(ClassPath.image_convert)
        classpath(ClassPath.component)
        classpath(ClassPath.dep_deduplicate)
    }
}

allprojects {
   addRepos(repositories)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
