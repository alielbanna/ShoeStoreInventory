package com.alilbanna.shoestore.screens.instructions

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
import com.alilbanna.shoestore.databinding.Instructions2FragmentBinding


class Instructions2Fragment : Fragment() {
    private lateinit var binding: Instructions2FragmentBinding
    private lateinit var viewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.instructions2_fragment, container, false)

        initViewModel()
        initObservers()

        (activity as AppCompatActivity).supportActionBar?.hide()

        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(AllViewModel::class.java)

        binding.instructionViewModel = viewModel
    }

    private fun initObservers() {
        viewModel.eventNextInstructionDetailPress.observe(viewLifecycleOwner, {
            if (it) {
                goToShoeList()
                viewModel.goToShoeListComplete()
            }
        })
    }

    private fun goToShoeList() {
        findNavController().navigate(R.id.action_instructions2_to_home)
    }
}