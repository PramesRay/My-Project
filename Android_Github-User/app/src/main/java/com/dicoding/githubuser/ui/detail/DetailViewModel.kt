package com.dicoding.githubuser.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuser.data.database.UserFavorite
import com.dicoding.githubuser.data.repository.UserFavoriteRepository
import com.dicoding.githubuser.data.response.DetailUserResponse
import com.dicoding.githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel() {
    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "DetailViewModel"
    }

    init {
        findDetailUser("")
    }

    private val mUserFavoriteRepository = UserFavoriteRepository(application)
    val userData = MutableLiveData<DetailUserResponse>()
    val userFavorite = MutableLiveData<UserFavorite>()
    val error = MutableLiveData<String>()
    val isFavorite = MutableLiveData<Boolean>()

    fun setFavorite(favorite: UserFavorite) {
        mUserFavoriteRepository.setFavorite(favorite)
        isFavorite.postValue(true)
    }

    fun unsetFavorite(username:String) {
        mUserFavoriteRepository.unsetFavorite(username)
        isFavorite.postValue(false)
    }

    // inisialisasi sementara data untuk favorite
    fun initFavorite(favorite: UserFavorite) {
        userFavorite.postValue(favorite)
    }

    // jalankan query untuk check apakah user di like
    fun checkFavorite(username: String): LiveData<UserFavorite> =
        mUserFavoriteRepository.checkFavorite(username)


    fun findDetailUser(username : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailUser.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}