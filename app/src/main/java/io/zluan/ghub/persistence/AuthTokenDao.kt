package io.zluan.ghub.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.zluan.ghub.model.AuthToken

@Dao
interface AuthTokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(authToken: AuthToken): Long

    @Query(value = "UPDATE auth_token SET token = null WHERE account_pk = :pk")
    fun nullifyToken(pk: Int): Int
}
