package com.akshat.eydemo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.akshat.eydemo.R
import com.bumptech.glide.Glide
import com.akshat.eydemo.model.trendingModel.Data

class TrendingAdapter(private val mList: List<Data>, val mLongPress: LongPress) :
    RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 80f
        circularProgressDrawable.start()
        Glide
            .with(holder.itemView.context)
            .load(ItemsViewModel.images.original.url)
            .fitCenter()
            .placeholder(circularProgressDrawable)
            .into(holder.figLoder)

        holder.delete.visibility = View.GONE
        holder.add.setOnClickListener {
            mLongPress.add(ItemsViewModel.images.original.url)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val figLoder: ImageView = itemView.findViewById(R.id.figLoder)
        val delete: ImageView = itemView.findViewById(R.id.delete)
        val add: ImageView = itemView.findViewById(R.id.add)
    }

    interface LongPress {
        fun add(id: String)
    }

}
