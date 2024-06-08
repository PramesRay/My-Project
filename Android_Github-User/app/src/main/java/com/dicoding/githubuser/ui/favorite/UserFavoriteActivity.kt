package com.dicoding.githubuser.ui.favorite

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.githubuser.R
import com.dicoding.githubuser.databinding.ActivityFavoriteUserBinding
import com.dicoding.githubuser.utils.Helper
import com.dicoding.githubuser.utils.ViewModelUserFactory
import com.dicoding.githubuser.utils.callback.RecyclerViewSwipeCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserFavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var viewModel: UserFavoriteViewModel
    private lateinit var favoriteAdapter: UserFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteAdapter = UserFavoriteAdapter()
        binding.rvFavorite.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = favoriteAdapter
        }

        viewModel = obtainViewModel(this@UserFavoriteActivity)
        viewModel.getAllFavorites().observe(this) { favoriteList ->
            if (favoriteList != null) {
                favoriteAdapter.setListFavorites(favoriteList)
            }
        }

        val swipeCallback = object : RecyclerViewSwipeCallback() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val username = favoriteAdapter.getSwipedUsername(position)
                binding.rvFavorite.adapter?.let {
                    it.notifyItemRemoved(position)
                    it.notifyDataSetChanged()
                    viewModel.unsetFavorite(username)
                    Toast.makeText(this@UserFavoriteActivity, "$username dihapus dari favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.rvFavorite)

        binding.imgRemoveAll.setOnClickListener {
            if (favoriteAdapter.itemCount > 0) {
                MaterialAlertDialogBuilder(this@UserFavoriteActivity)
                    .setTitle("Hapus Semua Favorite ?")
                    .setMessage("Anda akan menghapus semua data favorite?")
                    .setCancelable(true)
                    .setIcon(R.drawable.baseline_delete_24)
                    .setPositiveButton("Hapus") { _: DialogInterface, _: Int ->
                        viewModel.removeAllFavorites()
                        Helper.toast(this@UserFavoriteActivity, "Data favorite dihapus")
                    }
                    .setNegativeButton("Batalkan") { _: DialogInterface, _: Int -> }
                    .show()
            } else {
                Helper.toast(this@UserFavoriteActivity, "Kamu tidak memiliki data favorite")
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserFavoriteViewModel {
        val factory = ViewModelUserFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UserFavoriteViewModel::class.java]
    }
}