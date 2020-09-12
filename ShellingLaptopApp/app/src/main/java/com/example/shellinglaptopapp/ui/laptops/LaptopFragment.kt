package com.example.shellinglaptopapp.ui.laptops

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Laptop
import com.example.shellinglaptopapp.data.network.LaptopApi
import com.example.shellinglaptopapp.data.repository.LaptopRepository
import com.example.shellinglaptopapp.ui.MainActivity
import com.example.shellinglaptopapp.ui.laptops.detail.DetailLaptopsFragment
import com.example.shellinglaptopapp.ui.laptops.share.ShareLaptopViewModel
import kotlinx.android.synthetic.main.fragment_laptop.*

class LaptopFragment: Fragment(), RecyclerViewClickListener {

    private lateinit var factory: LaptopViewModelFactory
    private lateinit var viewModel: LaptopViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_laptop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).setVisibleToolBar(true)

        val api = LaptopApi()
        val repository = LaptopRepository(api)
        factory = LaptopViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(LaptopViewModel::class.java)
        viewModel.getLaptops()
        viewModel.laptops.observe(viewLifecycleOwner, Observer {
            rv_laptop.layoutManager = GridLayoutManager(context, 2)
            rv_laptop.adapter = LaptopAdapter(it.informationLaptop!!, this)
        })
    }

    override fun onRecyclerViewItemClick(laptop: Laptop) {

        val viewModel = ViewModelProvider(requireActivity()).get(ShareLaptopViewModel::class.java)
        viewModel.laptop.value = laptop

        val fragmentTransaction = activity!!.supportFragmentManager?.beginTransaction()
        val detailLaptopsFragment = DetailLaptopsFragment()
        fragmentTransaction.replace(R.id.fragment, detailLaptopsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}