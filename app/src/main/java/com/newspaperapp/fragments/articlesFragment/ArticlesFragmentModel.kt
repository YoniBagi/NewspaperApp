package com.newspaperapp.fragments.articlesFragment

import com.newspaperapp.dataModel.ArticleCategory
import com.newspaperapp.network.NetworkManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticlesFragmentModel(viewModel: ArticlesContract.IViewModel) : ArticlesContract.IModel {

    override fun fetchArticlesData(): Flow<ArticleCategory> = flow {
        emit(getFirstItem(NetworkManager.instanceServiceAdi.getArticlesCategory().main))
    }

    private fun getFirstItem(listArticles: List<ArticleCategory>): ArticleCategory =
        listArticles.first()
}