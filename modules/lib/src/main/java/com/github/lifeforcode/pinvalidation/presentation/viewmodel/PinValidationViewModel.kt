package com.github.lifeforcode.pinvalidation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEvent
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEvent.AddDigit
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEvent.RemoveLastDigit
import com.github.lifeforcode.pinvalidation.domain.model.pincode.provider.IPinCodeProvider
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.result.IPinValidationResultHandler
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.result.PinValidationResult
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinInput
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationState
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PinValidationViewModel(
  pinCodeProvider: IPinCodeProvider,
  private val mResultHandler: IPinValidationResultHandler,
) : ViewModel() {

  private val mPinCode = pinCodeProvider.pinCode

  private val mUiState = MutableStateFlow(PinValidationUiState(mPinCode.size))
  internal val uiState: StateFlow<PinValidationUiState> = mUiState.asStateFlow()

  internal fun onEvent(event: IPinValidationEvent) {
    viewModelScope.launch(Dispatchers.Default) {
      when (event) {
        is AddDigit -> handleAddDigitEvent(event)
        RemoveLastDigit -> handleRemoveDigitEvent()
      }
    }
  }

  private fun handleAddDigitEvent(event: AddDigit) {
    if (mUiState.value.pinInput.isFull)
      return
    mUiState.update {
      val newPinInput = PinInput.from(it.pinInput)
      newPinInput.add(event.digit)
      it.copyOnInput(newPinInput)
    }
    if (mUiState.value.isReadyToValidate)
      validatePinCode()
  }

  private fun handleRemoveDigitEvent() {
    mUiState.update {
      val newPinInput = PinInput.from(it.pinInput)
      newPinInput.delete()
      it.copyOnInput(newPinInput)
    }
  }

  private fun PinValidationUiState.copyOnInput(newPinInput: PinInput): PinValidationUiState =
    this.copy(
      pinInput = newPinInput,
      validationState = if (newPinInput.isFull) PinValidationState.Ready else PinValidationState.NotReady,
    )

  private val PinInput.isFull get() = this.size == mPinCode.size

  private fun validatePinCode() {
    val pinCode = mUiState.value.pinInput.asPinCode
    val validationState = if (pinCode == mPinCode) PinValidationState.Success else PinValidationState.Failed
    mUiState.update {
      it.copy(validationState = validationState)
    }
    val validationResult = validationState.toValidationResult()
    mResultHandler(validationResult)
  }

  private fun PinValidationState.toValidationResult(): PinValidationResult =
    when (this) {
      PinValidationState.Success -> PinValidationResult.Success
      PinValidationState.Failed -> PinValidationResult.Failed
      else -> PinValidationResult.NotPresent
    }
}
