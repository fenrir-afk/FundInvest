package com.example.fundinvest.ui.news

import android.content.Context

object SearchHistoryManager {
    private const val PREF_NAME = "SearchHistory"
    private const val KEY_SEARCH_HISTORY = "SearchHistory"

    fun saveSearchQuery(context: Context, query: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        val history = getSearchHistory(context).toMutableSet()
        if (history.size >= 10) {
            history.remove(history.last())
        }
        history.add(query)
        sharedPreferences.edit().putStringSet(KEY_SEARCH_HISTORY, history).apply()
    }

    fun getSearchHistory(context: Context): Set<String> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return emptySet()
        return sharedPreferences.getStringSet(KEY_SEARCH_HISTORY, emptySet()) ?: emptySet()
    }

    fun clearSearchHistory(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        sharedPreferences.edit().remove(KEY_SEARCH_HISTORY).apply()
    }
}