package com.yifan.airgithubclient

import android.os.Bundle
import com.yifan.airgithubclient.data.model.Repo

class RepoListActivity : BaseActivity(),
        RepoListFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_list)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, RepoListFragment.newInstance(intent.getStringExtra(RepoListFragment.ARG_USERNAME)), "repoListFragment")
                .commit()
    }

    override fun onListFragmentInteraction(item: Repo?) {
        // TODO
    }
}