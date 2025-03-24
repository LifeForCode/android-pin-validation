package com.github.lifeforcode.pinvalidation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEvent
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEvent.AddDigit
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEvent.RemoveLastDigit
import com.github.lifeforcode.pinvalidation.domain.model.pincode.PinCode
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.result.IPinValidationResultHandler
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.result.PinValidationResult
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinInput
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationState
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

internal class PinValidationViewModel(
  private val mPinCode: PinCode,
  private val mResultHandler: IPinValidationResultHandler,
) : ViewModel() {

  private val mPinCodeValidator = PinCodeValidator()

  private val mInitialUiState get() = PinValidationUiState(mPinCode.size)
  private val mUiState = MutableStateFlow(mInitialUiState)
  internal val uiState: StateFlow<PinValidationUiState> = mUiState.asStateFlow()

  internal fun onEvent(event: IPinValidationEvent) {
    viewModelScope.launch {
      when (event) {
        is AddDigit -> handleAddDigitEvent(event)
        is RemoveLastDigit -> handleRemoveDigitEvent()
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
      mPinCodeValidator.invoke()
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

  private inner class PinCodeValidator {

    fun invoke() {
      val pinCode = mUiState.value.pinInput.asPinCode
      val validationState = if (pinCode == mPinCode) PinValidationState.Success else PinValidationState.Failed
      mUiState.update {
        it.copy(validationState = validationState)
      }
      val validationResult = validationState.toValidationResult()
      mResultHandler(validationResult)
      if (validationState.isFailed)
        setInitialUiState()
    }

    private fun PinValidationState.toValidationResult(): PinValidationResult =
      when (this) {
        PinValidationState.Success -> PinValidationResult.Success
        PinValidationState.Failed -> PinValidationResult.Failed
        else -> PinValidationResult.NotPresent
      }

    private fun setInitialUiState() {
      viewModelScope.launch {
        delay(700.milliseconds)
        mUiState.value = mInitialUiState
      }
    }
  }
}
