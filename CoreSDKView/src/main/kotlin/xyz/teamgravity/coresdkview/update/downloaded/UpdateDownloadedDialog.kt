package xyz.teamgravity.coresdkview.update.downloaded

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber
import xyz.teamgravity.coresdkview.R
import xyz.teamgravity.coresdkview.databinding.DialogTwoButtonBinding
import xyz.teamgravity.coresdkview.fragment.setBackground
import xyz.teamgravity.coresdkview.fragment.setDialogWidth
import xyz.teamgravity.coresdkview.view.visible

class UpdateDownloadedDialog : DialogFragment() {

    companion object {
        fun instance(): UpdateDownloadedDialog {
            return UpdateDownloadedDialog()
        }
    }

    private var _binding: DialogTwoButtonBinding? = null
    private val binding get() = _binding!!

    private var onConfirmClick: (() -> Unit)? = null

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

    override fun showNow(manager: FragmentManager, tag: String?) {
        try {
            super.showNow(manager, tag)
        } catch (e: IllegalStateException) {
            Timber.e(e)
        }
    }

    private fun ui() {
        content()
    }

    private fun button() {
        onLater()
        onConfirm()
    }

    private fun content() {
        binding.apply {
            setDialogWidth()
            dialog?.setCanceledOnTouchOutside(false)
            iconI.setImageResource(R.drawable.ic_check_circle)
            titleT.setText(R.string.update_downloaded_title)
            messageT.setText(R.string.update_downloaded_text)
            negativeB.setText(R.string.update_downloaded_dismiss_button)
            negativeB.visible()
            positiveB.setText(R.string.update_downloaded_confirm_button)
        }
    }

    private fun onLater() {
        binding.negativeB.setOnClickListener {
            if (isAdded) dismissAllowingStateLoss()
        }
    }

    private fun onConfirm() {
        binding.positiveB.setOnClickListener {
            if (isAdded) dismissAllowingStateLoss()
            onConfirmClick?.invoke()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun setOnConfirmClick(onConfirmClick: () -> Unit) {
        this.onConfirmClick = onConfirmClick
    }
}