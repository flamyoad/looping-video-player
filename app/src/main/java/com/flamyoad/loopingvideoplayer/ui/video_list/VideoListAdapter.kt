package com.flamyoad.loopingvideoplayer.ui.folder_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flamyoad.loopingvideoplayer.R
import com.flamyoad.loopingvideoplayer.databinding.ListFolderItemBinding
import com.flamyoad.loopingvideoplayer.model.Folder

class FolderListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Folder, FolderListAdapter.FolderViewHolder>(COMPARATOR) {

    interface OnClickListener {
        fun onFolderClick(folder: Folder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val binding = DataBindingUtil.inflate<ListFolderItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_folder_item,
            parent,
            false
        )
        return FolderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        if (position == RecyclerView.NO_POSITION) return
        with(holder.binding) {
            obj = getItem(position)
            listener = onClickListener
            executePendingBindings()
        }
    }

    inner class FolderViewHolder(val binding: ListFolderItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Folder>() {
            override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
                return oldItem == newItem
            }
        }
    }
}