package xyz.teamgravity.coresdkview.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.onBackPressed() {
    activity?.onBackPressedDispatcher?.onBackPressed() ?: findNavController().navigateUp()
}