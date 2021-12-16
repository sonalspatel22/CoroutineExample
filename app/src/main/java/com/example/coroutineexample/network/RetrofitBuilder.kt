package com.example.coroutineexample.network

import com.example.coroutineexample.network.request.PostRequest
import com.example.coroutineexample.network.utils.NetworkConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder
{
    private fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val postRetrofit = getInstance().create(PostRequest::class.java)
}