package com.example.magicthegathering.presentation.fragment.details_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.magicthegathering.data.di.CustomViewModelFactory
import com.example.magicthegathering.data.utils.CustomToast
import com.example.magicthegathering.databinding.DetailsScreenFragmentBinding
import com.example.magicthegathering.presentation.activity.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsScreenFragment : Fragment() {

    private lateinit var binding: DetailsScreenFragmentBinding

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel: DetailsScreenViewModel by viewModels { viewModelFactory }

    private var newsId : Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        unpackArguments()

        initViews()

        observe()

        viewModel.getNewsById(newsId)
    }

    private fun initViews(){
        binding.backIv.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun unpackArguments(){
        newsId = arguments?.getInt(NEWS_ID) ?: 0
    }

    private fun observe(){
        with(viewLifecycleOwner.lifecycleScope) {
            launch { observeResult() }
            launch { observeErrors() }
        }
    }

    private suspend fun observeResult(){
        viewModel.result.collect {
            with(binding){
                progress.isVisible = false
                detailsContainer.isVisible = true

                Glide.with(requireActivity())
                    .load(it.image)
                    .centerCrop()
                    .into(newsImageIv)

                siteTv.text = it.publisher
                dateTv.text = it.date
                titleTv.text = it.title
                descriptionTv.text = it.description
            }
        }
    }

    private suspend fun observeErrors(){
        viewModel.errors.collect {
            CustomToast.makeToast(requireContext(), getString(it.error))
        }
    }

    companion object {
        const val NEWS_ID = "NEWS_ID"
    }
}