package nl.tcilegnar.mygithub.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.api.Resource
import nl.tcilegnar.mygithub.db.GithubDb
import nl.tcilegnar.mygithub.model.User
import nl.tcilegnar.mygithub.data.UserRepository
import nl.tcilegnar.mygithub.util.AbsentLiveData

class UserViewModel(
    application: Application
//    private val repository: UserRepository // TODO (PK): inject this
) : AndroidViewModel(application) {
    private val repository: UserRepository =
        UserRepository(GithubDb.getDatabase(application).userDao(), GitHubService.create())

    private val _login = MutableLiveData<String>()

    val login: LiveData<String>
        get() = _login

    fun setLogin(login: String?) {
        if (_login.value != login) {
            _login.postValue(login)
        }
    }

    val userResource: LiveData<Resource<User>> = Transformations.switchMap(_login) { login ->
        if (login == null) {
            AbsentLiveData.create()
        }
        else {
            repository.loadUser(login)
        }
    }

    val user: LiveData<User> = Transformations.switchMap(userResource) { userResource ->
        if (userResource == null) {
            AbsentLiveData.create()
        }
        else {
            MutableLiveData(userResource.data)
        }
    }
}

