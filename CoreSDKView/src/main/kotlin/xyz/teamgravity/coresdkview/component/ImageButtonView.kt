package xyz.teamgravity.coresdkview.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import xyz.teamgravity.coresdkview.R

class ImageButtonView : AppCompatImageView {

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        style()
    }

    private fun style() {
        applyRipple()
    }

    private fun applyRipple() {
        val states = arrayOf(intArrayOf(android.R.attr.state_pressed))
        val colors = intArrayOf(ContextCompat.getColor(context, R.color.background_ripple))
        val state = ColorStateList(states, colors)
        val ripple = RippleDrawable(state, background, null)
        background = ripple
    }
}