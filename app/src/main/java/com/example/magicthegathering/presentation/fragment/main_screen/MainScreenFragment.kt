package com.example.magicthegathering.presentation.fragment.main_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magicthegathering.data.di.CustomViewModelFactory
import com.example.magicthegathering.utils.ViewUtils
import com.example.magicthegathering.databinding.FragmentMainScreenBinding
import com.example.magicthegathering.domain.callback.OnItemEventMainScreen
import com.example.magicthegathering.presentation.activity.MainActivity
import com.example.magicthegathering.presentation.fragment.main_screen.adapter.CardListRecyclerViewAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainScreenFragment : Fragment(), OnItemEventMainScreen {

    private lateinit var binding : FragmentMainScreenBinding
    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel : MainScreenViewModel by viewModels { viewModelFactory }

    private val adapter = CardListRecyclerViewAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()

        initViews()

        observe()
    }

    private fun initViews(){
        with(binding){
            backIv.setOnClickListener {
                requireActivity().onBackPressed()
            }

            val linearLayoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            cardListRv.layoutManager = linearLayoutManager
            cardListRv.adapter = adapter
        }
    }

    private fun observe(){
        with(viewLifecycleOwner.lifecycleScope) {
            launch { observeRecyclerViewItems() }
            launch { observeErrors() }
        }
    }

    private suspend fun observeRecyclerViewItems(){
        viewModel.result.collect {
            adapter.setList(it)
            binding.progress.isVisible = false
        }
    }

    private suspend fun observeErrors(){
        viewModel.errors.collect {
            ViewUtils.makeToast(requireContext(), getString(it.error))
        }
    }

    override fun onItemClicked(id: Int) {
        viewModel.navigateToNewsDetails(id)
    }
}