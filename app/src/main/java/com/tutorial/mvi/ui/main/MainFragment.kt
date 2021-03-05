package com.tutorial.mvi.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tutorial.mvi.R

class MainFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        mainViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        subscribeObservers()

    }

    private fun subscribeObservers() {
        mainViewModel.dataState.observe(viewLifecycleOwner, { state ->
            println("DEBUG : Data State: $state")
            //Handle Data
            state.data?.let { data->
                data.blogPosts?.let { blogs ->
                    mainViewModel.setBlogListData(blogs)
                }

                data.user?.let { user ->
                    mainViewModel.setUser(user)
                }
            }

            //Handle Error
            state.message?.let { msg ->{

            }

            }

            state.loading.let { loading ->


            }


        })

        mainViewModel.viewState.observe(viewLifecycleOwner, {
            println("DEBUG: View State: $it")
            it.blogPosts?.let {
                //Todo setting blog posts to RecyclerView
            }

            it.user?.let {
                //TODO setting user information
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_get_user -> triggerGetUserEvent()
            R.id.action_get_blog -> triggerGetBlogsEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetBlogsEvent() {
        mainViewModel.setStateEvent(MainStateEvent.GetBlogPostsEvent())
    }

    private fun triggerGetUserEvent() {
        mainViewModel.setStateEvent(MainStateEvent.GetUser("1"))
    }

}