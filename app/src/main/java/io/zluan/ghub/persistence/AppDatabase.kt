package io.zluan.ghub.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import io.zluan.ghub.model.Account
import io.zluan.ghub.model.AuthToken

@Database(entities = [AuthToken::class, Account::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAuthTokenDao(): AuthTokenDao
    abstract fun getAccountDao(): AccountDao

    companion object {
        const val DATABASE_NAME = "ghub_db"
    }
}
