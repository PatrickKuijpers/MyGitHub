package nl.tcilegnar.mygithub.data

import androidx.lifecycle.LiveData
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.api.Resource
import nl.tcilegnar.mygithub.db.UserDao
import nl.tcilegnar.mygithub.model.User
import nl.tcilegnar.mygithub.AppExecutors

class UserRepository(
    private val executors: AppExecutors,
    private val userDao: UserDao,
    private val githubService: GitHubService
) {
    fun loadUser(userName: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(executors) {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User?) = data == null // TODO (PK): once a day / week?
            // TODO (PK): Does avatar link change when new one is uploaded?

            override fun loadFromDb() = userDao.findByLogin(userName)

            override fun createCall() = githubService.getUser(userName)
        }.asLiveData()
    }
}
