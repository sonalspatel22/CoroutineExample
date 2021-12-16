package com.example.coroutineexample.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coroutineexample.viewmodel.MainViewModel
import com.example.coroutineexample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class ThreeCoroutineScopeAndThreeCoroutineBuilderActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this, DispatcherTypeAndContextSwitching::class.java))
            finish()
        }

        lifecycleScope.launch {
            test1()

        }

        //3 types of coroutines builder
        //launch//async//runBlocking
//        runBlocking {
//            test1()
//        }


        // can access throughout whole application
//        GlobalScope.launch {
//            while (true) {
//                Log.i("gagging", "Coroutine Launch in :${Thread.currentThread().name}")
//                delay(2000)
//                Log.i("gagging", "" + button.text)
//                fun1()
//            }
//        }
//
//        //access  during activity
//        lifecycleScope.launch {
//            while (true) {
//                Log.i("gagging", "Coroutine Launch in :${Thread.currentThread().name}")
//                delay(2000)
//                Log.i("gagging", "" + button.text)
//                fun1()
//            }
//        }

        //access during ViewModel Scope
//        mainViewModel.run()
    }

    //    ++++lifecycleScope.launch+++
// ++ launch++ is coroutine builder
// which return job through which we can access job is active or not,we can cancel job, we can pause job
    suspend fun test() {
        var job = lifecycleScope.launch {
            delay(2000)
            Log.i("gagging", "2 sec finish")
        }
        job.join()
//        job.cancelAndJoin()
//        job.isActive
//        job.isCompleted
//        job.isCancelled
//        job.cancel()

        Log.i("gagging", "test :")
    }

    suspend fun test1() {
        val deferred:Deferred<String> = lifecycleScope.async {
            delay(2000)
            Log.i("gagging", "2 sec finish")
            "async finish"
        }
        //get value of scope
        val value = deferred.await()
        Log.i("gagging", value)
    }

    // suspend fun can call from other suspend fun or coroutinescope
    private suspend fun fun1() {
        Log.e("gagging", "fun1: start")
        fun2()

    }

    private fun fun2() {
        Log.e("gagging", "fun2: start")
    }
}