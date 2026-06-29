package xyz.teamgravity.coresdkview.view

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import xyz.teamgravity.coresdkview.R

/**
 * Makes view visible.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Makes view invisible.
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Makes view gone.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Makes view fade in.
 */
fun View.fadeIn() {
    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
    this.animation = animation
    visible()
}

/**
 * Makes view fade out.
 */
fun View.fadeOut() {
    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
    animation.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) = gone()

            override fun onAnimationRepeat(animation: Animation?) = Unit
        }
    )
    startAnimation(animation)
}

/**
 * Makes view slide up.
 */
fun View.slideUp() {
    val animation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
    this.animation = animation
    visible()
}

/**
 * Clears error and focus of TextInputLayout.
 */
fun TextInputLayout.clearErrorFocus() {
    error = null
    clearFocus()
}

/**
 * Gets trimmed text of TextInputLayout.
 */
fun TextInputLayout.text(): String {
    return editText?.text.toString().trim()
}

/**
 * Sets text of TextInputLayout.
 */
fun TextInputLayout.setText(text: String?) {
    editText?.setText(text)
}

/**
 * Sets error and requests focus to TextInputLayout.
 */
fun TextInputLayout.error(error: String) {
    this.error = error
    requestFocus()
}

/**
 * Submits data to adapter and animates RecyclerView if first time.
 */
fun <T> ListAdapter<T, *>.submitListWithAnimation(
    data: List<T>,
    recyclerview: RecyclerView,
    @AnimRes animation: Int,
    animated: Boolean,
    onAnimated: () -> Unit
) {
    submitList(data) {
        if (!animated && data.isNotEmpty()) {
            recyclerview.doOnPreDraw {
                recyclerview.layoutAnimation = AnimationUtils.loadLayoutAnimation(recyclerview.context, animation)
                recyclerview.scheduleLayoutAnimation()
                onAnimated()
                recyclerview.post {
                    recyclerview.layoutAnimation = null
                }
            }
        }
    }
}