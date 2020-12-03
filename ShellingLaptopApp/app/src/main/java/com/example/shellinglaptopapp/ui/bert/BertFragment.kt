package com.example.shellinglaptopapp.ui.bert

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.databinding.FragmentBertBinding
import kotlinx.android.synthetic.main.fragment_bert.*

class BertFragment: Fragment() {

    private val factory by lazy {
        BertViewModelFactory(context!!)
    }

    private val viewModel by lazy{
        ViewModelProvider(this, factory).get(BertViewModel::class.java)
    }

    private lateinit var adapter: BertAdapter
    val messages = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentBertBinding.inflate(inflater, container, false).also {
        it.lifecycleOwner = this
        it.viewmodel = viewModel
        viewModel.progress.postValue(8)
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BertAdapter(messages)
        rv_message.adapter = adapter

        viewModel.messages.observe(viewLifecycleOwner, Observer {
            messages.clear()
            messages.addAll(it)
            adapter.notifyDataSetChanged()
            viewModel.progress.postValue(8)
        })
    }


}