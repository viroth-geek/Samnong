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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.databinding.FragmentHomeBinding
import com.samnong.app.epoxy.controller.CategoryController
import com.samnong.app.model.CategoryElement
import com.samnong.app.view.order.OrderedFragment
import com.seanghay.statusbar.statusBar

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var controller: CategoryController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.light(false).color(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = CategoryController(viewModel= viewModel, context = requireContext(), object : ItemClickListener {
            override fun onProductCategoryItemClick(categoryElement: CategoryElement) {
                super.onProductCategoryItemClick(categoryElement)
                replaceFragmentOfTag("home_fragment") { OrderedFragment.newInstance() }
            }
        })
        binding.icMenu.setOnClickListener {
            requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout)
                .openDrawer(GravityCompat.START)
        }
        binding.icSearch.setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_messageFragment)
        }

        binding.recyclerView.setController(controller = controller)
        initAction()
        initObservation()

    }

    private fun initAction() {
        viewModel.getCategory(controller)
    }

    private fun initObservation() {
        viewModel.categories.observe(viewLifecycleOwner, {

        })
    }
    private fun <T : Fragment> replaceFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, primaryFragment, tag)
            addToBackStack(primaryFragment::class.java.name)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Fragment> findFragmentByTagOrElse(tag: String, factory: () -> T): T {
        return childFragmentManager.findFragmentByTag(tag) as? T ?: factory()
    }
}