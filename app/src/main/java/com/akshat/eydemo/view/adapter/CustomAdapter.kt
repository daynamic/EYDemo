package com.akshat.eydemo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.akshat.eydemo.R
import com.akshat.eydemo.model.FavModel
import com.bumptech.glide.Glide


class CustomAdapter(private val mList: List<FavModel>, val mLongPress: LongPress) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

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
            .load(ItemsViewModel.id)
            .fitCenter()
            .placeholder(circularProgressDrawable)
            .into(holder.figLoder)
        holder.add.visibility = View.GONE
        holder.delete.setOnClickListener {
            mLongPress.deleteItem(ItemsViewModel.id)
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
        fun deleteItem(id: String)
    }
}
