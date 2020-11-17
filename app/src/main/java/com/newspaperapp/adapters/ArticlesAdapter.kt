package com.newspaperapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.newspaperapp.dataModel.Article
import com.newspaperapp.databinding.TopAuthorItemAdapterBinding
import com.newspaperapp.enums.ItemArticleTypeEnum
import com.newspaperapp.R
import com.newspaperapp.databinding.BottomAuthorItemAdapterBinding

class ArticlesAdapter(
    private val articlesList: List<Article>,
    val listener: CallBackArticleAdapter
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemArticleTypeEnum.AUTHOR_NAME_TOP.getIntValue() -> createTopViewHolder(parent)
            else -> createBottomViewHolder(parent)
        }
    }

    private fun createTopViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<TopAuthorItemAdapterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.top_author_item_adapter,
            parent,
            false
        )
        return TopAuthorViewHolder(binding)
    }

    private fun createBottomViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<BottomAuthorItemAdapterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.bottom_author_item_adapter,
            parent,
            false
        )
        return BottomAuthorViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int = articlesList[position].type

    override fun getItemCount(): Int = articlesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (articlesList[position].type) {
            ItemArticleTypeEnum.AUTHOR_NAME_TOP.getIntValue() -> {
                holder as TopAuthorViewHolder
                holder.onBind(articlesList[position])
            }
            else -> {
                holder as BottomAuthorViewHolder
                holder.onBind(articlesList[position])
            }
        }
    }

    inner class TopAuthorViewHolder(private val binding: TopAuthorItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(article: Article) {
            binding.topAuthorViewHolder = this
            binding.article = article
            binding.executePendingBindings()
        }

        fun onClickItem(url: String?) {
            url?.let { listener.onClickItem(it) }
        }
    }

    inner class BottomAuthorViewHolder(private val binding: BottomAuthorItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(article: Article) {
            binding.bottomAuthorViewHolder = this
            binding.article = article
            binding.executePendingBindings()
        }

        fun onClickItem(url: String?) {
            url?.let { listener.onClickItem(it) }
        }
    }

    interface CallBackArticleAdapter {
        fun onClickItem(url: String)
    }
}