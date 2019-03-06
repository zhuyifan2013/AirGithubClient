package com.yifan.airgithubclient

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yifan.airgithubclient.data.RepoDataController
import com.yifan.airgithubclient.data.enqueue
import com.yifan.airgithubclient.data.model.Repo
import kotlinx.android.synthetic.main.fragment_repo_list.*


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RepoListFragment.OnListFragmentInteractionListener] interface.
 */
class RepoListFragment : Fragment() {

    private var username:String? = null

    private var listener: OnListFragmentInteractionListener? = null
    private var adapter: RepoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            username = it.getString(ARG_USERNAME)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()

        adapter = RepoListAdapter(mutableListOf(), listener)

        with(repo_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RepoListFragment.adapter
        }

        username?.apply {
            RepoDataController.getGithubService().getRepoList(username!!).enqueue {
                onResponse = {
                    hideLoading()
                    it.body()?.let { list ->
                        adapter?.setData(list)
                    }
                }

                onFailure = {
                    hideLoading()
                }
            }
        }

    }

    private fun showLoading() {
        progress_bar.visibility = VISIBLE
        repo_list.visibility = INVISIBLE
    }

    private fun hideLoading() {
        progress_bar.visibility = INVISIBLE
        repo_list.visibility = VISIBLE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Repo?)
    }

    companion object {

        const val ARG_USERNAME = "username"

        @JvmStatic
        fun newInstance(username : String) =
                RepoListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_USERNAME, username)
                    }
                }
    }
}
