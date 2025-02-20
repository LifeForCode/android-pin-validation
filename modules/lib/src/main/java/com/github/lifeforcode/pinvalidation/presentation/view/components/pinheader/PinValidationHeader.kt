package com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationUiState
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator.PinIndicators

@Composable
internal fun PinValidationHeader(state: PinValidationUiState) {
  PinIndicators(state.pinSize, state.pinCodeInputSize, state.validationState)
  Spacer(modifier = Modifier.height(30.dp))
  PinEntryText(stringResource(state.validationState.textResId))
}
