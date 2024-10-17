package ca.douglascollege.csis4280.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.douglascollege.csis4280.fragments.data.Taster

class TasterAdapter(
    private val tasterList: List<Taster>,
    private val clickListener: (Taster) -> Unit
) : RecyclerView.Adapter<TasterAdapter.TasterViewHolder>() {

    class TasterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tasterName)
        val twitterTextView: TextView = itemView.findViewById(R.id.tasterTwitter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_taster, parent, false)
        return TasterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasterViewHolder, position: Int) {
        val taster = tasterList[position]
        holder.nameTextView.text = taster.name
        holder.twitterTextView.text = taster.twitterHandle

        holder.itemView.setOnClickListener {
            clickListener(taster)
        }
    }

    override fun getItemCount(): Int = tasterList.size
}