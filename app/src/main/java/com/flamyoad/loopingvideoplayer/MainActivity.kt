package com.flamyoad.loopingvideoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flamyoad.loopingvideoplayer.ui.VideoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = VideoListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }
}