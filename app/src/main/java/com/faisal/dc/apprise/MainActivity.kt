package com.faisal.dc.apprise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.faisal.dc.apprise.databinding.ActivityMainBinding
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    var list :  MutableList<DataModel> = mutableListOf()
    lateinit var adapter : CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        val quotesApi = RetrofitHelper.getInstance().create(AppApi::class.java)
        // launching a new coroutine
        GlobalScope.launch(Dispatchers.Main) {
            val result = quotesApi.getMovie("tt3896198",RetrofitHelper.API_KEY)


            // Checking the results
                Log.d("dim: ", result.body().toString())


            for (i in 1..5) {
                result.body()?.Poster?.let {
                    list.add(DataModel(i, it)) }

            }
            adapter.notifyDataSetChanged()


        }

         adapter = CarouselAdapter(list)

        binding.rvBanner.adapter = adapter
        binding.rvBanner.set3DItem(true)
        binding.rvBanner.setAlpha(true)
        val carouselLayoutManager = binding.rvBanner.getCarouselLayoutManager()
        val currentlyCenterPosition = binding.rvBanner.getSelectedPosition()

        binding.rvBanner.setItemSelectListener(object : CarouselLayoutManager.OnSelected {
            override fun onItemSelected(position: Int) {
                //Cente item
                Toast.makeText(this@MainActivity, list[position].img.toString(), Toast.LENGTH_LONG).show()

            }
        })
    }
}