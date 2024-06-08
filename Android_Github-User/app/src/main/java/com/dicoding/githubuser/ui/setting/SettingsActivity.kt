package com.dicoding.githubuser.ui.setting

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuser.databinding.ActivitySettingsBinding
import com.dicoding.githubuser.utils.SettingPreferences
import com.dicoding.githubuser.utils.ViewModelSettingFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel =
            ViewModelProvider(this, ViewModelSettingFactory(pref))[SettingsViewModel::class.java]


        settingViewModel.getThemeSettings().observe(this) {
            when (it) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    binding.themeAuto.isChecked = true
                }

                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    binding.themeLight.isChecked = true
                }

                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    binding.themeDark.isChecked = true
                }
            }
        }

        binding.themeContainer.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.themeAuto.id -> settingViewModel.saveThemeSetting(0)
                binding.themeLight.id -> settingViewModel.saveThemeSetting(1)
                binding.themeDark.id -> settingViewModel.saveThemeSetting(2)
            }
        }
    }
}