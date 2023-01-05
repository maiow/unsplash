package com.example.unsplashattestation.presentation.photos.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.unsplashattestation.data.state.ClickableView
import com.example.unsplashattestation.data.state.LoadState
import com.example.unsplashattestation.databinding.FragmentPhotosBinding
import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.presentation.photos.list.adapter.PhotoPagingAdapter
import com.example.unsplashattestation.tools.BaseFragment
import com.example.unsplashattestation.tools.setChangeTextListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentPhotosBinding.inflate(inflater)

    private val viewModel by viewModels<PhotosViewModel>()

    private val adapter by lazy {
        PhotoPagingAdapter { buttonState, item ->
            onClick(buttonState, item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        loadStateItemsObserve()
        loadStateLike()
        settingAdapter()
        setSearchView()
        initRefresher()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPhoto().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun onClick(buttonState: ClickableView, item: Photo) {
        val action =
            PhotosFragmentDirections.actionNavigationPhotosToNavigationPhotoDetails(item.id)
        when (buttonState) {
            ClickableView.PHOTO -> findNavController().navigate(action)
            ClickableView.LIKE -> viewModel.like(item)
        }
    }

    private fun setSearchView() {
        val searchView = binding.searchBar.menu.getItem(0).actionView as SearchView
        searchView.setChangeTextListener { query ->
            viewModel.setQuery(query) { adapter.refresh() }
        }
    }

    private fun settingAdapter() {
        binding.photoRecycler.adapter = adapter
        binding.photoRecycler.itemAnimator?.changeDuration = 0
    }

    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                binding.error.isVisible =
                    loadState.mediator?.refresh is androidx.paging.LoadState.Error
                binding.recyclerProgressBar.isVisible =
                    loadState.mediator?.refresh is androidx.paging.LoadState.Loading
            }
        }
    }

    private fun loadStateLike() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadStateLike ->
                binding.error.isVisible = loadStateLike == LoadState.ERROR
            }
        }
    }

    private fun initRefresher() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.photoRecycler.isVisible = true
            adapter.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}
