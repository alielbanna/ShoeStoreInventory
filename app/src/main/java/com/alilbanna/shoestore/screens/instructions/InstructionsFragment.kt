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
import com.alilbanna.shoestore.databinding.InstructionsFragmentBinding

class InstructionsFragment : Fragment() {

    private lateinit var binding: InstructionsFragmentBinding

    private lateinit var instructionsViewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)

        initViewModel()
        initObservers()

        (activity as AppCompatActivity).supportActionBar?.hide()

        return binding.root
    }

    private fun initViewModel() {
        instructionsViewModel = ViewModelProvider(requireActivity()).get(AllViewModel::class.java)

        binding.instructionViewModel = instructionsViewModel
    }

    private fun initObservers() {
        instructionsViewModel.eventNextInstructionPress.observe(viewLifecycleOwner) {
            if (it) {
                goToInstruction2()
                instructionsViewModel.goToInstructionDetailComplete()
            }
        }
    }

    private fun goToInstruction2() {
        findNavController().navigate(R.id.action_instructions1_to_instructions2)
    }
}