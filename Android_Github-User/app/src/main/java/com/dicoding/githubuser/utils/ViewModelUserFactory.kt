package com.dicoding.githubuser.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuser.ui.detail.DetailViewModel
import com.dicoding.githubuser.ui.favorite.UserFavoriteViewModel

class ViewModelUserFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelUserFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelUserFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelUserFactory::class.java) {
                    INSTANCE = ViewModelUserFactory(application)
                }
            }
            return INSTANCE as ViewModelUserFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserFavoriteViewModel::class.java)) {
            return UserFavoriteViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}