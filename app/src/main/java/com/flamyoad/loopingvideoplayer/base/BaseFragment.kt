package com.flamyoad.loopingvideoplayer.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.flamyoad.loopingvideoplayer.core.navigator.NavigationDispatcher
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var navigator: NavigationDispatcher

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}