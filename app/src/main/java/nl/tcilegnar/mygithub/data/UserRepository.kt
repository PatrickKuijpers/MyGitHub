package nl.tcilegnar.mygithub.data

import androidx.lifecycle.LiveData
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.api.Resource
import nl.tcilegnar.mygithub.db.UserDao
import nl.tcilegnar.mygithub.model.User


class UserRepository(
    private val userDao: UserDao,
    private val githubService: GitHubService
) {
    fun loadUser(userName: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>() {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User?) = data == null

            override fun loadFromDb() = userDao.findByLogin(userName)

            override fun createCall() = githubService.getUser(userName)
        }.asLiveData()
    }
}
