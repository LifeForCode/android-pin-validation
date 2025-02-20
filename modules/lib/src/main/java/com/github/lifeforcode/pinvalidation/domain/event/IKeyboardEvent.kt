package com.github.lifeforcode.pinvalidation.domain.event

import com.github.lifeforcode.pinvalidation.common.pincode.Digit
import com.github.lifeforcode.pinvalidation.domain.event.IKeyboardEvent.DeleteLastDigit

internal interface IKeyboardEvent {
  data class DigitInput(val digit: Digit) : IKeyboardEvent
  data object DeleteLastDigit : IKeyboardEvent
}

internal val IKeyboardEvent.isDeleteLastDigit get() = this is DeleteLastDigit
