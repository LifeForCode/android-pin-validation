package com.github.lifeforcode.pinvalidation.domain.model.pincode

import com.github.lifeforcode.pinvalidation.common.pincode.PinCodeMaxSize
import com.github.lifeforcode.pinvalidation.common.pincode.PinCodeMinSize

@JvmInline
value class PinCode(private val asString: String) {

  companion object {
    fun fromString(value: String): PinCode {
      checkValue(value)
      return PinCode(value)
    }

    private fun checkValue(value: String) {
      if (value.length < PinCodeMinSize || value.length > PinCodeMaxSize)
        throw IllegalArgumentException("Invalid pin code length: current=${value.length} min=$PinCodeMinSize max=$PinCodeMaxSize")
      val isAllCharsDigits = value.toCharArray().all { it.isDigit() }
      if (!isAllCharsDigits)
        throw IllegalArgumentException("Invalid pin code: $value")
    }
  }

  val size: Int get() = asString.length

  override fun toString(): String = asString
}
