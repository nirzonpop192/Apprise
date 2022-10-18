package com.faisal.dc.apprise

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.faisal.dc.apprise.adapter.CarouselAdapter
import com.faisal.dc.apprise.adapter.HomeMovieAdapter
import com.faisal.dc.apprise.databinding.FragmentHomeBinding
import com.faisal.dc.apprise.databinding.FragmentListBinding
import com.faisal.dc.apprise.model.BannerDataModel
import com.faisal.dc.apprise.model.Search
import com.faisal.dc.apprise.network.AppApi
import com.faisal.dc.apprise.network.RetrofitHelper
import com.faisal.dc.apprise.network.Utility.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var currentPage: Int = 1
    lateinit var  binding: FragmentListBinding

    var movieList :  MutableList<Search> = mutableListOf()
    var latestMovieList :  MutableList<Search> = mutableListOf()

    lateinit var rail_1_Adapter : HomeMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding= FragmentListBinding.inflate(inflater,  container, false)
        val view: View = binding.getRoot()



        rail_1_Adapter = HomeMovieAdapter(movieList,
            object:HomeMovieAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(activity, MovieDetailsActivity::class.java)
                    intent.putExtra("key", movieList.get(position))
                    startActivity(intent)
                }
            }
        )



        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        binding.rvMovieList.layoutManager = layoutManager
        binding.rvMovieList.adapter=rail_1_Adapter

        binding.rvMovieList.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                getMoreItems()
            }
        })



        return view
    }
    fun getMoreItems() {


        if(context?.isInternetAvailable() == true){
            currentPage++
            calApi(currentPage)
        }
    }

    override fun onResume() {

        super.onResume()
        if(context?.isInternetAvailable() == true) calApi(currentPage)

    }
    fun calApi(pageNumber:Int){
        val quotesApi = RetrofitHelper.getInstance().create(AppApi::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val result = quotesApi.getMovieList("Batman",pageNumber.toString(), RetrofitHelper.API_KEY)


            // Checking the results
            // Log.d("dim: ", result.body().toString())
            movieList.addAll( result.body()!!.Search)

            rail_1_Adapter.updateData(movieList)


        }
    }
}