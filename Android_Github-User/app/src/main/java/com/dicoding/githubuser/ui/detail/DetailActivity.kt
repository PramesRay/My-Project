package com.dicoding.githubuser.ui.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.githubuser.R
import com.dicoding.githubuser.data.database.UserFavorite
import com.dicoding.githubuser.databinding.ActivityDetailBinding
import com.dicoding.githubuser.ui.detail.social.SectionsPagerAdapter
import com.dicoding.githubuser.utils.Helper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private val mContext = this@DetailActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailViewModel = obtainViewModel(mContext)

        val username = intent.getStringExtra("USERNAME") ?: ""
        detailViewModel.findDetailUser(username)
        detailViewModel.checkFavorite(username).observe(this) { data ->
            // cek apakah user pernah like
            if (data != null) {
                detailViewModel.isFavorite.postValue(true)
            } else {
                detailViewModel.isFavorite.postValue(false)
            }
        }

        detailViewModel.detailUser.observe(this) { detailUser ->
            if (detailUser != null) {
                binding.tvName.text = detailUser.name
                binding.tvLogin.text = detailUser.login
                binding.tvFollower.text = buildString {append(detailUser.followers)
                                                        append(" followers")}
                binding.tvFollowing.text = buildString {append(detailUser.following)
                                                        append(" following")}
                Glide.with(this)
                    .load(detailUser.avatarUrl)
                    .circleCrop()
                    .into(binding.imgUser)

                val favorite = UserFavorite(
                    detailUser.login,
                    detailUser.avatarUrl
                )
                detailViewModel.initFavorite(favorite)
            }
        }

        sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = username

        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Followers"
                1 -> "Following"
                else -> ""
            }
        }.attach()

        detailViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        detailViewModel.error.observe(
            this
        ) { Helper.toast(this, it) }

        detailViewModel.isFavorite.observe(this) { isFavorite ->
            if (isFavorite) {
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
            } else {
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }

        binding.fabFavorite.setOnClickListener {
            if (detailViewModel.isFavorite.value == true) {
                MaterialAlertDialogBuilder(mContext)
                    .setTitle("Hapus Favorite ?")
                    .setMessage(StringBuilder("@").append("$username sudah ada di favorite. Apakah anda ingin menghapusnya dari favorite?"))
                    .setCancelable(true)
                    .setIcon(R.drawable.baseline_favorite_border_24)
                    .setPositiveButton("Hapus") { _: DialogInterface, _: Int ->
                        detailViewModel.unsetFavorite(username)
                        Helper.toast(mContext, "$username dihapus dari favorite")
                    }
                    .setNegativeButton("Batalkan") { _: DialogInterface, _: Int -> }
                    .show()
            } else {
                detailViewModel.apply {
                    this.userFavorite.value?.let { favorite ->
                        this.setFavorite(favorite)
                        Helper.toast(mContext, "Berhasil menyukai $username")
                    }
                }
            }
        }
    }



    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }
}