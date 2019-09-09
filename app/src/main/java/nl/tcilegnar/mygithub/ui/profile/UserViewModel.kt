package nl.tcilegnar.mygithub.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.data.UserRepository
import nl.tcilegnar.mygithub.model.User

class UserViewModel(
//    private val repository: UserRepository
) : ViewModel() {
    private val repository: UserRepository = UserRepository(GitHubService.create()) // TODO (PK): inject this

    var user: LiveData<User> = repository.user

    fun loadUser(userName: String) {
        repository.loadUser(userName)
    }
}

