package com.example.coroutineexample.network.request

import com.example.coroutineexample.model.UserData
import retrofit2.http.GET

interface PostRequest {

    @GET("posts")
    suspend fun getPost():List<UserData>

}