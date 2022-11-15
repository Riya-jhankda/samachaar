package com.example.recycle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context:Context, val articles:List<Article>)
    :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        //creating an object for the view
        val view:View=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)

    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=articles[position]
        //this is how we bind our text view
        holder.newstitle.text=article.title
        holder.newsdesc.text=article.description

        //images are binded using some other method
        //we need to pass dependenciesto load live images
        Glide.with(context).load(article.urlToImage).into(holder.newsImg)
        //this is how we use to glide to download our image

        //now we want that we can touch the news pr press it
        holder.itemView.setOnClickListener {
            Toast.makeText(context,article.title,Toast.LENGTH_SHORT).show()

        }

    }

    override fun getItemCount(): Int {
        return  articles.size
    }
    class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        //creating reference for the views
        var newsImg = itemView.findViewById<ImageView>(R.id.newsimage)
        var newstitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsdesc = itemView.findViewById<TextView>(R.id.newsDescription)

    }
}