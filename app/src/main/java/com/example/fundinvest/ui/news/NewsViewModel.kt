package com.example.fundinvest.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class NewsViewModel : ViewModel() {
    val articlesLiveData: MutableLiveData<List<String> > = MutableLiveData()
    private val articlesArr: MutableList<String> = mutableListOf()
    val imgUrls: MutableList<String> = mutableListOf()
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

                // Находим ссылки
                val newsLinks = doc.select("div.news-item__image > a[href]")
                    .map { it.attr("href") }.toSet()


                for (newsLink in newsLinks) {
                    val newsDoc = Jsoup.connect("https://rb.ru$newsLink").get()
                    //Получение заголовка
                    // Получаем URL изображения из атрибута `content`
                    val metaElement = newsDoc.select("meta[content~=https://media.rbcdn.ru/media/news/]").first()
                    val imageUrl = metaElement.attr("content")
                    imgUrls.add(imageUrl)

                    //Получаем вступление без последнего предложения(оно непонятно откуда берётся)
                    val divElement = newsDoc.select("div.article__introduction")[1]
                    val lastSentenceIndex = divElement.text().lastIndexOf('.')
                    val article = divElement.text().substring(0, lastSentenceIndex)
                    articlesArr.add(article)
                }
                articlesLiveData.postValue(articlesArr)

            } catch (e: Exception) {
                Log.e("Exception during parsing: ", "Error: ${e.message}")
            }
        }
    }
}