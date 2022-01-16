package com.flamyoad.loopingvideoplayer.ui.video_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flamyoad.loopingvideoplayer.R
import com.flamyoad.loopingvideoplayer.databinding.ListFolderItemBinding
import com.flamyoad.loopingvideoplayer.databinding.ListVideoItemBinding
import com.flamyoad.loopingvideoplayer.model.Folder
import com.flamyoad.loopingvideoplayer.model.Video

class VideoListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Video, VideoListAdapter.VideoViewHolder>(COMPARATOR) {

    interface OnClickListener {
        fun onVideoClick(video: Video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = DataBindingUtil.inflate<ListVideoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_video_item,
            parent,
            false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        if (position == RecyclerView.NO_POSITION) return
        with(holder.binding) {
            obj = getItem(position)
            listener = onClickListener
            executePendingBindings()
        }
    }

    inner class VideoViewHolder(val binding: ListVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Video>() {
            override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
                return oldItem == newItem
            }
        }
    }
}