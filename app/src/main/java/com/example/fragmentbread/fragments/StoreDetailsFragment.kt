package com.example.fragmentbread.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentbread.R
import com.example.fragmentbread.databinding.FragmentStoreDetailsBinding
import com.example.fragmentbread.presenters.StoreDetailsPresenter
import com.example.fragmentbread.views.StoreDetailsView

class StoreDetailsFragment : Fragment(R.layout.fragment_store_details), StoreDetailsView {
    private var _binding: FragmentStoreDetailsBinding? = null
    private val binding get() = _binding!!

    private var _presenter: StoreDetailsPresenter? = null
    private val presenter get() = _presenter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _presenter = StoreDetailsPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onAttachView(this)

        binding.storeImageView.setOnClickListener {
            presenter.toggleFavoriteStore()
        }
    }

    override fun toggleFavoriteStore() {
        binding.storeImageView.isSelected = !binding.storeImageView.isSelected
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        presenter.onDetachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _presenter = null
    }
}