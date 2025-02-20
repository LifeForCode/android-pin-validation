package com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator

internal enum class PinIndicatorState {
  Disabled,
  Enabled,
  EnabledOnSuccess,
  EnabledOnFailed;

  val isDisabled get() = this == Disabled
}
