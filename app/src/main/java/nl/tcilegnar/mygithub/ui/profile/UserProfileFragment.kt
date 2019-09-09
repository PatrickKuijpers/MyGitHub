package nl.tcilegnar.mygithub.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import nl.tcilegnar.mygithub.R
import nl.tcilegnar.mygithub.model.User

private const val TAG = "UserProfileFragment"

class UserProfileFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.user.observe(this, Observer<User> { user ->
            Log.i(TAG, "User found")
            Log.i(TAG, user.id.toString())
            Log.i(TAG, user.userName)
        })
        userViewModel.loadUser("PatrickKuijpers") // TODO (PK): hardcoded for now, id = 4254366
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}
