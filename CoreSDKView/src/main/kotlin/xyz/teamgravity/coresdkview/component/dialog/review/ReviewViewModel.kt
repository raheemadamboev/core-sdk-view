package xyz.teamgravity.coresdkview.component.dialog.review

import android.app.Activity
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import xyz.teamgravity.coresdkandroid.review.ReviewManager
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val review: ReviewManager
) : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onDeny() {
        review.deny()
    }

    fun onRemindLater() {
        review.remindLater()
    }

    fun onReview(activity: Activity?) {
        if (activity == null) {
            Timber.e("onReview(): activity is null! Aborted the operation.")
            return
        }

        review.review(activity)
    }
}