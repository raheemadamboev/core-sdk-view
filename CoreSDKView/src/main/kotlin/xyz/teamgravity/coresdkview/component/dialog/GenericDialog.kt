package xyz.teamgravity.coresdkview.component.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber
import xyz.teamgravity.coresdkview.databinding.DialogTwoButtonBinding
import xyz.teamgravity.coresdkview.fragment.setBackground
import xyz.teamgravity.coresdkview.fragment.setDialogWidth
import xyz.teamgravity.coresdkview.resources.ResourcesConst.ID_NULL
import xyz.teamgravity.coresdkview.view.gone
import xyz.teamgravity.coresdkview.view.visible

class GenericDialog : DialogFragment() {

    companion object {
        fun instance(
            @DrawableRes icon: Int? = null,
            title: String? = null,
            message: String? = null,
            positiveButton: String?,
            negativeButton: String?,
            dismissOnButtonClick: Boolean = true
        ): GenericDialog {
            val dialog = GenericDialog()
            val bundle = Bundle()
            if (icon != null) bundle.putInt(EXTRA_ICON, icon)
            if (title != null) bundle.putString(EXTRA_TITLE, title)
            if (message != null) bundle.putString(EXTRA_MESSAGE, message)
            if (positiveButton != null) bundle.putString(EXTRA_POSITIVE_BUTTON, positiveButton)
            if (negativeButton != null) bundle.putString(EXTRA_NEGATIVE_BUTTON, negativeButton)
            bundle.putBoolean(EXTRA_DISMISS_ON_BUTTON_CLICK, dismissOnButtonClick)
            dialog.arguments = bundle
            return dialog
        }

        const val EXTRA_ICON = "GenericDialog.extra.icon"
        const val EXTRA_TITLE = "GenericDialog.extra.title"
        const val EXTRA_MESSAGE = "GenericDialog.extra.message"
        const val EXTRA_POSITIVE_BUTTON = "GenericDialog.extra.positiveButton"
        const val EXTRA_NEGATIVE_BUTTON = "GenericDialog.extra.negativeButton"
        const val EXTRA_DISMISS_ON_BUTTON_CLICK = "GenericDialog.extra.dismissOnButtonClick"
    }

    private var _binding: DialogTwoButtonBinding? = null
    private val binding get() = _binding!!

    private var onPositiveClick: (() -> Unit)? = null
    private var onNegativeClick: (() -> Unit)? = null
    private var onDismissListener: (() -> Unit)? = null

    @DrawableRes
    private var extraIcon: Int = ID_NULL
    private var extraTitle: String? = null
    private var extraMessage: String? = null
    private var extraPositiveButton: String? = null
    private var extraNegativeButton: String? = null
    private var extraDismissOnButtonClick: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogTwoButtonBinding.inflate(inflater, container, false)
        setBackground()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui()
        button()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
    }

    override fun showNow(manager: FragmentManager, tag: String?) {
        try {
            super.showNow(manager, tag)
        } catch (e: IllegalStateException) {
            Timber.e(e)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun ui() {
        args()
        content()
    }

    private fun button() {
        onPositive()
        onNegative()
    }

    private fun args() {
        val args = requireArguments()
        extraIcon = args.getInt(EXTRA_ICON, ID_NULL)
        extraTitle = args.getString(EXTRA_TITLE)
        extraMessage = args.getString(EXTRA_MESSAGE)
        extraPositiveButton = args.getString(EXTRA_POSITIVE_BUTTON)
        extraNegativeButton = args.getString(EXTRA_NEGATIVE_BUTTON)
        extraDismissOnButtonClick = args.getBoolean(EXTRA_DISMISS_ON_BUTTON_CLICK)
    }

    private fun content() {
        binding.apply {
            setDialogWidth()

            if (extraIcon == ID_NULL) {
                iconI.gone()
            } else {
                iconI.setImageResource(extraIcon)
                iconI.visible()
            }

            titleT.text = extraTitle
            titleT.isVisible = extraTitle != null

            messageT.text = extraMessage
            messageT.isVisible = extraMessage != null

            positiveB.text = extraPositiveButton
            positiveB.isVisible = extraPositiveButton != null

            negativeB.text = extraNegativeButton
            negativeB.isVisible = extraNegativeButton != null
        }
    }

    private fun onPositive() {
        binding.positiveB.setOnClickListener {
            onPositiveClick?.invoke()
            if (extraDismissOnButtonClick && isAdded) dismissAllowingStateLoss()
        }
    }

    private fun onNegative() {
        binding.negativeB.setOnClickListener {
            onNegativeClick?.invoke()
            if (extraDismissOnButtonClick && isAdded) dismissAllowingStateLoss()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setOnPositiveClick(onPositiveClick: () -> Unit) {
        this.onPositiveClick = onPositiveClick
    }

    fun setOnNegativeClick(onNegativeClick: () -> Unit) {
        this.onNegativeClick = onNegativeClick
    }

    fun setOnDismissListener(onDismissListener: () -> Unit) {
        this.onDismissListener = onDismissListener
    }
}