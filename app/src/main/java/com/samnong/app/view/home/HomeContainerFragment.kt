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
import com.samnong.app.view.message.MessageFragment

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
        viewModel.showDetail.observe(viewLifecycleOwner, { value ->
            if (value) {
                replaceFragmentOfTag("message_fragment") { MessageFragment.newInstance() }
            } else {
                replaceFragmentOfTag("home_fragment") { HomeFragment.newInstance() }
            }
        })
    }


    private fun <T : Fragment> replaceFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, primaryFragment, tag)
            setPrimaryNavigationFragment(primaryFragment)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Fragment> findFragmentByTagOrElse(tag: String, factory: () -> T): T {
        return childFragmentManager.findFragmentByTag(tag) as? T ?: factory()
    }

}