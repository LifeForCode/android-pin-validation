import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.jetpack.compose.compiler)
  alias(libs.plugins.dagger.hilt)
  alias(libs.plugins.google.devtools.ksp)
}

android {
  namespace = "com.github.lifeforcode.pinvalidation"
  compileSdk = 35

  defaultConfig {
    minSdk = 21

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
  }
  kotlinOptions {
    jvmTarget = "19"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.15"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  libraryVariants.all {
    outputs.all { output ->
      if (output is BaseVariantOutputImpl) {
        output.outputFileName = "${applicationId}-${baseName}.aar"
      }
      true
    }
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.runtime.android)
  implementation(libs.androidx.material3.android)
  implementation(libs.androidx.ui.tooling)
  implementation(libs.androidx.ui.tooling.preview.android)
  implementation(libs.androidx.lifecycle.viewmodel.compose)

  // Dagger Hilt
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}