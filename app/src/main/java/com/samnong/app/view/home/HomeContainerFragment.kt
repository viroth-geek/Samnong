package com.samnong.app.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.samnong.app.R
import com.samnong.app.databinding.FragmentHomeContainerBinding
import com.samnong.app.view.order.OrderedFragment

class HomeContainerFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderFragment = OrderedFragment.newInstance()
        val homeFragment = HomeFragment.newInstance()

        replaceFragmentOfTag("home_fragment") { homeFragment }

    }


    private fun <T : Fragment> hideFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            hide(primaryFragment)
            addToBackStack(primaryFragment::class.java.name)
        }
    }

    private fun <T : Fragment> showFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            show(primaryFragment)
            addToBackStack(primaryFragment::class.java.name)
        }
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