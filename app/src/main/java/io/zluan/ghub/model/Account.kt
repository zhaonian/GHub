package io.zluan.ghub.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/** User's github account for authentication. */
@Entity(tableName = "account")
data class Account(
    @Transient
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id")
    var id: Int,

    @field:Json(name = "username")
    @ColumnInfo(name = "username")
    var username: String? = null,

    @field:Json(name = "password")
    @ColumnInfo(name = "password")
    var password: String? = null
)
