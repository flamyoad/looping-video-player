package com.flamyoad.loopingvideoplayer.ui.folder_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.flamyoad.loopingvideoplayer.R
import com.flamyoad.loopingvideoplayer.base.BaseFragment
import com.flamyoad.loopingvideoplayer.databinding.FragmentFolderListBinding
import com.flamyoad.loopingvideoplayer.model.Folder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FolderListFragment : BaseFragment(), FolderListAdapter.OnClickListener {

    private val viewModel: FolderListViewModel by viewModels()

    private lateinit var binding: FragmentFolderListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_folder_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.folderListRecyclerview) {
            setHasFixedSize(true)
            adapter = FolderListAdapter(this@FolderListFragment)
            layoutManager = LinearLayoutManager(this@FolderListFragment.requireContext())
        }
    }

    override fun onFolderClick(folder: Folder) = navigator.toVideoList(folder)
}