package xyz.teamgravity.coresdkview.component.dialog.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import xyz.teamgravity.coresdkview.R
import xyz.teamgravity.coresdkview.databinding.DialogThreeButtonBinding
import xyz.teamgravity.coresdkview.fragment.setBackground
import xyz.teamgravity.coresdkview.fragment.setDialogWidth

@AndroidEntryPoint
class ReviewDialog : DialogFragment() {

    companion object {
        fun instance(): ReviewDialog {
            return ReviewDialog()
        }
    }

    private var _binding: DialogThreeButtonBinding? = null
    private val binding get() = _binding!!

    private val viewmodel by viewModels<ReviewViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogThreeButtonBinding.inflate(inflater, container, false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun ui() {
        content()
    }

    private fun button() {
        onDeny()
        onRemindLater()
        onReview()
    }

    private fun content() {
        binding.apply {
            setDialogWidth()
            iconI.setImageResource(R.drawable.ic_favorite)
            titleT.setText(R.string.review_title)
            messageT.setText(R.string.review_text)
            oneB.setText(R.string.review_dismiss_button)
            twoB.setText(R.string.review_neutral_button)
            threeB.setText(R.string.review_confirm_button)
        }
    }

    private fun onDeny() {
        binding.oneB.setOnClickListener {
            if (isAdded) dismissAllowingStateLoss()
            viewmodel.onDeny()
        }
    }

    private fun onRemindLater() {
        binding.twoB.setOnClickListener {
            if (isAdded) dismissAllowingStateLoss()
            viewmodel.onRemindLater()
        }
    }

    private fun onReview() {
        binding.threeB.setOnClickListener {
            if (isAdded) dismissAllowingStateLoss()
            viewmodel.onReview(activity)
        }
    }
}