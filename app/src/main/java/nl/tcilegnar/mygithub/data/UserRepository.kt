package nl.tcilegnar.mygithub.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.api.UserApi
import nl.tcilegnar.mygithub.model.User

private const val TAG = "UserRepository"

class UserRepository(
    private val service: GitHubService
) {
    var user = MutableLiveData<User>()

    fun loadUser(userName: String) {
        // TODO (PK): get from cache / db
        UserApi.getUser(userName, service, { user ->
            Log.d(TAG, "User retrieved from API: $userName")
            this.user.value = user
        }, { error ->
            Log.e(TAG, "fail to get user: $error")
        })
    }
}
