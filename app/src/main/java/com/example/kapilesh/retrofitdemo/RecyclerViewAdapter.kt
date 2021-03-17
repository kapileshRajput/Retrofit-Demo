package com.example.kapilesh.retrofitdemo

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()


    fun setListData(data:  ArrayList<RecyclerData>){
        this.items = data
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.tv_title
        val tvDescription = itemView.tv_description
        val ivAvatar = itemView.iv_avatar

        fun bind(item: RecyclerData){
            tvTitle.text = item.name
            if (!TextUtils.isEmpty(item.description)){
                tvDescription.text = item.description
            }else{
                tvDescription.text = "No description available."
            }

            val url = item.owner.avatar_url
            Glide.with(ivAvatar)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater)
        }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}