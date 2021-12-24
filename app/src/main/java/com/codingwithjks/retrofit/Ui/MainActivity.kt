package com.codingwithjks.retrofit.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.appcompat.widget.Toolbar

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

    private lateinit var articlesList: ArrayList<Articles>
    private val newsViewModel:NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.Toolbar)
        setSupportActionBar(toolbar)
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


    private fun fetchData() {
        val call:Call<News> = RetrofitBuilder.newApi
                .getNews("bitcoin"
                ,"c0ad41152144433b927d9a9208e2031b"
                ,"2020-09-16","publishedAt")

        call.enqueue(object :Callback<News> {

            override fun onResponse(call: Call<News>, response: Response<News>) {
               if(response.isSuccessful)
               {
                   val listArticles=response.body()?.articles
                   if(listArticles!=null && listArticles.isNotEmpty()){
                       newsAdapter.setData(listArticles as ArrayList<Articles>)
                       progressBar.visibility=View.GONE
                       recyclerView.visibility=View.VISIBLE
                   }
                   articlesList= response.body()?.articles as ArrayList<Articles>
               }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("main", "onFailure: ${t.message}")
            }

    override fun onItemClickListener(position: Int) {
        newsViewModel.newsResponse.observe(this, Observer {response->
            val intent=Intent(this, WebActivity::class.java)
            intent.putExtra("url",response.articles[position].url)
            startActivity(intent)
 
        })

    override fun onItemClickListener(position: Int) {
        val intent=Intent(this, WebActivity::class.java)
        intent.putExtra("url",articlesList[position].url)
        startActivity(intent)

    }
}