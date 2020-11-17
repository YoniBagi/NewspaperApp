package com.newspaperapp.managers

import com.newspaperapp.fragments.articlesFragment.ArticlesContract
import com.newspaperapp.fragments.articlesFragment.ArticlesFragmentModel

object InjectionCreator {
    fun getArticleModelFragment(viewModel: ArticlesContract.IViewModel): ArticlesContract.IModel =
        ArticlesFragmentModel(viewModel)
}