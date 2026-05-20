package xyz.teamgravity.coresdkview.update.available

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import xyz.teamgravity.coresdkandroid.update.UpdateManager
import xyz.teamgravity.coresdkview.R
import xyz.teamgravity.coresdkview.databinding.DialogTwoButtonBinding
import xyz.teamgravity.coresdkview.fragment.setBackground
import xyz.teamgravity.coresdkview.fragment.setDialogWidth

@AndroidEntryPoint
class UpdateAvailableDialog : DialogFragment() {

    companion object {
        fun instance(type: UpdateManager.Type? = null): UpdateAvailableDialog {
            val dialog = UpdateAvailableDialog()
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_TYPE, type)
            dialog.arguments = bundle
            return dialog
        }

        const val EXTRA_TYPE = "UpdateAvailableDialog.extra.type"
    }

    private var _binding: DialogTwoButtonBinding? = null
    private val binding get() = _binding!!

    private val viewmodel by viewModels<UpdateAvailableViewModel>()

    private var onConfirmClick: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogTwoButtonBinding.inflate(inflater, container, false)
        setBackground()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui()
        observe()
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

    private fun observe() {
        observeForced()
    }

    private fun button() {
        onDeny()
        onConfirm()
    }

    private fun content() {
        binding.apply {
            setDialogWidth()
            iconI.setImageResource(R.drawable.ic_notification)
            negativeB.setText(R.string.update_available_dismiss_button)
            positiveB.setText(R.string.update_available_confirm_button)
        }
    }

    private fun handleForced(forced: Boolean) {
        binding.apply {
            isCancelable = !forced
            titleT.setText(if (forced) R.string.update_available_title_forced else R.string.update_available_title_optional)
            messageT.setText(if (forced) R.string.update_available_text_forced else R.string.update_available_text_optional)
            negativeB.isVisible = !forced
        }
    }

    private fun observeForced() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.forced.collectLatest { forced ->
                handleForced(forced)
            }
        }
    }

    private fun onDeny() {
        binding.negativeB.setOnClickListener {
            if (isAdded) dismissAllowingStateLoss()
        }
    }

    private fun onConfirm() {
        binding.positiveB.setOnClickListener {
            if (!viewmodel.forced.value && isAdded) dismissAllowingStateLoss()
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