package com.github.lifeforcode.pinvalidation.domain.event

internal fun interface IPinValidationEventHandler : (IPinValidationEvent) -> Unit
