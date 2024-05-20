package com.example.fundinvest.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fundinvest.R

class CustomRecyclerAdapter(val listener: (String) -> Unit) : RecyclerView
.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    private var textList: List<String> = listOf()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val largeTextView: TextView = itemView.findViewById(R.id.textViewLarge)
        val card: CardView = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.card
        holder.largeTextView.text = textList[position]
        holder.card.setOnClickListener{
            listener(holder.largeTextView.text.toString())
        }
    }
    fun setData(trades: List<String>) {
        textList = trades
        notifyDataSetChanged()
    }

    override fun getItemCount() = textList.size

}