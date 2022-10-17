package com.faisal.dc.apprise

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import com.faisal.dc.apprise.databinding.ActivityMainBinding
import com.faisal.dc.apprise.databinding.ActivityMovieDetailsBinding
import com.faisal.dc.apprise.model.BannerDataModel
import com.faisal.dc.apprise.model.Search
import com.faisal.dc.apprise.network.AppApi
import com.faisal.dc.apprise.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieDetailsBinding

    private var playbackPosition = 0
  //  private val rtspUrl = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov"
    private val rtspUrl = "https://ia600201.us.archive.org/22/items/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4"
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        mediaController = MediaController(this)

        binding.videoView.setOnPreparedListener {
            it.isLooping=true
            mediaController.setAnchorView(binding.videoContainer)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.seekTo(playbackPosition)
            binding.videoView.start()
        }

        binding.videoView.setOnInfoListener { player, what, extras ->
            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                binding.progressBar.visibility = View.INVISIBLE
            true
        }

        val obj: Search = intent.getSerializableExtra("key") as Search

        binding.tvMovieTitle.text="Title: "+obj.Title
        binding.tvMovieType.text="Type: "+obj.Type
        val quotesApi = RetrofitHelper.getInstance().create(AppApi::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val result = quotesApi.getMovie(obj.imdbID, RetrofitHelper.API_KEY)


            // Checking the results
            //     Log.d("dim: ", result.body().toString())

            binding.tvMovieDetails.text="Plot:\n"+result.body()?.Plot



        }
    }

    override fun onStart() {
        super.onStart()

        val uri = Uri.parse(rtspUrl)
        binding.videoView.setVideoURI(uri)
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()

        binding.videoView.setOnCompletionListener( MediaPlayer.OnCompletionListener() {

                it.reset();
            val uri = Uri.parse(rtspUrl)
            binding.videoView.setVideoURI(uri)
            binding.videoView.start();

        })
    }

    override fun onPause() {
        super.onPause()

        binding.videoView.pause()
        playbackPosition = binding.videoView.currentPosition
    }

    override fun onStop() {
        binding.videoView.stopPlayback()

        super.onStop()
    }
}