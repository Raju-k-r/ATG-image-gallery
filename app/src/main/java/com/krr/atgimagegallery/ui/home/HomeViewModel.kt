package com.krr.atgimagegallery.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krr.atgimagegallery.resource.network.Photo
import com.krr.atgimagegallery.resource.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _images : MutableLiveData<List<Photo>> = MutableLiveData()

    val images = _images

    fun getAllImages(){
        viewModelScope.launch {
            val response = repository.getAllImages()
            images.value = response
        }
    }
}