package com.github.lifeforcode.pinvalidation.domain.model.pincode.provider

import com.github.lifeforcode.pinvalidation.domain.model.pincode.PinCode

interface IPinCodeProvider {
  val pinCode: PinCode
}
