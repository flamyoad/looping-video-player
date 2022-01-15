package com.flamyoad.loopingvideoplayer.ui.video_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.flamyoad.loopingvideoplayer.R
import com.flamyoad.loopingvideoplayer.databinding.FragmentVideoListBinding
import com.flamyoad.loopingvideoplayer.model.Folder
import com.flamyoad.loopingvideoplayer.ui.folder_list.FolderListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoListFragment : Fragment(), VideoListAdapter.OnClickListener {

    private val viewModel: VideoListViewModel by viewModels()

    private lateinit var binding: FragmentVideoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.videoListRecyclerview) {
            setHasFixedSize(true)
            adapter = VideoListAdapter(this@VideoListFragment)
            layoutManager = LinearLayoutManager(this@VideoListFragment.requireContext())
        }
    }

    override fun onVideoClick(folder: Folder) {

    }
}