package com.newspaperapp.fragments.articlesFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.newspaperapp.R
import com.newspaperapp.adapters.ArticlesAdapter
import com.newspaperapp.dataModel.ArticleCategory
import com.newspaperapp.databinding.FragmentArticlesBinding
import com.newspaperapp.utils.CustomTabsBrowser

class ArticlesFragment : Fragment(), ArticlesAdapter.CallBackArticleAdapter {
    private lateinit var viewModelFragment: ArticlesFragmentViewModel
    private lateinit var mBinding: FragmentArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(container?.context),
            R.layout.fragment_articles,
            container,
            false
        )
        setViewModel()
        setObservers()
        return mBinding.root
    }

    private fun setViewModel() {
        viewModelFragment = ViewModelProvider(this).get(ArticlesFragmentViewModel::class.java)
        mBinding.articlesFragmentViewModel = viewModelFragment
        mBinding.lifecycleOwner = this
    }

    private fun setObservers() {
        viewModelFragment.getArticlesDataMLD().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setDataToViews(it)
            }
        })
    }

    private fun setDataToViews(articleCategory: ArticleCategory) {
        setRecyclerArticlesList(articleCategory)
    }

    private fun setRecyclerArticlesList(articleCategory: ArticleCategory) {
        mBinding.articlesRecyclerView.adapter = ArticlesAdapter(articleCategory.items, this)
    }

    override fun onResume() {
        super.onResume()
        viewModelFragment.onResume()
    }

    override fun onClickItem(url: String) {
        this.context?.let { CustomTabsBrowser.openCustomTab(url, it) }
    }
}
