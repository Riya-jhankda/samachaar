package com.example.recycle

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://newsapi.org/"
const val API_KEY="5b7d8c038b2e44d78355568a9fb290ba"

interface NewsInterface {

    //making a get request
    @GET("/v2/top-headlines?apiKey=$API_KEY")
    //we need to pass the country and page as a querry
    fun getheadlines(@Query("country")country :String ,@Query("Page") Page:Int):Call<News>


}
//implementing the news interface on the retrofit object
//singleton object creation - whenever we need to make a call we call the singleton object
object NewsService{
   val newsInstance :NewsInterface
   init{
       val retrofit:Retrofit=Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()

       //and here our retrofit object ends

       newsInstance=retrofit.create(NewsInterface::class.java)
           //now whenever we want headlines we will cal this object

   }

}