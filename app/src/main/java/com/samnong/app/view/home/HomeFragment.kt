package com.samnong.app.view.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.samnong.app.R
import com.samnong.app.adapter.ContentAdapter
import com.samnong.app.databinding.FragmentHomeBinding
import com.samnong.app.view.message.MessageFragment
import com.seanghay.statusbar.statusBar

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private val contentAdapter = ContentAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.light(false).color(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).apply {
            initView(binding = this)
            initObservation()
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView(binding: FragmentHomeBinding) {

        binding.content.adapter = contentAdapter
        binding.icMenu.setOnClickListener {
            requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout)
                .openDrawer(GravityCompat.START)
        }
        binding.icSearch.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_messageFragment)
        }
    }

    private fun initObservation() {
        viewModel.content.observe(viewLifecycleOwner) {
            contentAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}