package com.github.lifeforcode.pinvalidation.common.pincode

@JvmInline
internal value class Digit(private val asChar: Char) {

  companion object {
    fun fromChar(value: Char) =
      if (value.isDigit())
        Digit(value)
      else
        throw IllegalArgumentException("$value is not a digit")
  }

  override fun toString(): String = "$asChar"
}
