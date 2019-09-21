package nl.tcilegnar.mygithub.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.data.ReposRepository
import nl.tcilegnar.mygithub.model.Repo

class ReposViewModel(
//    private val repository: ReposRepository
) : ViewModel() {
    private val repository: ReposRepository = ReposRepository(GitHubService.create()) // TODO (PK): inject this

    var repos: LiveData<List<Repo>> = repository.repos

    fun loadRepos(userName: String) {
        repository.loadRepos(userName)
    }
}