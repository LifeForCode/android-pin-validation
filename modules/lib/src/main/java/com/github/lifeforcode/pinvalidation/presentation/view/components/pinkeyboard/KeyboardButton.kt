package com.github.lifeforcode.pinvalidation.presentation.view.components.pinkeyboard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lifeforcode.pinvalidation.R
import com.github.lifeforcode.pinvalidation.common.pincode.Digit

@Composable
internal fun KeyboardButton(
  button: IKeyboardButton,
  onClick: (() -> Unit)? = null,
) {
  val modifier = Modifier
    .clip(CircleShape)
    .background(Transparent, CircleShape)
    .defaultMinSize(minWidth = 70.dp, minHeight = 70.dp)
    .maybeClickable(onClick)
  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center,
  ) {
    ButtonContent(button)
  }
}

@Composable
private fun ButtonContent(button: IKeyboardButton) {
  when (button) {
    is DigitButton -> DigitButtonContent(button.digit)
    is IconButton -> IconButtonContent(button.drawableResId)
    is ButtonStub -> Unit
  }
}

@Composable
private fun DigitButtonContent(digit: Digit) {
  Text(
    text = digit.toString(),
    fontSize = 26.sp,
    fontWeight = FontWeight.Normal,
    color = colorResource(R.color.keyboard_button),
  )
}

@Composable
private fun IconButtonContent(@DrawableRes drawableResId: Int) {
  Icon(
    painter = painterResource(id = drawableResId),
    contentDescription = "Clear",
    modifier = Modifier.size(26.dp),
    tint = colorResource(R.color.keyboard_button),
  )
}

private fun Modifier.maybeClickable(onClick: (() -> Unit)? = null) =
  if (onClick != null) this.clickable(onClick = onClick) else this
