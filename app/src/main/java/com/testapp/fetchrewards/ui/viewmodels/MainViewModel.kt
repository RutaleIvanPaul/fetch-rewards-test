package com.testapp.fetchrewards.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testapp.fetchrewards.models.Item
import com.testapp.fetchrewards.repositories.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    fun returnPostsVM() = mainRepository.returnItemsRepository()
}