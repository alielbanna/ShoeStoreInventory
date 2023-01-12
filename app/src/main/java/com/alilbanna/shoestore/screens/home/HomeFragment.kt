package com.alilbanna.shoestore.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alilbanna.shoestore.AllViewModel
import com.alilbanna.shoestore.R
import com.alilbanna.shoestore.databinding.HomeFragmentBinding
import com.alilbanna.shoestore.databinding.ShoeLayoutBinding
import com.alilbanna.shoestore.models.ShoeModel


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: AllViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackPressedConfiguration()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        initViewModel()
        initObservers()

        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_home_to_login)
        return super.onOptionsItemSelected(item)
    }

    private fun setBackPressedConfiguration() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
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

        binding.homeViewModel = viewModel
    }

    private fun initObservers() {
        viewModel.eventAddShoeListPress.observe(viewLifecycleOwner) {
            if (it) {
                goToShoeList()
                viewModel.goToShoeDetailStartComplete()
            }
        }

        viewModel.shoesList.observe(viewLifecycleOwner) { listShoes ->
            if (listShoes != null) {
                initShoeList(listShoes)
            }
        }
    }

    private fun goToShoeList() {
        findNavController().navigate(R.id.action_home_to_shoeDetails)
    }

    private fun initShoeList(listShoes: MutableList<ShoeModel>) {
        val parentLayout = binding.shoeList

        var index = 0
        while (index < listShoes.size) {
            val shoeBinding: ShoeLayoutBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.shoe_layout,
                parentLayout,
                false
            )

            val shoe = listShoes[index]
            shoeBinding.nameShoe.text = shoe.name
            shoeBinding.nameCompanyList.text = shoe.company
            shoeBinding.sizeShoeList.text = shoe.size.toString()
            when (shoe.modelsAvailable[shoe.shoeModel]) {
                "model1" -> shoeBinding.imageShoe.setImageResource(R.drawable.model1)
                "model2" -> shoeBinding.imageShoe.setImageResource(R.drawable.model2)
                "model3" -> shoeBinding.imageShoe.setImageResource(R.drawable.model3)
                "model4" -> shoeBinding.imageShoe.setImageResource(R.drawable.model4)
                "model5" -> shoeBinding.imageShoe.setImageResource(R.drawable.model5)
                "model6" -> shoeBinding.imageShoe.setImageResource(R.drawable.model6)
                "model7" -> shoeBinding.imageShoe.setImageResource(R.drawable.model7)
                "model8" -> shoeBinding.imageShoe.setImageResource(R.drawable.model8)
                "model9" -> shoeBinding.imageShoe.setImageResource(R.drawable.model9)
                "model10" -> shoeBinding.imageShoe.setImageResource(R.drawable.model10)
            }

            parentLayout.addView(binding.root)

            index++
        }
    }
}