package com.reddit.indicatorfastscroll

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.DimenRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.use

internal fun Context.getColorFromAttr(@AttrRes attrId: Int): Int {
  return ContextCompat.getColor(this, getResId(attrId))
}

internal fun Context.getColorStateListFromAttr(@AttrRes attrId: Int): ColorStateList {
  return ContextCompat.getColorStateList(this, getResId(attrId))!!
}

internal fun Context.getResId(@AttrRes attr: Int): Int {
  return theme.obtainStyledAttributes(intArrayOf(attr)).use {
    it.getResourceIdOrThrow(0)
  }
}

internal fun Context.getDimenPixelSize(@DimenRes dimen: Int): Int {
  return resources.getDimensionPixelSize(dimen)
}

internal fun View.throwIfMissingAttrs(@StyleRes styleRes: Int, block: () -> Unit) {
  try {
    block()
  } catch (e: IllegalArgumentException) {
    throw IllegalArgumentException(
        "This ${this::class.java.simpleName} is missing an attribute. " +
            "Add it to its style, or make the style inherit from " +
            "${resources.getResourceName(styleRes)}.",
        e
    )
  }
}
