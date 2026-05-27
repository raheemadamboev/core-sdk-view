package xyz.teamgravity.coresdkview.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import xyz.teamgravity.coresdkview.databinding.ViewMenuBinding

class MenuView : FrameLayout {

    private lateinit var binding: ViewMenuBinding

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
        inflate()
    }

    private fun inflate() {
        binding = ViewMenuBinding.inflate(LayoutInflater.from(context), this, true)
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setIcon(@DrawableRes icon: Int) {
        binding.iconI.setImageResource(icon)
    }

    fun setLabel(@StringRes label: Int) {
        binding.labelT.setText(label)
    }

    fun setOnClick(onClick: () -> Unit) {
        binding.root.setOnClickListener {
            onClick()
        }
    }
}