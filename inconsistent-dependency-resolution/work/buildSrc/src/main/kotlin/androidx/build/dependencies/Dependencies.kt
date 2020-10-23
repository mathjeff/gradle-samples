/*
 */
package androidx.build.dependencies
const val ANDROIDX_TEST_CORE = "androidx.test:core:1.3.0"
const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:1.1.2"
const val ANDROIDX_TEST_RULES = "androidx.test:rules:1.3.0"
const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:1.3.0"
const val DEXMAKER_MOCKITO = "com.linkedin.dexmaker:dexmaker-mockito:2.25.0"
const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.3.0"
const val GUAVA_VERSION = "29.0-jre"
const val GUAVA = "com.google.guava:guava:$GUAVA_VERSION"
const val GUAVA_ANDROID_VERSION = "29.0-android"
internal lateinit var hiltVersion: String
const val JUNIT = "junit:junit:4.12"
const val KOTLINPOET_CLASSINSPECTOR_ELEMENTS =
    "com.squareup:kotlinpoet-classinspector-elements:1.4.0"
const val LEAKCANARY_INSTRUMENTATION =
    "com.squareup.leakcanary:leakcanary-android-instrumentation:2.2"
const val MOCKITO_CORE = "org.mockito:mockito-core:2.25.0"
const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
const val SKIKO_VERSION = "0.1.7"
val SKIKO_CURRENT_OS by lazy {
}
const val TRUTH = "com.google.truth:truth:1.0.1"

const val SHADOW_PLUGIN = "com.github.jengelman.gradle.plugins:shadow:5.2.0"
internal lateinit var kotlinVersion: String
val KOTLIN_STDLIB get() = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
val KOTLIN_TEST get() = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
val KOTLIN_TEST_ANNOTATIONS_COMMON get() =
    "org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinVersion"
internal lateinit var kotlinCoroutinesVersion: String
val KOTLIN_COROUTINES_ANDROID
    get() = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion"
val KOTLIN_COROUTINES_SWING
    get() = "org.jetbrains.kotlinx:kotlinx-coroutines-swing:$kotlinCoroutinesVersion"
val KOTLIN_COROUTINES_CORE
    get() = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion"
val KOTLIN_COROUTINES_GUAVA
    get() = "org.jetbrains.kotlinx:kotlinx-coroutines-guava:$kotlinCoroutinesVersion"
val KOTLIN_COROUTINES_TEST
    get() = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutinesVersion"
val KOTLIN_COROUTINES_RX2
    get() = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$kotlinCoroutinesVersion"
val KOTLIN_COROUTINES_RX3
    get() = "org.jetbrains.kotlinx:kotlinx-coroutines-rx3:$kotlinCoroutinesVersion"
internal lateinit var agpVersion: String
internal lateinit var lintVersion: String
internal const val lintMinVersion = "26.3.0"
