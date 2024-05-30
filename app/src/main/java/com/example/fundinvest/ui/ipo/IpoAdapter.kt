package com.example.fundinvest.ui.ipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.fundinvest.R
import com.example.fundinvest.data.IPOData
class IpoAdapter(private val viewModelStoreOwner: ViewModelStoreOwner)
    : RecyclerView.Adapter<IpoAdapter.IpoViewHolder>() {
    private var ipoList: List<IPOData> = listOf()
    class IpoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image)!!
        val token = itemView.findViewById<TextView>(R.id.token)!!
        val company = itemView.findViewById<TextView>(R.id.company)!!
        val exchange = itemView.findViewById<TextView>(R.id.exchange)!!
        val date = itemView.findViewById<TextView>(R.id.date)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IpoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.ipo_item,
            parent,
            false
        )
        return IpoViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: IpoViewHolder, position: Int) {
       with(holder){
           if (position.mod(2) == 0){
               image.setImageResource(R.drawable.arrow_light)
           }else{
               image.setImageResource(R.drawable.arrow_dark)
           }
           token.text = ipoList[position].symbol
           company.text = ipoList[position].company
           exchange.text = ipoList[position].exchange
           date.text = ipoList[position].date
       }
    }
    fun setIpoList(list:List<IPOData>){
        ipoList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return ipoList.size
    }


}