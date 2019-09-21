package nl.tcilegnar.mygithub.ui.repos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import nl.tcilegnar.mygithub.R
import nl.tcilegnar.mygithub.model.Repo

private const val TAG = "ReposFragment"

class ReposFragment : Fragment() {

    private lateinit var reposViewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reposViewModel = ViewModelProviders.of(this).get(ReposViewModel::class.java)
        reposViewModel.repos.observe(this, Observer<List<Repo>> { repos ->
            Log.i(TAG, "Repos found")
            repos.forEach { repo ->
                Log.i(TAG, repo.id.toString())
                Log.i(TAG, repo.name)
            }
        })
        reposViewModel.loadRepos("PatrickKuijpers") // TODO (PK): hardcoded for now, id = 4254366
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }
}