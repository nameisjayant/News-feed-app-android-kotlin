package com.codingwithjks.retrofit.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.newsfeedapp.Model.Articles
import com.codingwithjks.retrofit.Adapter.NewsAdapter
import com.codingwithjks.retrofit.Listener.Listener
import com.codingwithjks.retrofit.R
import com.codingwithjks.retrofit.ViewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),Listener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel:NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        newsViewModel.newsResponse.observe(this, Observer { response->
            newsAdapter.setData(response.articles as ArrayList<Articles>)
            Log.d("main", " ${response.articles}")
            progressBar.visibility=View.GONE
            recyclerView.visibility=View.VISIBLE
        })
    }

    private fun setUpUi() {
        recyclerView=findViewById(R.id.recyclerView)
        newsAdapter= NewsAdapter(this,ArrayList<Articles>(),this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=newsAdapter
        }
    }

    override fun onItemClickListener(position: Int) {
        newsViewModel.newsResponse.observe(this, Observer {response->
            val intent=Intent(this, WebActivity::class.java)
            intent.putExtra("url",response.articles[position].url)
            startActivity(intent)
        })

    }
}