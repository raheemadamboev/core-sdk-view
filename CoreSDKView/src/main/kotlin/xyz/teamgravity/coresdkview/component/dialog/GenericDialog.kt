package xyz.teamgravity.coresdkview.component.dialog

import android.content.DialogInterface
import android.content.res.Resources
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
import xyz.teamgravity.coresdkview.view.gone
import xyz.teamgravity.coresdkview.view.visible

class GenericDialog : DialogFragment() {

    companion object {
        fun instance(
            @DrawableRes icon: Int? = null,
            title: String? = null,
            message: String? = null,
            positiveButton: String?,
            negativeButton: String?
        ): GenericDialog {
            val dialog = GenericDialog()
            val bundle = Bundle()
            if (icon != null) bundle.putInt(EXTRA_ICON, icon)
            if (title != null) bundle.putString(EXTRA_TITLE, title)
            if (message != null) bundle.putString(EXTRA_MESSAGE, message)
            if (positiveButton != null) bundle.putString(EXTRA_POSITIVE_BUTTON, positiveButton)
            if (negativeButton != null) bundle.putString(EXTRA_NEGATIVE_BUTTON, negativeButton)
            dialog.arguments = bundle
            return dialog
        }

        const val EXTRA_ICON = "GenericDialog.extra.icon"
        const val EXTRA_TITLE = "GenericDialog.extra.title"
        const val EXTRA_MESSAGE = "GenericDialog.extra.message"
        const val EXTRA_POSITIVE_BUTTON = "GenericDialog.extra.positiveButton"
        const val EXTRA_NEGATIVE_BUTTON = "GenericDialog.extra.negativeButton"
    }

    private var _binding: DialogTwoButtonBinding? = null
    private val binding get() = _binding!!

    private var onPositiveClick: (() -> Unit)? = null
    private var onNegativeClick: (() -> Unit)? = null
    private var onDismissListener: (() -> Unit)? = null

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
        content()
    }

    private fun button() {
        onPositive()
        onNegative()
    }

    private fun content() {
        binding.apply {
            setDialogWidth()

            val args = requireArguments()

            val icon = args.getInt(EXTRA_ICON, Resources.ID_NULL)
            if (icon == Resources.ID_NULL) {
                iconI.gone()
            } else {
                iconI.setImageResource(icon)
                iconI.visible()
            }

            val title = args.getString(EXTRA_TITLE)
            titleT.text = title
            titleT.isVisible = title != null

            val message = args.getString(EXTRA_MESSAGE)
            messageT.text = message
            messageT.isVisible = message != null

            val positiveButton = args.getString(EXTRA_POSITIVE_BUTTON)
            positiveB.text = positiveButton
            positiveB.isVisible = positiveButton != null

            val negativeButton = args.getString(EXTRA_NEGATIVE_BUTTON)
            negativeB.text = negativeButton
            negativeB.isVisible = negativeButton != null
        }
    }

    private fun onPositive() {
        binding.positiveB.setOnClickListener {
            onPositiveClick?.invoke()
        }
    }

    private fun onNegative() {
        binding.negativeB.setOnClickListener {
            onNegativeClick?.invoke()
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