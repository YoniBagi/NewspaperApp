package com.newspaperapp.fragments.articlesFragment

import com.newspaperapp.dataModel.ArticleCategory
import kotlinx.coroutines.flow.Flow

interface ArticlesContract {
    interface IViewModel {}
    interface IModel {
        fun fetchArticlesData(): Flow<ArticleCategory>
    }
}