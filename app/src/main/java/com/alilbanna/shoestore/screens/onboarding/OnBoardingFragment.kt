package com.alilbanna.shoestore.screens.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alilbanna.shoestore.AllViewModel
import com.alilbanna.shoestore.R
import com.alilbanna.shoestore.databinding.OnboardingFragmentBinding

class OnBoardingFragment : Fragment() {
    private lateinit var binding: OnboardingFragmentBinding
    private lateinit var viewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.onboarding_fragment, container, false)

        initViewModel()
        initObservers()

        setBackPressedConfiguration()

        (activity as AppCompatActivity).supportActionBar?.hide()

        return binding.root
    }

    private fun setBackPressedConfiguration() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val i = Intent()
                i.action = Intent.ACTION_MAIN
                i.addCategory(Intent.CATEGORY_HOME)
                startActivity(i)
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(AllViewModel::class.java)

        binding.onboardingViewModel = viewModel
    }

    private fun initObservers() {
        viewModel.eventNextWelcomePress.observe(viewLifecycleOwner, {
            if (it) {
                goToInstruction()
                viewModel.goToInstructionComplete()
            }
        })
    }

    private fun goToInstruction() {
        findNavController().navigate(R.id.action_onBoarding_to_instructions1)
    }
}