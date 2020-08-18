package com.codingwithjks.retrofit.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.newsfeedapp.Model.Articles
import com.codingwithjks.newsfeedapp.Model.News
import com.codingwithjks.newsfeedapp.Network.RetrofitBuilder
import com.codingwithjks.retrofit.Adapter.NewsAdapter
import com.codingwithjks.retrofit.Listener.Listener
import com.codingwithjks.retrofit.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),Listener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var articlesList: ArrayList<Articles>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        fetchData()
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
        val call:Call<News> = RetrofitBuilder.newApi.getNews("bitcoin","156c7ce8e18b47b98a0a459cb348684f","2020-07-17","publishedAt")
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

        })
    }

    override fun onItemClickListener(position: Int) {
       val intent=Intent(this, WebActivity::class.java)
        intent.putExtra("url",articlesList[position].url)
        startActivity(intent)
    }
}