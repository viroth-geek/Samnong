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
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.adapter.ContentAdapter
import com.samnong.app.databinding.FragmentHomeBinding
import com.samnong.app.model.Item
import com.seanghay.statusbar.statusBar

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private val contentAdapter = ContentAdapter(object : ItemClickListener {
        override fun itemClick(item: Item) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(productId = item.id.toInt())
            findNavController().navigate(action)
        }
    })

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
            initAction()
            initObservation()
        }.root
    }

    private fun initView(binding: FragmentHomeBinding) {
        binding.content.adapter = contentAdapter
        binding.icMenu.setOnClickListener {
            requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout)
                .openDrawer(GravityCompat.START)
        }
    }

    private fun initAction() {
        viewModel.getCategory()
    }

    private fun initObservation() {
        viewModel.content.observe(viewLifecycleOwner) {
            contentAdapter.submitList(it)
        }
    }
}