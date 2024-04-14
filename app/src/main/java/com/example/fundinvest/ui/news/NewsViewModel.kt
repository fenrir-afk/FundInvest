package com.example.fundinvest.ui.news

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class NewsViewModel : ViewModel() {
    val articlesLiveData: MutableLiveData<List<String> > = MutableLiveData()
    private val articlesArr: MutableList<String> = mutableListOf()
    val imgUrls: MutableList<String> = mutableListOf()
    private var newsLinks: Set<String> = emptySet()

    fun getNews(url: String) {
        articlesLiveData.value = emptyList()
        articlesArr.clear()
        imgUrls.clear()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Получаем HTML-содержимое страницы
                val doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .get()


                newsLinks =  doc.select("div.news-item__image > a[href]")
                    .map { it.attr("href") }.toSet()

                if (newsLinks.size == 1){
                    articlesLiveData.postValue(emptyList())
                    return@launch
                }
                val deferredTasks = newsLinks.map { newsLink ->
                    async {
                        val newsDoc = Jsoup.connect("https://rb.ru$newsLink").get()

                        val metaElement = newsDoc.select("meta[content~=https://media.rbcdn.ru/media/news/]").first()
                        val imageUrl = metaElement.attr("content")
                        imgUrls.add(imageUrl)

                        val divElement = newsDoc.select("div.article__introduction")[1]
                        val lastSentenceIndex = divElement.text().lastIndexOf('.')
                        val article = divElement.text().substring(0, lastSentenceIndex)
                        articlesArr.add(article)
                        article
                    }
                }
                deferredTasks.awaitAll()
                articlesLiveData.postValue(articlesArr)

            } catch (e: Exception) {
                Log.e("Exception during parsing: ", "Error: ${e.message}")
            }
        }
    }
}