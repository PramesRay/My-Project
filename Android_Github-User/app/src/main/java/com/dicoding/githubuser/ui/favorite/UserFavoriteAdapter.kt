package com.dicoding.githubuser.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.githubuser.data.database.UserFavorite
import com.dicoding.githubuser.databinding.ItemRowUserBinding
import com.dicoding.githubuser.utils.callback.UserFavoriteDiffCallback
import com.bumptech.glide.Glide
import com.dicoding.githubuser.ui.detail.DetailActivity


class UserFavoriteAdapter : RecyclerView.Adapter<UserFavoriteAdapter.FavoriteViewHolder>() {

    private val listFavorites = ArrayList<UserFavorite>()

    fun setListFavorites(listFavorites: List<UserFavorite>) {
        val diffCallback = UserFavoriteDiffCallback(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getSwipedUsername(position: Int): String {
        return listFavorites[position].username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }

    inner class FavoriteViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: UserFavorite) {
            with(binding) {
                tvItemName.text = favorite.username ?: favorite.username
                Glide.with(itemView)
                    .load(favorite.avatarUrl)
                    .into(imgItemPhoto)
                itemView.setOnClickListener {
                    val moveDetailActivity = Intent(
                        it.context,
                        DetailActivity::class.java
                    )
                    moveDetailActivity.putExtra("USERNAME", favorite.username)
                    it.context.startActivity(moveDetailActivity)
                }
            }
        }
    }

}