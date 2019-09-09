package nl.tcilegnar.mygithub.api

import nl.tcilegnar.mygithub.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val UNKNOWN_ERROR = "unknown error"

class ReposApi {
    companion object {
        fun getRepos(
            userName: String,
            service: GitHubService,
            onSuccess: (repos: List<Repo>) -> Unit,
            onError: (error: String) -> Unit
        ) {
            service.getRepos(userName).enqueue(
                object : Callback<List<Repo>> {
                    override fun onResponse(
                        call: Call<List<Repo>>?,
                        response: Response<List<Repo>>
                    ) {
                        if (response.isSuccessful) {
                            val repos = response.body() ?: emptyList()
                            onSuccess(repos)
                        }
                        else {
                            onError(response.errorBody()?.string() ?: UNKNOWN_ERROR)
                        }
                    }

                    override fun onFailure(call: Call<List<Repo>>?, t: Throwable) {
                        onError(t.message ?: UNKNOWN_ERROR)
                    }
                }
            )
        }
    }
}