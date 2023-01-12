package com.alilbanna.shoestore

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alilbanna.shoestore.models.ShoeModel

class AllViewModel : ViewModel(){

    var shoe = ShoeModel(
        "",
        0,
        "",
        "",
        0
    )

    // Login
    private val _eventLoginMade = MutableLiveData(false)
    val eventLoginMade: LiveData<Boolean>
        get() = _eventLoginMade


    // OnBoarding
    private val _eventNextWelcomePress = MutableLiveData(false)
    val eventNextWelcomePress: LiveData<Boolean>
        get() = _eventNextWelcomePress


    // Instruction
    private val _eventNextInstructionPress = MutableLiveData(false)
    val eventNextInstructionPress: LiveData<Boolean>
        get() = _eventNextInstructionPress


    // Instructions Detail
    private val _eventNextInstructionDetailPress = MutableLiveData(false)
    val eventNextInstructionDetailPress: LiveData<Boolean>
        get() = _eventNextInstructionDetailPress

    //Shoes List
    private var _shoesList = MutableLiveData<MutableList<ShoeModel>?>(mutableListOf())
    val shoesList: MutableLiveData<MutableList<ShoeModel>?>
        get() = _shoesList

    private val _eventAddShoeListPress = MutableLiveData(false)
    val eventAddShoeListPress: LiveData<Boolean>
        get() = _eventAddShoeListPress


    //save
    private val _eventSaveShoeDetailPress = MutableLiveData(false)
    val eventSaveShoeDetailPress: LiveData<Boolean>
        get() = _eventSaveShoeDetailPress

    //cancel
    private val _eventCancelShoeDetailPress = MutableLiveData(false)
    val eventCancelShoeDetailPress: LiveData<Boolean>
        get() = _eventCancelShoeDetailPress

    //shoe picture
    private val _eventPictureShoeDetailPress = MutableLiveData<String>()
    val eventPictureShoeDetailPress: LiveData<String>
        get() = _eventPictureShoeDetailPress

    //size
    private val _eventSizeViewShoeDetailPress = MutableLiveData<View>()
    val eventSizeViewShoeDetailPress: LiveData<View>
        get() = _eventSizeViewShoeDetailPress

    //missing name shoe
    private val _eventSaveFailByNameShoeDetail = MutableLiveData(false)
    val eventSaveFailByNameShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailByNameShoeDetail

    //missing size shoe
    private val _eventSaveFailBySizeShoeDetail = MutableLiveData(false)
    val eventSaveFailBySizeShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailBySizeShoeDetail

    //missing name company
    private val _eventSaveFailByNameCompanyShoeDetail = MutableLiveData(false)
    val eventSaveFailByNameCompanyShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailByNameCompanyShoeDetail


    // Login Functions
    fun goToWelcomeStart() {
        _eventLoginMade.value = true
    }

    fun goToWelcomeComplete() {
        _eventLoginMade.value = false
    }


    // Instructions Functions
    fun goToInstructionDetailStart() {
        _eventNextInstructionPress.value = true
    }

    fun goToInstructionDetailComplete() {
        _eventNextInstructionPress.value = false
    }


    // Instructions Details Functions
    fun goToShoeListStart() {
        _eventNextInstructionDetailPress.value = true
    }

    fun goToShoeListComplete() {
        _eventNextInstructionDetailPress.value = false
    }


    // OnBoarding Functions
    fun goToInstructionStart() {
        _eventNextWelcomePress.value = true
    }

    fun goToInstructionComplete() {
        _eventNextWelcomePress.value = false
    }


    //Shoes List Functions
    fun goToShoeDetailStart() {
        _eventAddShoeListPress.value = true
    }

    fun goToShoeDetailStartComplete() {
        _eventAddShoeListPress.value = false
    }


    // Save Functions
    fun saveShoeDetailStart() {
        when {
            shoe.company.trim().isEmpty() -> {
                _eventSaveFailByNameCompanyShoeDetail.value = true
            }
            shoe.name.trim().isEmpty() -> {
                _eventSaveFailByNameShoeDetail.value = true
            }
            shoe.size <= 0.9 -> {
                _eventSaveFailBySizeShoeDetail.value = true
            }
            else -> {
                saveNewShoe()
                _eventSaveShoeDetailPress.value = true
            }
        }
    }

    fun saveShoeDetailComplete() {
        _eventSaveShoeDetailPress.value = false
    }

    fun saveFailByNameShoeDetailComplete() {
        _eventSaveFailByNameShoeDetail.value = false
    }

    fun saveFailByNameCompanyShoeDetailComplete() {
        _eventSaveFailByNameCompanyShoeDetail.value = false
    }

    fun saveFailBySizeShoeDetailComplete() {
        _eventSaveFailBySizeShoeDetail.value = false
    }

    //Cancel Functions
    fun cancelShoeDetailStart() {
        _eventCancelShoeDetailPress.value = true
    }

    fun cancelShoeDetailComplete() {
        _eventCancelShoeDetailPress.value = false
    }

    fun setSize(view: View, size: Int) {
        _eventSizeViewShoeDetailPress.value = view
        shoe.size = size
    }

    fun changeShoePicture() {
        shoe.shoeModel++
        if (shoe.shoeModel >= shoe.modelsAvailable.size) {
            shoe.shoeModel = 0
        }
        _eventPictureShoeDetailPress.value = shoe.modelsAvailable[shoe.shoeModel]
    }

    private fun saveNewShoe() {
        val list = _shoesList.value
        list?.let {
            it.add(shoe)
            _shoesList.setValue(list)
        }
    }

    fun clearShoeTemplate() {
        shoe = ShoeModel(
            "",
            0,
            "",
            "",
            0
        )
        _eventPictureShoeDetailPress.value = shoe.modelsAvailable[shoe.shoeModel]
    }
}