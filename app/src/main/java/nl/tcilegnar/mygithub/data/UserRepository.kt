package nl.tcilegnar.mygithub.data

import android.content.Context
import androidx.lifecycle.LiveData
import nl.tcilegnar.mygithub.AppExecutors
import nl.tcilegnar.mygithub.api.GitHubService
import nl.tcilegnar.mygithub.api.Resource
import nl.tcilegnar.mygithub.db.GithubDb
import nl.tcilegnar.mygithub.db.UserDao
import nl.tcilegnar.mygithub.model.User
import nl.tcilegnar.mygithub.util.RateLimiter
import nl.tcilegnar.mygithub.util.SingletonHolder
import java.util.concurrent.TimeUnit

class UserRepository private constructor(
    private val executors: AppExecutors,
    private val userDao: UserDao,
    private val githubService: GitHubService
) {
    companion object : SingletonHolder<UserRepository, Context>({ context ->
        UserRepository(AppExecutors(), GithubDb.getDatabase(context).userDao(), GitHubService.create())
    })

    private val repoListRateLimit = RateLimiter<String>(1, TimeUnit.DAYS)

    fun loadUser(userName: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(executors) {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User?): Boolean {
                // TODO (PK): Does avatar link change when new one is uploaded?
                return data == null || repoListRateLimit.shouldFetch(userName)
            }

            override fun loadFromDb() = userDao.findByLogin(userName)

            override fun createCall() = githubService.getUser(userName)

            override fun onFetchFailed() = repoListRateLimit.reset(userName)
        }.asLiveData()
    }
}
