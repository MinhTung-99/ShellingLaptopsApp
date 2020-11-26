package com.example.shellinglaptopapp.ui.bert

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.data.network.BertApi
import com.example.shellinglaptopapp.data.repository.BertRepository
import com.example.shellinglaptopapp.databinding.FragmentBertBinding

class BertFragment: Fragment() {

    private val api by lazy {
        BertApi()
    }

    private val repository by lazy {
        BertRepository(api)
    }

    private val factory by lazy {
        BertViewModelFactory(repository)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(BertViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentBertBinding.inflate(inflater, container, false).also {
        it.lifecycleOwner = this
        it.viewmodel = viewModel

        viewModel.progress.postValue(8) //View.GONE
        viewModel.txtAnswer.postValue(8)

        viewModel.bert.observe(viewLifecycleOwner, Observer {bert->
            it.bert = bert
            viewModel.progress.postValue(8) //View.GONE
            viewModel.txtAnswer.postValue(0)
        })

    }.root
}