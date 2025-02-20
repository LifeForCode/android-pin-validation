package com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.github.lifeforcode.pinvalidation.R
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator.PinIndicatorState.Disabled
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator.PinIndicatorState.Enabled
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator.PinIndicatorState.EnabledOnFailed
import com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader.indicator.PinIndicatorState.EnabledOnSuccess

@Composable
internal fun PinIndicator(state: PinIndicatorState) {
  Box(
    modifier = Modifier
      .padding(10.dp)
      .size(15.dp)
      .clip(CircleShape)
      .border(getBorderStroke(state), CircleShape)
      .background(getBackgroundColor(state), CircleShape),
  )
}

@Composable
private fun getBorderStroke(state: PinIndicatorState) =
  if (state.isDisabled)
    BorderStroke(1.dp, colorResource(R.color.pin_border_stroke))
  else
    BorderStroke(0.dp, Transparent)

@Composable
private fun getBackgroundColor(state: PinIndicatorState) =
  when (state) {
    Disabled -> Transparent
    Enabled -> colorResource(R.color.pin_indicator_enabled)
    EnabledOnSuccess -> colorResource(R.color.pin_indicator_success)
    EnabledOnFailed -> colorResource(R.color.pin_indicator_failed)
  }
