package com.example.innovecstesttask.presentation.fragment.start_screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.innovecstesttask.R
import com.example.innovecstesttask.data.di.CustomViewModelFactory
import com.example.innovecstesttask.databinding.StartScreenFragmentBinding
import com.example.innovecstesttask.domain.model.ui.start_screen_validation.ActionType
import com.example.innovecstesttask.presentation.activity.MainActivity
import com.example.innovecstesttask.utils.InternetConnectionManager
import com.example.innovecstesttask.utils.NotificationHelper
import com.example.innovecstesttask.utils.ViewUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class StartScreenFragment : Fragment() {

    private lateinit var binding: StartScreenFragmentBinding

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel: StartScreenViewModel by viewModels { viewModelFactory }
    private val calendar = Calendar.getInstance()

    private lateinit var rotateAnimation: RotateAnimation
    private lateinit var notificationHelper: NotificationHelper

    private val pickContact = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
    private var contactActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d(StartScreenFragment::class.simpleName, "$result")
    }

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

        viewModel.setCurrentDay(calendar.get(Calendar.DAY_OF_WEEK))
        notificationHelper = NotificationHelper(requireContext())

        initViews()

        initAnimation()

        observe()
    }

    private fun initViews() {
        with(binding) {
            buttonStart.setOnClickListener {
                viewModel.getAnimationData()
            }
        }
    }

    private fun initAnimation() {
        rotateAnimation =
            RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )

        rotateAnimation.duration = 1000
        rotateAnimation.interpolator = LinearInterpolator()
    }

    private fun observe() {
        with(viewLifecycleOwner.lifecycleScope) {
            launch { observeCurrentAction() }
            launch { observeErrors() }
        }
    }

    private suspend fun observeCurrentAction() {
        viewModel.result.collect {
            when (it) {
                ActionType.None -> {}
                ActionType.Animation -> binding.buttonStart.startAnimation(rotateAnimation)
                ActionType.ToastMessage -> ViewUtils.makeToast(
                    requireContext(),
                    getString(R.string.toast_action_message)
                )
                ActionType.Call -> contactActivityResultLauncher.launch(pickContact)
                ActionType.Notification -> notificationHelper.showNotification()
            }
        }
    }

    private suspend fun observeErrors() {
        viewModel.errors.collect {
            if(!InternetConnectionManager.isNetworkAvailable(requireContext())){
                ViewUtils.makeToast(requireContext(), getString(R.string.no_internet_connection_message))
            } else {
                ViewUtils.makeToast(requireContext(), getString(it.error))
            }
        }
    }
}