package com.example.coroutineexample.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.coroutineexample.R
import com.example.coroutineexample.network.RetrofitBuilder
import com.example.coroutineexample.network.repository.PostRepository
import com.example.coroutineexample.network.utils.Resource

class PostActivity : AppCompatActivity() {

    val TAG = "PostActivity"

    private val mViewModel: PostViewModel by viewModels {
        PostViewModelFactory(PostRepository(RetrofitBuilder.postRetrofit))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_post)

        mViewModel.post.observe(this) {
            when (it) {
                is Resource.Failed -> Log.i(TAG, "Failed:${it.message}")
                is Resource.Loading -> Log.i(TAG, "Loading")
                is Resource.Success -> Log.i(TAG, "Success:${it.data}")
            }
        }

        mViewModel.getPost()

    }

}