package io.zluan.ghub.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.zluan.ghub.model.Account

/** DB object for storing [Account]. */
@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAndReplace(accountDao: Account): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnore(accountDao: Account): Long

//    @Query("SELECT * FROM account WHERE account_id = :account_id")
//    fun searchByAccountId(accountId: Int): LiveData<Account>

    @Query(value = "SELECT * FROM account WHERE username = :username")
    fun searchByEmail(username: String): Account?
}
