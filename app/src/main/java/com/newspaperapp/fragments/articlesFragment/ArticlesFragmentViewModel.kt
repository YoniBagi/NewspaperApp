package com.newspaperapp.fragments.articlesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newspaperapp.dataModel.ArticleCategory
import com.newspaperapp.managers.InjectionCreator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val TAG = "ArticlesViewModel"

class ArticlesFragmentViewModel : ViewModel(), ArticlesContract.IViewModel {
    private val fragmentModel = InjectionCreator.getArticleModelFragment(this)
    private val dataFetchingProgressMLD = MutableLiveData(true)
    private val articlesDataMLD = MutableLiveData<ArticleCategory?>()
    private val errorMLD = MutableLiveData(false)
    private val titleArticleMLD = MutableLiveData<String>()

    fun getArticlesDataMLD(): LiveData<ArticleCategory?> = articlesDataMLD
    fun getDataFetchingProgressMLD(): LiveData<Boolean> = dataFetchingProgressMLD
    fun getErrorMLD(): LiveData<Boolean> = errorMLD
    fun getTitleArticleMLD(): LiveData<String> = titleArticleMLD

    private fun fetchArticlesData() {
        viewModelScope.launch {
            val articleCategory = fragmentModel.fetchArticlesData()
                .onStart { dataFetchingProgressMLD.postValue(true) }
                .onCompletion { dataFetchingProgressMLD.postValue(false) }
                .catch { errorMLD.postValue(true) }
                .singleOrNull()
            articlesDataMLD.postValue(articleCategory)
            articleCategory?.title?.let { titleArticleMLD.postValue(it) }
        }
    }

    fun onResume() {
        fetchArticlesData()
    }
}