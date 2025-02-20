package com.github.lifeforcode.pinvalidation.domain.model.pincode.provider

import com.github.lifeforcode.pinvalidation.domain.model.pincode.PinCode

abstract class BasePinCodeHolder : IPinCodeProvider {

  @Volatile private var mPinCode: PinCode? = null

  override val pinCode: PinCode get() = requirePinCode()

  fun setPinCode(value: PinCode) {
    mPinCode = value
  }

  private fun requirePinCode() =
    mPinCode ?: throw IllegalStateException("Pin code not set")
}
