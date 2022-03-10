package com.fernandohbrasil.marvelsquad.ui.viewmodel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _titleBar: MutableLiveData<Boolean> = MutableLiveData()

    val titleBar: LiveData<Boolean>
        get() = _titleBar

    fun hasTitleBar(value: Boolean) {
        _titleBar.value = value
    }
}