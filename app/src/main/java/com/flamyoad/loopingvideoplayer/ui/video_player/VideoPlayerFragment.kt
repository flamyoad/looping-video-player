package com.flamyoad.loopingvideoplayer.ui.video_player

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.flamyoad.loopingvideoplayer.R
import com.flamyoad.loopingvideoplayer.databinding.FragmentVideoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment : Fragment() {

    private lateinit var binding: FragmentVideoPlayerBinding

    private val args: VideoPlayerFragmentArgs by navArgs()

    private val viewModel: VideoPlayerViewModel by viewModels()

    private var _player: ExoPlayer? = null
    private val player get() = requireNotNull(_player)

    private var playbackPosition: Long = 0
    private var playWhenReady = true
    private var currentWindow = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_video_player, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initPlayer() {
        _player = ExoPlayer.Builder(requireContext()).build().also {
            binding.playerView.player = it
            it.playWhenReady = true
            it.repeatMode = Player.REPEAT_MODE_ONE
            it.seekTo(playbackPosition)
            it.setMediaItem(MediaItem.fromUri(args.videoUri))
            it.prepare()
        }
    }

    private fun releasePlayer() {
        _player?.let {
            playWhenReady = it.playWhenReady
            it.release()
        }
        _player = null
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= 24) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT < 24 || _player == null) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= 24) {
            releasePlayer()
        }
    }
}