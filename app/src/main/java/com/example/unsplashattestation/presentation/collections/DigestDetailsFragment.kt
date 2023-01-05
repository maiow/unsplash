package com.example.unsplashattestation.presentation.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.unsplashattestation.R
import com.example.unsplashattestation.data.state.ClickableView
import com.example.unsplashattestation.data.state.LoadState
import com.example.unsplashattestation.databinding.FragmentDigestDetailsBinding
import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.presentation.photos.list.adapter.PhotoPagingAdapter
import com.example.unsplashattestation.tools.BaseFragment
import com.example.unsplashattestation.tools.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DigestDetailsFragment : BaseFragment<FragmentDigestDetailsBinding>() {

    private val viewModel by viewModels<DigestDetailsViewModel>()

    private val adapter by lazy {
        PhotoPagingAdapter { buttonState, item ->
            onClick(buttonState, item)
        }
    }

    private val args by navArgs<DigestDetailsFragmentArgs>()

    override fun initBinding(inflater: LayoutInflater) =
        FragmentDigestDetailsBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        getLoadingState()
        loadStateItemsObserve()
        loadStateLike()
        settingAdapter()
        initRefresher()
    }

    /** Обсервер чего? из названия должно быть понятно*/

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            /** не очень понимаю зачем вам обращатся к вью модели в корутине
             * не совсем правильно
             * это обсержер он подписывается на делает какие то операции с вью моделью, который еще
             * ни как на сам обсервер не влияют*/
            viewModel.setId(args.id) { adapter.refresh() }
            viewModel.getPhoto().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    /** это лоад стайт обсервер и в название лучше так и писать loadStateObserver */
    private fun getLoadingState() {
        /** смотри коммент выше*/
        viewModel.getDigestInfo(args.id)
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.loadState.collect { loadState -> updateUiOnServerResponse(loadState) }
            }
    }

    /**почему не вен с веткой else?*/
    private fun updateUiOnServerResponse(loadState: LoadState) {
        if (loadState == LoadState.ERROR) {
            binding.error.isVisible = true
        }
        if (loadState == LoadState.SUCCESS) {
            /** вот тут тоже обсервер который соит вынести в отдельную функцию*/
            viewLifecycleOwner.lifecycleScope
                .launchWhenStarted {
                    viewModel.state
                        .collect { state -> showInfo(state) }
                }
        }
    }
/**очень странное разбиение на функции...ты вызваешь эту функцию один раз,в нутри коррутины
 * почему бы все что находтся в функции не обернуть в корутину и не вызвать в одну строчку выше?
 * и опять такиж (это лично мое) для таких целей не очень люблю салед классы говорил почему
 * как миними две вещи появляется is в when e (это просто не нравится ) во вторых получается наругение
 * одного из принципов типо одна функция одно действие...а тут обсервер одновременно и на стейт и на данные*/
    private fun showInfo(state: DigestState) {
        when (state) {
            DigestState.NotStartedYet ->
                binding.toolProgressBar.visibility = View.VISIBLE
            is DigestState.Success -> {
                binding.toolProgressBar.visibility = View.GONE
                binding.collapsingToolbarLayout.title = state.data.title
                binding.digestTitle.text = state.data.title
                binding.description.text = state.data.description
                binding.tags.text = state.data.tags.joinToString { tag ->
                    "#${tag.title}"
                }
                binding.data.text =
                    resources.getQuantityString(
                        R.plurals.digest_data,
                        state.data.totalPhotos,
                        state.data.totalPhotos,
                        state.data.userUsername
                    )
                binding.preview.loadImage(state.data.previewPhoto)
            }
        }
    }

    /** зачем тут переменная? Directions сразу кидаем в навкотроллер */
    private fun onClick(buttonState: ClickableView, item: Photo) {
        val action =
            DigestDetailsFragmentDirections.actionDigestDetailsFragmentToNavigationPhotoDetails(item.id)
        when (buttonState) {
            ClickableView.PHOTO ->
                findNavController().navigate(action)
            ClickableView.LIKE -> viewModel.like(item)
        }
    }

    private fun settingAdapter() {
        binding.photoRecycler.adapter = adapter
        binding.photoRecycler.itemAnimator?.changeDuration = 0
    }

    private fun loadStateItemsObserve() {
        adapter.addLoadStateListener { loadState ->
            binding.error.isVisible =
                loadState.mediator?.refresh is androidx.paging.LoadState.Error
            binding.recyclerProgressBar.isVisible =
                loadState.mediator?.refresh is androidx.paging.LoadState.Loading
        }
    }

    /** неочень понимаю зачем еще один обсервер на лайки?*/
    private fun loadStateLike() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadStateLike ->
                binding.error.isVisible =
                    loadStateLike == LoadState.ERROR
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
