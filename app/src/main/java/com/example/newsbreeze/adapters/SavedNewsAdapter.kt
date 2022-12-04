package com.example.newsbreeze.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsbreeze.R
import com.example.newsbreeze.models.Article
import kotlinx.android.synthetic.main.item_article_saved_preview.view.*

class SavedNewsAdapter : RecyclerView.Adapter<SavedNewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

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
        val viewHolder= ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_saved_preview,
                parent,
                false
            )
        )

        return viewHolder

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
           Glide.with(this).load(article.urlToImage).into(imageView)
            tvTitleText.text = "#"+article.source?.name
            tvTitleDesc.text = article.title
            tvTime.text = article.publishedAt?.subSequence(0, 10)
            tvAuthor.text = article.author

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}













