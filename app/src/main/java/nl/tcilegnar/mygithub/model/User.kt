package nl.tcilegnar.mygithub.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id") @field:SerializedName("id") val id: Long,
    @ColumnInfo(name = "login") @field:SerializedName("login") val loginName: String,
    @ColumnInfo(name = "avatar_url") @field:SerializedName("avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "url") @field:SerializedName("url") val url: String,
    @ColumnInfo(name = "name") @field:SerializedName("name") val name: String,
    @ColumnInfo(name = "company") @field:SerializedName("company") val company: String?,
    @ColumnInfo(name = "location") @field:SerializedName("location") val location: String?,
    @ColumnInfo(name = "email") @field:SerializedName("email") val email: String?,
    @ColumnInfo(name = "bio") @field:SerializedName("bio") val bio: String?,
    @ColumnInfo(name = "blog") @field:SerializedName("blog") val blog: String?,
    @ColumnInfo(name = "public_repos") @field:SerializedName("public_repos") val publicRepos: Int
)
