package com.flamyoad.loopingvideoplayer.ui.video_list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flamyoad.loopingvideoplayer.model.Video
import com.flamyoad.loopingvideoplayer.ui.folder_list.FolderListAdapter

@BindingAdapter("videoList")
fun bindRecyclerView(recyclerView: RecyclerView, list: List<Video>?) {
    val adapter = recyclerView.adapter as VideoListAdapter
    adapter.submitList(list)
}

//@BindingAdapter("image")
//fun bindImage(recyclerView: RecyclerView) {
//
//}