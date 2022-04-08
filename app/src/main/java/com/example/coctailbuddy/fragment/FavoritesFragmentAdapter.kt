package com.example.coctailbuddy.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coctailbuddy.R
import com.example.coctailbuddy.data.CoctailItem

class FavoritesFragmentAdapter : RecyclerView.Adapter<FavoritesFragmentAdapter.ViewHolder>() {

    private var dataList: List<CoctailItem>? = null
    private lateinit var clickListener: FavoritesFragmentAdapterOnClickListener

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgView: ImageView = itemView.findViewById(R.id.img_view_favorites)
        var drinkName: TextView = itemView.findViewById(R.id.strDrinkfavorites)
    }

    fun setClickListener(listener: FavoritesFragmentAdapterOnClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.favorites_item_row, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.drinkName.text = dataList!![position].strDrink

        Glide.with(holder.itemView.context)
            .load(dataList!![position].strDrinkThumb + "/preview")
            .into(holder.imgView)

        holder.itemView.setOnClickListener {
            clickListener.onClick(dataList!![position])
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataset(dataList: List<CoctailItem>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}
