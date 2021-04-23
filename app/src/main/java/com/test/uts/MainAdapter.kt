package com.test.uts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(private val onclickListener: OnClickListener) :
    ListAdapter<MyModel, MainAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onclickListener)
    }

    class ViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MyModel, onclickListener: OnClickListener) {
            Glide.with(itemView.context).load(item.gambarMobil).into(itemView.iv_main)
            itemView.tv_title.text = item.namaMobil
            itemView.tv_subtitle.text = item.jenisMobil
            itemView.tv_desc.text = item.deskripsiMobil
            itemView.tv_price.text = "Price : \$${item.hargaHarian}/day"
            itemView.tv_additional.text =
                "Seat: ${item.jumlahTempatDuduk} Bag: ${item.jumlahBagasi}"

            itemView.setOnClickListener {
                onclickListener.onClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_main, parent, false)
                return ViewHolder(view)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<MyModel>() {
        override fun areItemsTheSame(oldItem: MyModel, newItem: MyModel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MyModel, newItem: MyModel) =
            oldItem.id == newItem.id
    }

    interface OnClickListener {
        fun onClick(item: MyModel)
    }
}
