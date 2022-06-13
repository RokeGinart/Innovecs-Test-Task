package com.example.magicthegathering.presentation.fragment.start_screen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.magicthegathering.data.di.CustomViewModelFactory
import com.example.magicthegathering.databinding.StartScreenFragmentBinding
import com.example.magicthegathering.domain.callback.OnDatePickerListener
import com.example.magicthegathering.domain.model.ui.DateModel
import com.example.magicthegathering.presentation.activity.MainActivity
import com.example.magicthegathering.presentation.dialog.date_picker.DatePicker
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class StartScreenFragment : Fragment(), OnDatePickerListener {

    private lateinit var binding : StartScreenFragmentBinding

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val dateDialog = DatePicker()
    private var date: DateModel? = null

    private val viewModel: StartScreenViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateDialog.setParentFragmentListener(this)

        initViews()

        observe()
    }

    private fun initViews(){
        with(binding){
            buttonNext.setOnClickListener {
                viewModel.validateFields(
                    name = nameEd.text.toString(),
                    surname = surnameEd.text.toString(),
                    email = emailEd.text.toString(),
                    date = dateEd.text.toString(),
                )
            }

            dateEd.setOnClickListener {
                dateDialog.setDate(date)
                dateDialog.show(childFragmentManager, "DatePickerDialog")
            }

            nameEd.doAfterTextChanged {
                nameTil.error = null
            }

            surnameEd.doAfterTextChanged {
                surnameTil.error = null
            }

            emailEd.doAfterTextChanged {
                emailTil.error = null
            }
        }
    }

    private fun observe() {
        with(viewLifecycleOwner.lifecycleScope) {
            launch { observeRecyclerViewItems() }
        }
    }

    private suspend fun observeRecyclerViewItems() {
        viewModel.fieldErrors.collect { validation ->
            with(binding){
                nameTil.error = validation.nameError?.let { getString(it.error)}
                surnameTil.error = validation.surnameError?.let { getString(it.error)}
                emailTil.error = validation.emailError?.let { getString(it.error)}
                dateTil.error = validation.dateError?.let { getString(it.error)}
            }
        }
    }


    @SuppressLint("SetTextI18n")
    override fun dateSelected(date : DateModel) {
        this.date = date
        binding.dateEd.setText("${date.month}/${date.day}/${date.year}")
        binding.dateTil.error = null
    }
}