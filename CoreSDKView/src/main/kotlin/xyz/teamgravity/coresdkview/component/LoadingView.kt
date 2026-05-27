package xyz.teamgravity.coresdkview.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.view.isVisible
import xyz.teamgravity.coresdkview.R
import xyz.teamgravity.coresdkview.databinding.ViewLoadingBinding

class LoadingView : FrameLayout {

    private lateinit var binding: ViewLoadingBinding

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
        style()
    }

    private fun inflate() {
        binding = ViewLoadingBinding.inflate(LayoutInflater.from(context), this)
    }

    private fun style() {
        setBackgroundResource(R.color.background_loading)
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun set(
        window: Window,
        loading: Boolean
    ) {
        isVisible = loading
        if (loading) window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}