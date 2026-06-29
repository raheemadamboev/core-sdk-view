package xyz.teamgravity.coresdkview.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMarginsRelative
import xyz.teamgravity.coresdkview.databinding.ViewImageTextInfoBinding

class ImageTextInfoView : LinearLayout {

    private lateinit var binding: ViewImageTextInfoBinding

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize()
    }

    private fun initialize() {
        configuration()
        inflate()
    }

    private fun configuration() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    private fun inflate() {
        binding = ViewImageTextInfoBinding.inflate(LayoutInflater.from(context), this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setMarginHorizontalPercent(@FloatRange(from = 0.0, to = 1.0) percent: Float) {
        post {
            val margin = (((parent as View).width * percent) / 2).toInt()
            updateLayoutParams<MarginLayoutParams> {
                updateMarginsRelative(
                    start = margin,
                    end = margin
                )
            }
        }
    }

    fun setImage(@DrawableRes icon: Int) {
        binding.imageI.setImageResource(icon)
    }

    fun setText(@StringRes text: Int) {
        binding.textT.setText(text)
    }

    fun setTextSize(size: Float) {
        binding.textT.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }
}