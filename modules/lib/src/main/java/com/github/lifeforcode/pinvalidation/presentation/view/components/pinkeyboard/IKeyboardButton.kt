package com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard

import androidx.annotation.DrawableRes
import com.github.lifeforcode.pinvalidation.R
import com.github.lifeforcode.pinvalidation.common.pincode.Digit

internal sealed interface IKeyboardButton

internal sealed class DigitButton(val digit: Digit) : IKeyboardButton {
  data object Digit0Button : DigitButton(Digit.fromChar('0'))
  data object Digit1Button : DigitButton(Digit.fromChar('1'))
  data object Digit2Button : DigitButton(Digit.fromChar('2'))
  data object Digit3Button : DigitButton(Digit.fromChar('3'))
  data object Digit4Button : DigitButton(Digit.fromChar('4'))
  data object Digit5Button : DigitButton(Digit.fromChar('5'))
  data object Digit6Button : DigitButton(Digit.fromChar('6'))
  data object Digit7Button : DigitButton(Digit.fromChar('7'))
  data object Digit8Button : DigitButton(Digit.fromChar('8'))
  data object Digit9Button : DigitButton(Digit.fromChar('9'))
}

internal sealed class IconButton(@DrawableRes val drawableResId: Int) : IKeyboardButton {
  data object DeleteButton : IconButton(R.drawable.del_icon)
}

internal data object ButtonStub : IKeyboardButton
