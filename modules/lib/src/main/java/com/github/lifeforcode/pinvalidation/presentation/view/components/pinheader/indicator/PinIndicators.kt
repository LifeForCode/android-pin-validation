package com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationState

@Composable
internal fun PinIndicators(pinSize: Int, pinInputSize: Int, validationState: PinValidationState) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    repeat(pinSize) { index ->
      val indicatorState = getPinIndicatorState(index, pinInputSize, validationState)
      PinIndicator(indicatorState)
    }
  }
}

private fun getPinIndicatorState(index: Int, pinInputSize: Int, validationState: PinValidationState): PinIndicatorState =
  when {
    pinInputSize == 0 || index >= pinInputSize -> PinIndicatorState.Disabled
    validationState.isSuccess -> PinIndicatorState.EnabledOnSuccess
    validationState.isFailed -> PinIndicatorState.EnabledOnFailed
    else -> PinIndicatorState.Enabled
  }
