package com.alilbanna.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alilbanna.shoestore.AllViewModel
import com.alilbanna.shoestore.R
import com.alilbanna.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        initViewModel()
        initObservers()

        (activity as AppCompatActivity).supportActionBar?.hide()

        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(AllViewModel::class.java)

        binding.loginViewModel = viewModel
    }

    private fun initObservers() {
        viewModel.eventLoginMade.observe(viewLifecycleOwner, {
            if (it) {
                goToOnBoarding()
                viewModel.goToWelcomeComplete()
            }
        })
    }

    private fun goToOnBoarding() {
        findNavController().navigate(LoginFragmentDirections.actionLoginToOnboarding())
    }
}