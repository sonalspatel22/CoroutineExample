package com.example.coroutineexample.network.repository

import com.example.coroutineexample.model.UserData
import com.example.coroutineexample.network.request.PostRequest
import com.example.coroutineexample.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository(private val postRequest: PostRequest) {

    fun getPost() = flow<Resource<List<UserData>>> {
        emit(Resource.loading())
        val posts = postRequest.getPost()
        emit(Resource.success(posts))
    }.catch {
        emit(Resource.failed(it.message!!))
    }.flowOn(Dispatchers.IO)

}