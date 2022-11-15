package com.example.recycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var adapter :NewsAdapter
    //list of articles
    private var articles = mutableListOf<Article>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding our data in the recycler view
        //here we simply binded our data to an empty list
        adapter= NewsAdapter(this@MainActivity,articles)
        newsList.adapter=adapter
        //newsList.layoutManager=LinearLayoutManager(this@MainActivity)
        //after that we will be adding data in the list in the response function
        val layoutManager  = StackLayoutManager()



        getNews()

        }

    //calling the retrofit i.e. the newsService
    private fun getNews(){
        val news: Call<News> =NewsService.newsInstance.getheadlines("in",1)
        //whenever one request is completeed it's callback is called
        news.enqueue(object:Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("cheezyCode","errro in fetching the news")
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                //we are getting news as response
                val news:News?=response.body()
                if(news!=null){
                    Log.d("CHEEZYCODE",news.toString())
                    articles.addAll(news.articles)

                    //notifying our adapter that are data has been changed
                    adapter.notifyDataSetChanged()


                }
            }
        }
        )
    }
}
//we need live libraries to load the images for the news app