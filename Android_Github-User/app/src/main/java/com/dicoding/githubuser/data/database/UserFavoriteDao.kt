package com.dicoding.githubuser.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun setFavorite(favorite: UserFavorite)

    @Query("DELETE FROM UserFavorite WHERE username = :username")
    fun unsetFavorite(username: String)

    @Query("DELETE FROM UserFavorite")
    fun removeAllFavorites()

    @Query("SELECT * from UserFavorite")
    fun getAllFavorites(): LiveData<List<UserFavorite>>

    @Query("SELECT * FROM UserFavorite WHERE username = :username")
    fun checkFavorite(username: String): LiveData<UserFavorite>
}