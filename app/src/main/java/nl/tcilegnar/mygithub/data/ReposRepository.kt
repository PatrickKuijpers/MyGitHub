package nl.tcilegnar.mygithub.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.api.ReposApi
import nl.tcilegnar.mygithub.model.Repo

private const val TAG = "UserRepository"

class ReposRepository(
    private val service: GitHubService
) {
    var repos = MutableLiveData<List<Repo>>()

    fun loadRepos(userName: String) {
        // TODO (PK): get from cache / db
        ReposApi.getRepos(userName, service, { repos ->
            this.repos.value = repos
        }, { error ->
            Log.e(TAG, "fail to get repos: $error")
        })
    }
}
