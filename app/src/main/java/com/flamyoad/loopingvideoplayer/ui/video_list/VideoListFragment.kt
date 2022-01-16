package com.flamyoad.loopingvideoplayer.ui.video_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flamyoad.loopingvideoplayer.R
import com.flamyoad.loopingvideoplayer.base.BaseFragment
import com.flamyoad.loopingvideoplayer.databinding.FragmentVideoListBinding
import com.flamyoad.loopingvideoplayer.model.Video
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoListFragment : BaseFragment(), VideoListAdapter.OnClickListener {

    private val viewModel: VideoListViewModel by viewModels()

    private lateinit var binding: FragmentVideoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
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

    override fun onVideoClick(video: Video) {
        findNavController().navigate(
            VideoListFragmentDirections.actionVideoListFragmentToVideoPlayerFragment(
                videoUri = video.videoUri
            )
        )
    }
}