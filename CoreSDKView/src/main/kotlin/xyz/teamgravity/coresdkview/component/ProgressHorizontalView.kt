package xyz.teamgravity.coresdkview.component

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ScaleDrawable
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import xyz.teamgravity.coresdkview.R

class ProgressHorizontalView : ProgressBar {

    private lateinit var progressBackgroundGradientDrawable: GradientDrawable
    private lateinit var progressGradientDrawable: GradientDrawable

    @ColorInt
    private var progressBackgroundColor: Int = Default.PROGRESS_BACKGROUND_COLOR

    @ColorInt
    private var progressColor: Int = Default.PROGRESS_COLOR

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        attrs(attrs, 0, 0)
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        attrs(attrs, defStyleAttr, 0)
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        attrs(attrs, defStyleAttr, defStyleRes)
        initialize()
    }

    private fun attrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        context.withStyledAttributes(attrs, R.styleable.ProgressHorizontalView, defStyleAttr, defStyleRes) {
            isIndeterminate = getBoolean(R.styleable.ProgressHorizontalView_phvIsIndeterminate, Default.IS_INDETERMINATE)
            max = getInt(R.styleable.ProgressHorizontalView_phvMax, Default.MAX)
            progressBackgroundColor = getColor(R.styleable.ProgressHorizontalView_phvProgressBackgroundColor, Default.PROGRESS_BACKGROUND_COLOR)
            progressColor = getColor(R.styleable.ProgressHorizontalView_phvProgressColor, Default.PROGRESS_COLOR)
        }
    }

    private fun initialize() {
        style()
    }

    private fun style() {
        val layerDrawable = ContextCompat.getDrawable(context, R.drawable.progress_bar_horizontal) as LayerDrawable
        progressBackgroundGradientDrawable = layerDrawable.findDrawableByLayerId(android.R.id.background) as GradientDrawable
        progressBackgroundGradientDrawable.setColor(progressBackgroundColor)
        val scaleDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress) as ScaleDrawable
        progressGradientDrawable = scaleDrawable.drawable as GradientDrawable
        progressGradientDrawable.setColor(progressColor)
        progressDrawable = layerDrawable
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setProgressBackgroundColor(@ColorInt color: Int) {
        progressBackgroundGradientDrawable.setColor(color)
    }

    fun setProgressColor(@ColorInt color: Int) {
        progressGradientDrawable.setColor(color)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Misc
    ///////////////////////////////////////////////////////////////////////////

    private object Default {
        const val IS_INDETERMINATE = false
        const val MAX = 100
        const val PROGRESS_BACKGROUND_COLOR = Color.TRANSPARENT
        const val PROGRESS_COLOR = Color.TRANSPARENT
    }
}