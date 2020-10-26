package com.testapp.fetchrewards.services

import com.testapp.fetchrewards.models.ItemsList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    fun returnPosts():
            Observable<ItemsList>

    companion object {
        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}