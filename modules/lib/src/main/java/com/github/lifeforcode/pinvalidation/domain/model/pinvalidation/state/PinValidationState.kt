package com.github.lifeforcode.pinvalidation.domain.model.pinvalidation.state

import androidx.annotation.StringRes
import com.github.lifeforcode.pinvalidation.R

internal enum class PinValidationState(@StringRes val textResId: Int) {
  NotReady(R.string.pin_entry_not_ready),
  Ready(R.string.pin_entry_ready),
  Success(R.string.pin_entry_success),
  Failed(R.string.pin_entry_failed);

  val isReady get() = this == Ready
  val isSuccess get() = this == Success
  val isFailed get() = this == Failed
}
