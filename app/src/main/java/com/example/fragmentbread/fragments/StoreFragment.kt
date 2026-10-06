package com.example.fragmentbread.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentbread.R
import com.example.fragmentbread.databinding.FragmentStoreBinding

class StoreFragment : Fragment(R.layout.fragment_store) {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}