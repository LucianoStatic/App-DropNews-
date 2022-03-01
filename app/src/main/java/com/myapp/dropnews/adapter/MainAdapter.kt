package com.myapp.dropnews.adapter

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapp.dropnews.R
import com.myapp.dropnews.model.Article
import kotlinx.android.synthetic.main.item_news.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {

            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvTitle.text = article.source?.name
            tvSource.text = article.author ?: article.source?.name
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            setOnClickListener{
                onItemClickListener?.let { click ->
                    click(article)
                }
            }
        }
    }

    private var onItemClickListener :((Article) -> Unit)? = null

    fun setOnclickListener(listener:(Article) -> Unit){
        onItemClickListener = listener

    }

}

