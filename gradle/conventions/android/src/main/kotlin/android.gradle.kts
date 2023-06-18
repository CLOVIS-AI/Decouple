package opensavvy

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

fun CommonExtension<*, *, *, *>.configure() {
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

pluginManager.withPlugin("com.android.library") {
    extensions.configure<LibraryExtension> {
        configure()
    }
}

pluginManager.withPlugin("com.android.application") {
    extensions.configure<BaseAppModuleExtension> {
        configure()

        defaultConfig {
            targetSdk = 33
        }
    }
}
