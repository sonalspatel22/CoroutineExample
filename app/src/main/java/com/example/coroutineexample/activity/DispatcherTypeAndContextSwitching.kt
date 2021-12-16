package com.example.coroutineexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.coroutineexample.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DispatcherTypeAndContextSwitching : AppCompatActivity() {

    private val TAG = "Dispatchers"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Dispatchers type
        //IO
        //Main
        //Unconfined
        //Default

        //database related task/r/w
        //network related task
        //internal data r/w

        //Context Switching IO to Main
        lifecycleScope.launch(Dispatchers.IO) {
            val res = networkCall()
            //this is called context switching
            withContext(Dispatchers.Main){
                textView.setText(res)
            }
        }

        //cpu related task
        //eg. more power gain related task
        lifecycleScope.launch(Dispatchers.Default) {

        }

        //don't know about on which thread code will run
        lifecycleScope.launch(Dispatchers.Unconfined) {

        }

        //Ui related operation only run in main thread
        lifecycleScope.launch(Dispatchers.Main) {

        }

        //context = Job+Dispatcher+main
    }

    private suspend fun networkCall(): String {
        Log.i(TAG, "networkcall: start")
        delay(2000)
        Log.i(TAG, "networkcall: end")
        return "connected"
    }
}