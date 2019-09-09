package nl.tcilegnar.mygithub.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey @field:SerializedName("id") val id: Long,
    @field:SerializedName("login") val userName: String,
    @field:SerializedName("avatar_url") val avatarUrl: String?,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("company") val company: String?,
    @field:SerializedName("location") val location: String?,
    @field:SerializedName("email") val email: String?,
    @field:SerializedName("bio") val bio: String?,
    @field:SerializedName("public_repos") val publicRepos: Int
)
