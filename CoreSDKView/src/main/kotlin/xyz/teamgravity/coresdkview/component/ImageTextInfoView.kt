package xyz.teamgravity.coresdkview.component

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.view.updatePadding
import xyz.teamgravity.coresdkandroid.android.SizeUtil
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

        val horizontalPadding = SizeUtil.dpToPx(16)
        updatePadding(
            left = horizontalPadding,
            right = horizontalPadding
        )
    }

    private fun inflate() {
        binding = ViewImageTextInfoBinding.inflate(LayoutInflater.from(context), this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setIcon(@DrawableRes icon: Int) {
        binding.iconI.setImageResource(icon)
    }

    fun setText(@StringRes text: Int) {
        binding.textT.setText(text)
    }
}