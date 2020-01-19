package io.zluan.ghub.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/** User's auth token from Github. One [Account] maps to many [AuthToken]. */
@Entity(
    tableName = "auth_token",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["account_id"],
            childColumns = ["account_pk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AuthToken(
    @Transient
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_pk")
    var account_pk: Int? = -1,

    @field:Json(name = "token")
    @ColumnInfo(name = "token")
    var token: String? = null
)
