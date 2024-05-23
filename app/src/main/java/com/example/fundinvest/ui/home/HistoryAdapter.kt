package com.example.fundinvest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.fundinvest.R
import com.example.fundinvest.data.StatementHistoryElement

class HistoryAdapter(private val viewModelStoreOwner: ViewModelStoreOwner)
    : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var historyList: List<StatementHistoryElement> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.history_list_item,
            parent,
            false
        )
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.token.text = historyList[position].token
        holder.date.text = historyList[position].date
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val token = itemView.findViewById<TextView>(R.id.token)!!
        val date = itemView.findViewById<TextView>(R.id.date)!!
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
    fun setHistoryData(historyLocal: List<StatementHistoryElement>) {
        historyList = historyLocal
        notifyDataSetChanged()
    }


}