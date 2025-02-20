package com.github.lifeforcode.pinvalidation.presentation.view.components.pinheader

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.github.lifeforcode.pinvalidation.R

@Composable
internal fun PinEntryText(text: String = stringResource(R.string.pin_entry_not_ready)) {
  Text(
    text = text,
    color = colorResource(R.color.pin_entry_text),
    fontSize = 16.sp,
    textAlign = TextAlign.Center,
  )
}
