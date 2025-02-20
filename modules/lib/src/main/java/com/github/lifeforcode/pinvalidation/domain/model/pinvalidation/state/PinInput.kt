package com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state

import com.github.lifeforcode.pinvalidation.common.pincode.Digit
import com.github.lifeforcode.pinvalidation.domain.model.pincode.PinCode

internal data class PinInput(
  private val mInputDigits: MutableList<Digit>,
) {

  companion object {

    val Empty = PinInput(arrayListOf())

    fun from(value: PinInput) =
      PinInput(ArrayList(value.mInputDigits))
  }

  val size: Int get() = mInputDigits.size

  val asPinCode: PinCode
    get() {
      val value = mInputDigits.joinToString(separator = "")
      return PinCode.fromString(value)
    }

  fun add(digit: Digit) {
    mInputDigits.add(digit)
  }

  fun delete() {
    mInputDigits.removeLastOrNull()
  }

  override fun toString(): String =
    asPinCode.toString()
}
