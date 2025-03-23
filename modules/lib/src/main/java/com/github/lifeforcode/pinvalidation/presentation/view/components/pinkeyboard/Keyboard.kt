package com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.lifeforcode.pinvalidation.domain.event.IKeyboardEvent
import com.github.lifeforcode.pinvalidation.domain.event.IKeyboardEvent.DeleteLastDigit
import com.github.lifeforcode.pinvalidation.domain.event.IKeyboardEvent.DigitInput
import com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state.PinValidationUiState
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit0Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit1Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit2Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit3Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit4Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit5Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit6Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit7Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit8Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.DigitButton.Digit9Button
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard.IconButton.DeleteButton

@Composable
internal fun Keyboard(
  state: PinValidationUiState,
  onEvent: (IKeyboardEvent) -> Unit,
) {
  val rowModifier = Modifier.wrapContentSize()
  val rowArrangement = Arrangement.spacedBy(20.dp)
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(5.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    FirstRow(rowModifier, rowArrangement, onEvent)
    SecondRow(rowModifier, rowArrangement, onEvent)
    ThirdRow(rowModifier, rowArrangement, onEvent)
    FourthRow(rowModifier, rowArrangement, shouldShowDeleteButton = state.pinCodeInputSize > 0, onEvent)
  }
}

@Composable
private fun FirstRow(modifier: Modifier, arrangement: Arrangement.Horizontal, onEvent: (IKeyboardEvent) -> Unit) {
  Row(
    modifier = modifier,
    horizontalArrangement = arrangement,
  ) {
    KeyboardButton(Digit1Button) { onEvent(DigitInput(Digit1Button.digit)) }
    KeyboardButton(Digit2Button) { onEvent(DigitInput(Digit2Button.digit)) }
    KeyboardButton(Digit3Button) { onEvent(DigitInput(Digit3Button.digit)) }
  }
}

@Composable
private fun SecondRow(modifier: Modifier, arrangement: Arrangement.Horizontal, onEvent: (IKeyboardEvent) -> Unit) {
  Row(
    modifier = modifier,
    horizontalArrangement = arrangement,
  ) {
    KeyboardButton(Digit4Button) { onEvent(DigitInput(Digit4Button.digit)) }
    KeyboardButton(Digit5Button) { onEvent(DigitInput(Digit5Button.digit)) }
    KeyboardButton(Digit6Button) { onEvent(DigitInput(Digit6Button.digit)) }
  }
}

@Composable
private fun ThirdRow(modifier: Modifier, arrangement: Arrangement.Horizontal, onEvent: (IKeyboardEvent) -> Unit) {
  Row(
    modifier = modifier,
    horizontalArrangement = arrangement,
  ) {
    KeyboardButton(Digit7Button) { onEvent(DigitInput(Digit7Button.digit)) }
    KeyboardButton(Digit8Button) { onEvent(DigitInput(Digit8Button.digit)) }
    KeyboardButton(Digit9Button) { onEvent(DigitInput(Digit9Button.digit)) }
  }
}

@Composable
private fun FourthRow(modifier: Modifier, arrangement: Arrangement.Horizontal, shouldShowDeleteButton: Boolean = true, onEvent: (IKeyboardEvent) -> Unit) {
  Row(
    modifier = modifier,
    horizontalArrangement = arrangement,
  ) {
    KeyboardButton(ButtonStub)
    KeyboardButton(Digit0Button) { onEvent(DigitInput(Digit0Button.digit)) }
    if (shouldShowDeleteButton) {
      KeyboardButton(DeleteButton) { onEvent(DeleteLastDigit) }
    } else {
      KeyboardButton(ButtonStub)
    }
  }
}
