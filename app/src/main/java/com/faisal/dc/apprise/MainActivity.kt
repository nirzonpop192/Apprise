package com.faisal.dc.apprise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesApi = RetrofitHelper.getInstance().create(AppApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = quotesApi.getMovie("tt3896198","2088fa00")

            if (result != null)
            // Checking the results
                Log.d("dim: ", result.body().toString())
        }
    }
}