package com.example.gmail.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.ItemClickListener
import com.example.gmail.Models.GmailModel
import com.example.gmail.R

class ItemAdapter(val gmails: List<GmailModel>, val listener: ItemClickListener? = null): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int =gmails.size

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val gmail = gmails[position]
        holder.imageAvatar.setImageResource(gmail.avatarResId)
        holder.textName.text = gmail.name
        holder.textContent.text = gmail.content
        holder.textTime.text = gmail.time
        holder.selected.setImageResource(if (gmail.selected) R.drawable.star1 else R.drawable.star0)

        holder.selected.setOnClickListener {
            gmail.toggleSelected()
            notifyItemChanged(position)
        }
    }


    class ItemViewHolder(itemView: View, val listener: ItemClickListener?):RecyclerView.ViewHolder(itemView){
        val imageAvatar: ImageView
        val textName: TextView
        val textContent: TextView
        val textTime: TextView
        val selected: ImageButton

        init {
            imageAvatar = itemView.findViewById(R.id.avatar)
            textName = itemView.findViewById(R.id.textViewName)
            textContent = itemView.findViewById(R.id.textViewContent)
            textTime = itemView.findViewById(R.id.textViewTime)
            selected = itemView.findViewById(R.id.imageButton)

            itemView.setOnClickListener {
                listener?.OnItemClicked(adapterPosition)
            }
        }
    }
}