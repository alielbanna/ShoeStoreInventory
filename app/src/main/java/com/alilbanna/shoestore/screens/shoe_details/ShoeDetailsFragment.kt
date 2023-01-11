package com.alilbanna.shoestore.screens.shoe_details

import android.app.AlertDialog
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
import com.alilbanna.shoestore.databinding.ShoeDetailsFragmentBinding


class ShoeDetailsFragment : Fragment() {
    private lateinit var binding: ShoeDetailsFragmentBinding
    private lateinit var viewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_details_fragment, container, false)

        initViewModel()
        initObservers()

        changePictureShoeDetailPress("model1")
        (activity as AppCompatActivity).supportActionBar?.show()

        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(AllViewModel::class.java)

        binding.shoeDetailViewModel = viewModel
        binding.shoeModel = viewModel.shoe
    }

    private fun initObservers() {
        viewModel.eventSaveShoeDetailPress.observe(viewLifecycleOwner, {
            if (it) {
                saveShoeDetail()
                viewModel.saveShoeDetailComplete()
            }
        })

        viewModel.eventCancelShoeDetailPress.observe(viewLifecycleOwner, {
            if (it) {
                cancelShoeDetail()
                viewModel.cancelShoeDetailComplete()
            }
        })

        viewModel.eventSizeViewShoeDetailPress.observe(viewLifecycleOwner, { view ->
            view?.let {
                clearSizeShoeDetail()
                changeBackgroundSizeSelectedShoeDetail(view)
            }
        })

        viewModel.eventPictureShoeDetailPress.observe(
            viewLifecycleOwner,
            { nameModelShoe ->
                nameModelShoe?.let {
                    changePictureShoeDetailPress(nameModelShoe)
                }
            })

        viewModel.eventSaveFailByNameShoeDetail.observe(viewLifecycleOwner, {
            if (it) {
                val message = "The name of the shoe is required"
                showAlert(message)
                viewModel.saveFailByNameShoeDetailComplete()
            }
        })

        viewModel.eventSaveFailBySizeShoeDetail.observe(viewLifecycleOwner, {
            if (it) {
                val message = "The shoe's size is required"
                showAlert(message)
                viewModel.saveFailBySizeShoeDetailComplete()
            }
        })

        viewModel.eventSaveFailByNameCompanyShoeDetail
            .observe(viewLifecycleOwner, {
                if (it) {
                    val message = "The name of the company is required"
                    showAlert(message)
                    viewModel.saveFailByNameCompanyShoeDetailComplete()
                }
            })
    }

    private fun saveShoeDetail() {
        findNavController().popBackStack()
    }

    private fun cancelShoeDetail() {
        findNavController().popBackStack()
    }

    private fun clearSizeShoeDetail() {
        binding.button8.setBackgroundResource(R.drawable.disabled_circle)
        binding.button9.setBackgroundResource(R.drawable.disabled_circle)
        binding.button10.setBackgroundResource(R.drawable.disabled_circle)
        binding.button11.setBackgroundResource(R.drawable.disabled_circle)
        binding.button12.setBackgroundResource(R.drawable.disabled_circle)
    }

    private fun changeBackgroundSizeSelectedShoeDetail(view: View) {
        view.setBackgroundResource(R.drawable.enabled_circle)
    }

    private fun changePictureShoeDetailPress(nameModelShoe: String) {
        when (nameModelShoe) {
            "model1" -> binding.imageShoe.setImageResource(R.drawable.model1)
            "model2" -> binding.imageShoe.setImageResource(R.drawable.model2)
            "model3" -> binding.imageShoe.setImageResource(R.drawable.model3)
            "model4" -> binding.imageShoe.setImageResource(R.drawable.model4)
            "model5" -> binding.imageShoe.setImageResource(R.drawable.model5)
            "model6" -> binding.imageShoe.setImageResource(R.drawable.model6)
            "model7" -> binding.imageShoe.setImageResource(R.drawable.model7)
            "model8" -> binding.imageShoe.setImageResource(R.drawable.model8)
            "model9" -> binding.imageShoe.setImageResource(R.drawable.model9)
            "model10" -> binding.imageShoe.setImageResource(R.drawable.model10)
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(context)

        with(builder)
        {
            setTitle("Error")
            setMessage(message)
            setPositiveButton("OK", null)
            show()
        }
    }

}