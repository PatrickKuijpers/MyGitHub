package nl.tcilegnar.mygithub.api

import nl.tcilegnar.mygithub.model.Repo
import nl.tcilegnar.mygithub.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{userName}")
    fun getUser(@Path("userName") userName: String): Call<User>

    @GET("users/{userName}/repos")
    fun getRepos(@Path("userName") userName: String): Call<List<Repo>>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): GitHubService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            return OkHttpClient.Builder().addInterceptor(logger).build()
        }
    }
}