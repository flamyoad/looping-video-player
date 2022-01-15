package com.flamyoad.loopingvideoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flamyoad.loopingvideoplayer.base.BaseActivity
import com.flamyoad.loopingvideoplayer.ui.folder_list.FolderListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = FolderListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }
}