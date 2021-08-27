package com.excercise.bareksatest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.excercise.bareksatest.databinding.FragmentDanaKelolaanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DanaKelolaanFragment : Fragment() {
    private var _binding: FragmentDanaKelolaanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDanaKelolaanBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        const val TAG = "DanaKelolaanFragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}