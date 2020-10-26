package com.testapp.fetchrewards.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.testapp.fetchrewards.models.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RequestManager {
    private var disposable: Disposable? = null

    private val apiServe by lazy {
        ApiService.create()
    }

    fun returnPostsFromApi(): MutableLiveData<ArrayList<Item>> {
        val items = MutableLiveData<ArrayList<Item>>()
        disposable = apiServe.returnPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    items.postValue(result)

                },
                { error ->
                    Log.d("API ERROR", "API Fetch Error: ${error.message} ")
                }
            )
        return items
    }
}