package xyz.teamgravity.coresdkview.context

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.google.android.material.color.MaterialColors

/**
 * Resolves the given theme color attribute from this context.
 *
 * @param colorAttributeResId the color attribute to resolve.
 * @return the resolved color, or [Color.TRANSPARENT] if the attribute is not set.
 */
@ColorInt
fun Context.getColorAttribute(@AttrRes colorAttributeResId: Int): Int {
    return MaterialColors.getColor(this, colorAttributeResId, Color.TRANSPARENT)
}