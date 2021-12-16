package com.example.coroutineexample.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineexample.model.UserData
import com.example.coroutineexample.network.repository.PostRepository
import com.example.coroutineexample.network.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val _post: MutableLiveData<Resource<List<UserData>>> = MutableLiveData<Resource<List<UserData>>>()
    val post: LiveData<Resource<List<UserData>>>

    get() = _post

    fun getPost() = viewModelScope.launch {

        postRepository.getPost().collect {
            _post.value = it
        }

    }

}


