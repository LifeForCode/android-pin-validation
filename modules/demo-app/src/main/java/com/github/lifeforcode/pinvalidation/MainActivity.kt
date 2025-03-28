package com.github.lifeforcode.pinvalidation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.lifeforcode.pinvalidation.domain.model.pincode.PinCode
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.result.PinValidationResult
import com.github.lifeforcode.pinvalidation.presentation.view.components.PinValidation
import com.github.lifeforcode.pinvalidation.ui.theme.PinValidationViewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Content()
    }
  }

  @Composable
  private fun Content() {
    PinValidationViewTheme {
      Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
      ) {
        PinValidation(PinCode.fromString("1234"), ::onPinValidationResult)
      }
    }
  }

  private fun onPinValidationResult(result: PinValidationResult) {
    if (result != PinValidationResult.NotPresent)
      runOnUiThread {
        Toast.makeText(this@MainActivity, "$result", Toast.LENGTH_SHORT).show()
      }
  }
}
