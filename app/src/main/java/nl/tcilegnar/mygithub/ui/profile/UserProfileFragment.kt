package nl.tcilegnar.mygithub.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import nl.tcilegnar.mygithub.R
import nl.tcilegnar.mygithub.databinding.FragmentProfileBinding

private const val TAG = "UserProfileFragment"

class UserProfileFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.loadUser("PatrickKuijpers") // TODO (PK): hardcoded for now, id = 4254366
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentProfileBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )
        binding.viewmodel = userViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
