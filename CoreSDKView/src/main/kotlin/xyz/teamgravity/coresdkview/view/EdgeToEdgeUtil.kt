package xyz.teamgravity.coresdkview.view

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.Px
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMarginsRelative

object EdgeToEdgeUtil {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setup(
        view: View,
        left: Boolean = true,
        @Px leftPaddingAddition: Int = 0,
        top: Boolean = true,
        @Px topPaddingAddition: Int = 0,
        right: Boolean = true,
        @Px rightPaddingAddition: Int = 0,
        bottom: Boolean = true,
        @Px bottomPaddingAddition: Int = 0,
        component: Component = Component.Padding
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { currentView, insets ->
            val padding =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or WindowInsetsCompat.Type.ime())

            when (component) {
                Component.Padding -> {
                    currentView.setPadding(
                        if (left) padding.left + leftPaddingAddition else currentView.paddingLeft,
                        if (top) padding.top + topPaddingAddition else currentView.paddingTop,
                        if (right) padding.right + rightPaddingAddition else currentView.paddingRight,
                        if (bottom) padding.bottom + bottomPaddingAddition else currentView.paddingBottom
                    )
                }

                Component.Margin -> {
                    currentView.updateLayoutParams<MarginLayoutParams> {
                        updateMarginsRelative(
                            start = if (left) padding.left + leftPaddingAddition else leftMargin,
                            top = if (top) padding.top + topPaddingAddition else topMargin,
                            end = if (right) padding.right + rightPaddingAddition else rightMargin,
                            bottom = if (bottom) padding.bottom + bottomPaddingAddition else bottomMargin
                        )
                    }
                }
            }

            return@setOnApplyWindowInsetsListener insets
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Misc
    ///////////////////////////////////////////////////////////////////////////

    enum class Component {
        Padding,
        Margin;
    }
}