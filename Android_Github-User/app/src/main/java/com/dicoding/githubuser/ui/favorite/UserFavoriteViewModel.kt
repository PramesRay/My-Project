package com.dicoding.githubuser.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuser.data.database.UserFavorite
import com.dicoding.githubuser.data.repository.UserFavoriteRepository

class UserFavoriteViewModel(application: Application) : ViewModel() {

    private val mUserFavoriteRepository: UserFavoriteRepository =
        UserFavoriteRepository(application)

    fun getAllFavorites(): LiveData<List<UserFavorite>> = mUserFavoriteRepository.getAllFavorites()

    fun unsetFavorite(username:String) {
        mUserFavoriteRepository.unsetFavorite(username)
    }

    fun removeAllFavorites() {
        mUserFavoriteRepository.removeAllFavorites()
    }
}