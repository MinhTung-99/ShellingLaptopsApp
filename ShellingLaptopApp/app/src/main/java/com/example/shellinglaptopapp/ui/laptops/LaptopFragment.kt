package com.example.shellinglaptopapp.ui.laptops

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
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
import com.example.shellinglaptopapp.ui.share.ShareLaptopViewModel
import kotlinx.android.synthetic.main.fragment_laptop.*


class LaptopFragment: Fragment(), RecyclerViewLaptopClickListener {

    private val api by lazy {
        LaptopApi()
    }

    private val repository by lazy {
        LaptopRepository(api)
    }

    private val factory by lazy {
        LaptopViewModelFactory(repository)
    }


    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(LaptopViewModel::class.java)
    }

    private lateinit var adapter: LaptopAdapter

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

        viewModel.getLaptops()

        viewModel.laptops.observe(viewLifecycleOwner, Observer {
            if(it.informationLaptop!!.isEmpty()){
                progress_bar.visibility = View.VISIBLE
                rv_laptop.visibility = View.GONE
            }else {
                progress_bar.visibility = View.GONE
                rv_laptop.visibility = View.VISIBLE

                rv_laptop.layoutManager = GridLayoutManager(context, 2)
                adapter = LaptopAdapter(it.informationLaptop!!, this)
                rv_laptop.adapter = adapter
            }
        })

        search_laptop.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter(p0!!)
                return true
            }

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