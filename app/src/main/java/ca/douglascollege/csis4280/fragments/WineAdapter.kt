package ca.douglascollege.csis4280.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.douglascollege.csis4280.fragments.data.Wine

class WineAdapter(
    private val wineList: List<Wine>,
    private val clickListener: (Wine) -> Unit
) : RecyclerView.Adapter<WineAdapter.WineViewHolder>() {

    class WineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.wineTitle)
        val priceTextView: TextView = itemView.findViewById(R.id.winePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wine, parent, false)
        return WineViewHolder(view)
    }

    override fun onBindViewHolder(holder: WineViewHolder, position: Int) {
        val wine = wineList[position]
        holder.titleTextView.text = wine.title
        holder.priceTextView.text = "Price: ${wine.price ?: "N/A"}"

        holder.itemView.setOnClickListener {
            clickListener(wine)
        }
    }

    override fun getItemCount(): Int = wineList.size
}