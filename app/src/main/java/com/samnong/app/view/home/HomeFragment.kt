package com.samnong.app.view.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.databinding.FragmentHomeBinding
import com.samnong.app.epoxy.controller.CategoryController
import com.samnong.app.model.CategoryElement
import com.seanghay.statusbar.statusBar

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.light(false).color(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icMenu.setOnClickListener {
            requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(GravityCompat.START)
        }
        initAction()
        initObservation()
    }

    private fun initAction() {
        viewModel.getCategory()
    }

    private fun initObservation() {

        val controller = CategoryController(object : ItemClickListener {
            override fun onProductCategoryItemClick(categoryElement: CategoryElement) {
                super.onProductCategoryItemClick(categoryElement)
                Toast.makeText(context, "category ${categoryElement.nameKh}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.recyclerView.setController(controller)
        viewModel.product.observe(viewLifecycleOwner, {
            controller.submitProduct(it)
        })
        viewModel.categoryAndItems.observe(viewLifecycleOwner,  {
            print("it ${it.size}")
            controller.submitItem(it)
        })
    }
}