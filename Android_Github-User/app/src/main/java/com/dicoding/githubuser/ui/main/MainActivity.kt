package com.dicoding.githubuser.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuser.R
import com.dicoding.githubuser.data.response.ItemsItem
import com.dicoding.githubuser.databinding.ActivityMainBinding
import com.dicoding.githubuser.ui.detail.DetailActivity
import com.dicoding.githubuser.ui.favorite.UserFavoriteActivity
import com.dicoding.githubuser.ui.setting.SettingsActivity
import com.dicoding.githubuser.ui.setting.SettingsViewModel
import com.dicoding.githubuser.ui.setting.dataStore
import com.dicoding.githubuser.utils.SettingPreferences
import com.dicoding.githubuser.utils.ViewModelSettingFactory

class MainActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel =
            ViewModelProvider(this, ViewModelSettingFactory(pref))[SettingsViewModel::class.java]

        settingViewModel.getThemeSettings().observe(this) {
            when (it) {
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    val query = searchView.text.toString()
                    mainViewModel.searchUser(query)
                    searchView.hide()
                    false
                }
            searchBar.inflateMenu(R.menu.main_option_menu)
            searchBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_favorite -> {
                        val intent = Intent(this@MainActivity, UserFavoriteActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.menu_dark_mode -> {
                        val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }

        supportActionBar?.hide()

        mainViewModel.listUser.observe(this) { listUser ->
            setUserData(listUser)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setUserData(userData: List<ItemsItem>) {
        val adapter = UserAdapter(this)
        adapter.submitList(userData)
        binding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onItemClick(user: ItemsItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("USERNAME", user.login)
        startActivity(intent)
    }
}


