package com.yifan.airgithubclient

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.repo_input.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_input)

        next.setOnClickListener { goNext() }

    }

    private fun goNext() {
        val username = username_et.text.toString()
        if (username.isBlank()) {
            return
        }
        val intent = Intent(applicationContext, RepoListActivity::class.java)
        intent.putExtra(RepoListFragment.ARG_USERNAME, username)
        startActivity(intent)
    }

}
