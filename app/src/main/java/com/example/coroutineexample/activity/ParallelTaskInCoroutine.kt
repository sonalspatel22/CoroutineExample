package com.example.coroutineexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.coroutineexample.R
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class ParallelTaskInCoroutine : AppCompatActivity() {

    val TAG = "ParallelTask"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallel_task_in_coroutine)

        //return value with launch builder example
        /**lifecycleScope.launch {

            val time = measureTimeMillis {

                var s1 = ""
                var s2 = ""
                var s3 = ""

                val job = launch {
                    launch { s1 = networkCall3() }
                    launch { s2 = networkCall2() }
                    launch { s3 = networkCall1() }
                }
                job.join()
                Log.i(TAG, "network call done")
                Log.i(TAG, "oncreate: $s1 $s2 $s3" )
            }

            Log.i(TAG, "" + time)
        }
    }**/

        //parallel execution with async method to get return value from method
        lifecycleScope.launch {

            val time = measureTimeMillis {

                val job = launch {
                    val s3 = async {  networkCall3() }
                    val s2 = async {  networkCall2() }
                    val s1 = async {  networkCall1() }
                    Log.i(TAG, "oncreate: ${s1.await()} ${s2.await()} ${s3.await()}" )
                }
                job.join()

                Log.i(TAG, "network call done")

            }

            Log.i(TAG, "" + time)
        }
    }

    private suspend fun networkCall1(): String {
        delay(1000)
        Log.i(TAG, "networkCall1:called")
        return "Result1"
    }

    private suspend fun networkCall2(): String {
        delay(2000)
        Log.i(TAG, "networkCall2:called")
        return "Result2"
    }

    private suspend fun networkCall3(): String {
        delay(3000)
        Log.i(TAG, "networkCall3:called")
        return "Result3"
    }
}