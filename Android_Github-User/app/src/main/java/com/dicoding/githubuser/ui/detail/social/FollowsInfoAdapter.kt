package com.dicoding.githubuser.ui.detail.social


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubuser.data.response.ItemsItem
import com.dicoding.githubuser.databinding.ItemRowUserBinding

class FollowsInfoAdapter : ListAdapter<ItemsItem, FollowsInfoAdapter.ViewHolder>(DIFF_CALLBACK) {
    private var listData: List<ItemsItem>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ItemsItem>?) {
        listData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData?.get(position)
        if (data != null) holder.bind(data)
    }

    override fun getItemCount(): Int = listData?.size?:0

    class ViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemsItem?) {
            binding.tvItemName.text = data?.login
            Glide.with(itemView.context)
                .load(data?.avatarUrl)
                .circleCrop()
                .into(binding.imgItemPhoto)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}