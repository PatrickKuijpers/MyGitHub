package nl.tcilegnar.mygithub.api

import nl.tcilegnar.mygithub.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val UNKNOWN_ERROR = "unknown error"

class UserApi {
    companion object {
        fun getUser(
            userName: String,
            service: GitHubService,
            onSuccess: (user: User) -> Unit,
            onError: (error: String) -> Unit
        ) {
            service.getUser(userName).enqueue(
                object : Callback<User> {
                    override fun onResponse(
                        call: Call<User>?,
                        response: Response<User>
                    ) {
                        if (response.isSuccessful) {
                            onSuccess(response.body()!!) // TODO (PK): safe?
                        }
                        else {
                            onError(response.errorBody()?.string() ?: UNKNOWN_ERROR)
                        }
                    }

                    override fun onFailure(call: Call<User>?, t: Throwable) {
                        onError(t.message ?: UNKNOWN_ERROR)
                    }
                }
            )
        }
    }
}