package com.faisal.dc.apprise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.faisal.dc.apprise.adapter.CarouselAdapter
import com.faisal.dc.apprise.adapter.HomeMovieAdapter
import com.faisal.dc.apprise.databinding.ActivityMainBinding
import com.faisal.dc.apprise.model.BannerDataModel
import com.faisal.dc.apprise.model.Search
import com.faisal.dc.apprise.network.AppApi
import com.faisal.dc.apprise.network.RetrofitHelper
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    var bannerList :  MutableList<BannerDataModel> = mutableListOf()
    var movieList :  MutableList<Search> = mutableListOf()
    var latestMovieList :  MutableList<Search> = mutableListOf()
    lateinit var bannerAdapter : CarouselAdapter
    lateinit var rail_1_Adapter : HomeMovieAdapter
    lateinit var rail_2_Adapter : HomeMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)




        bannerAdapter = CarouselAdapter(bannerList)

        binding.rvBanner.adapter = bannerAdapter
        binding.rvBanner.set3DItem(true)
        binding.rvBanner.setAlpha(true)


        rail_1_Adapter = HomeMovieAdapter(movieList,
            object:HomeMovieAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
                    intent.putExtra("key", movieList.get(position))
                    startActivity(intent)
                }
            }
        )

        rail_2_Adapter = HomeMovieAdapter(latestMovieList,
            object:HomeMovieAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
                    intent.putExtra("key", latestMovieList.get(position))
                    startActivity(intent)
                }
            }
        )

        val layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        val layoutManager1 = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        binding.rvRail1.layoutManager = layoutManager
        binding.rvRail1.adapter=rail_1_Adapter
        binding.rvRail2.layoutManager = layoutManager1
        binding.rvRail2.adapter=rail_2_Adapter


    }

    override fun onResume() {
        val quotesApi = RetrofitHelper.getInstance().create(AppApi::class.java)
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {
            val result = quotesApi.getMovieList("Batman","1", RetrofitHelper.API_KEY)


            // Checking the results
           // Log.d("dim: ", result.body().toString())
            movieList.addAll( result.body()!!.Search)

            rail_1_Adapter.updateData(movieList)


        }


        GlobalScope.launch(Dispatchers.Main) {
            val result = quotesApi.getMovieList("Avenger","1", RetrofitHelper.API_KEY)


            // Checking the results
            // Log.d("dim: ", result.body().toString())
            latestMovieList.addAll( result.body()!!.Search)

            rail_2_Adapter.updateData(latestMovieList)


        }

        GlobalScope.launch(Dispatchers.Main) {
            val result = quotesApi.getMovie("tt3896198", RetrofitHelper.API_KEY)


            // Checking the results
            //     Log.d("dim: ", result.body().toString())

            bannerList.clear()
            for (i in 1..5) {
                result.body()?.Poster?.let {
                    bannerList.add(BannerDataModel(i, it)) }

            }
            bannerAdapter.updateData(bannerList)


        }
    }
}