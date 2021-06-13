package com.samnong.app.view.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.samnong.app.databinding.FragmentDetailBinding
import com.samnong.app.epoxy.controller.DetailController
import com.seanghay.statusbar.statusBar

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val controller by lazy {
        DetailController(
            viewModel = viewModel,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.onRestoreInstanceState(savedInstanceState)
        statusBar.light(false).color(Color.TRANSPARENT)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        controller.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailFragmentArgs by navArgs()
        initView()
        initAction(id = args.productId)
    }

    private fun initView() {
        binding.recyclerView.setController(controller)
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAction(id: Int? = null) {
        viewModel.getDetail(controller = controller, id = id!!, context = requireContext())
    }

}