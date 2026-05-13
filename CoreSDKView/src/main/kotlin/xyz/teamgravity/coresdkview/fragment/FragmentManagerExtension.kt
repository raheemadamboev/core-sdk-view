package xyz.teamgravity.coresdkview.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun <T : DialogFragment> FragmentManager.findDialog(tag: String): T? {
    @Suppress("UNCHECKED_CAST")
    return findFragmentByTag(tag) as? T
}