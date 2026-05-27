package xyz.teamgravity.coresdkview.component.dialog.update.available

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import xyz.teamgravity.coresdkandroid.update.UpdateManager
import javax.inject.Inject

@HiltViewModel
class UpdateAvailableViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {

    private val _forced = MutableStateFlow(getForced())
    val forced: StateFlow<Boolean> = _forced.asStateFlow()

    private fun getForced(): Boolean {
        val type = handle.get<UpdateManager.Type>(UpdateAvailableDialog.EXTRA_TYPE)
            ?: throw IllegalStateException("getType(): type is null. Type must be passed to the dialog.")

        if (type == UpdateManager.Type.None) {
            throw IllegalArgumentException("getType(): type is None. It mustn't be None for the dialog.")
        }

        return type == UpdateManager.Type.Forced
    }
}