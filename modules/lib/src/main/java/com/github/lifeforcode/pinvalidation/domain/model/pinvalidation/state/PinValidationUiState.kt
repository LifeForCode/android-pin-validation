package com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state

internal data class PinValidationUiState(
  val pinSize: Int, // Original PIN code size
  val pinInput: PinInput = PinInput.Empty,
  val validationState: PinValidationState = PinValidationState.NotReady,
) {
  val pinCodeInputSize get() = pinInput.size
  val isReadyToValidate get() = validationState.isReady
}
