package com.github.lifeforcode.pinvalidation.domain.event

import com.github.lifeforcode.pinvalidation.common.pincode.Digit

internal sealed interface IPinValidationEvent {
  data class AddDigit(val digit: Digit) : IPinValidationEvent
  data object RemoveLastDigit : IPinValidationEvent
}
