package com.testapp.fetchrewards.repositories

import androidx.lifecycle.MutableLiveData
import com.testapp.fetchrewards.models.Item
import com.testapp.fetchrewards.services.RequestManager
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val requestManager: RequestManager
) {
    fun returnItemsRepository() = requestManager.returnPostsFromApi()
}