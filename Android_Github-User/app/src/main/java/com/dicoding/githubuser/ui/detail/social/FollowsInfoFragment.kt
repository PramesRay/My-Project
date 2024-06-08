package com.dicoding.githubuser.ui.detail.social

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuser.data.response.ItemsItem
import com.dicoding.githubuser.databinding.FragmentFollowsInfoBinding

class FollowsInfoFragment : Fragment() {
    private var _binding: FragmentFollowsInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var followViewModel: FollowsInfoViewModel
    private var position: Int = 0
    private var username: String? = null

    private var listData: List<ItemsItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }

        followViewModel = ViewModelProvider(this)[FollowsInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollows.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollows.addItemDecoration(itemDecoration)


        if (position == 1) {
            followViewModel.getFollowers(username!!)
            followViewModel.listFollowers.observe(viewLifecycleOwner) { listFollowers ->
                listData = listFollowers
                val adapter = FollowsInfoAdapter()
                adapter.setData(listData)
                binding.rvFollows.adapter = adapter
            }
        } else {
            followViewModel.getFollowing(username!!)
            followViewModel.listFollowing.observe(viewLifecycleOwner) { listFollowing ->
                listData = listFollowing
                val adapter = FollowsInfoAdapter()
                adapter.setData(listData)
                binding.rvFollows.adapter = adapter
            }
        }

        followViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
}