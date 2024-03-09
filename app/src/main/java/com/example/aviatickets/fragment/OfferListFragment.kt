package com.example.aviatickets.fragment

import android.bluetooth.BluetoothHidDevice
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.service.FakeService
import com.example.aviatickets.model.network.ApiClient
import com.example.aviatickets.model.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Response


class OfferListFragment : Fragment() {



    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        adapter.setItems(FakeService.offerList)
    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        /**
                         * implement sorting by price
                         */
                    }

                    R.id.sort_by_duration -> {
                        /**
                         * implement sorting by duration
                         */
                    }
                }
            }
        }
    }

    val client = ApiClient.instance
    private val apiService: ApiService = ApiClient.retrofit.create(ApiService::class.java)



    suspend fun fetchOffers(): List<Offer>? = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getOffers()
            if (response.isSuccessful) {
                response.body()
            } else {
                null // or handle errors as needed
            }
        } catch (e: Exception) {
            null // Handle the exception e.g., no internet connection
        }
    }
}