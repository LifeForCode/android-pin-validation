package com.github.lifeforcode.pinvalidation.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.lifeforcode.pinvalidation.domain.event.IKeyboardEvent
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEvent
import com.github.lifeforcode.pinvalidation.domain.event.IPinValidationEventHandler
import com.github.lifeforcode.pinvalidation.domain.event.isDeleteLastDigit
import com.github.lifeforcode.pinvalidation.domain.model.pincode.provider.IPinCodeProvider
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.result.IPinValidationResultHandler
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationUiState
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.PinValidationHeader
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.Keyboard
import com.github.lifeforcode.pinvalidation.presentation.viewmodel.PinValidationViewModel

@Composable
fun PinValidation(
  pinCodeProvider: IPinCodeProvider,
  resultHandler: IPinValidationResultHandler,
) {
  val viewModel = viewModel(pinCodeProvider, resultHandler)
  val state by viewModel.uiState.collectAsStateWithLifecycle()
  Column(
    modifier = Modifier
      .background(color = Color.White)
      .padding(bottom = 30.dp)
      .fillMaxSize(),
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    PinValidationHeader(state)
    Spacer(modifier = Modifier.height(50.dp))
    Keyboard(state) {
      if (shouldHandleKeyboardEvent(it, state))
        handleKeyBoardEvent(it, viewModel::onEvent)
    }
  }
}

@Composable
private fun viewModel(pinCodeProvider: IPinCodeProvider, resultHandler: IPinValidationResultHandler) =
  viewModel<PinValidationViewModel>(
    factory = viewModelFactory {
      addInitializer(PinValidationViewModel::class) {
        PinValidationViewModel(pinCodeProvider, resultHandler)
      }
    }
  )

private fun shouldHandleKeyboardEvent(event: IKeyboardEvent, state: PinValidationUiState): Boolean =
  event.isDeleteLastDigit || !state.isReadyToValidate

private fun handleKeyBoardEvent(event: IKeyboardEvent, pinValidationEventHandler: IPinValidationEventHandler) {
  when (event) {
    is IKeyboardEvent.DigitInput -> pinValidationEventHandler(IPinValidationEvent.AddDigit(event.digit))
    is IKeyboardEvent.DeleteLastDigit -> pinValidationEventHandler(IPinValidationEvent.RemoveLastDigit)
  }
}
