package xyz.teamgravity.coresdkview.fragment

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.view.ViewGroup
import android.view.Window
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment

fun DialogFragment.setDialogWidth() {
    val percent = 0.85F
    val metrics = Resources.getSystem().displayMetrics
    val rect = Rect(0, 0, metrics.widthPixels, metrics.heightPixels)
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

fun DialogFragment.setBackground() {
    dialog?.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
}