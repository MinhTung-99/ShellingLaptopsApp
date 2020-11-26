package com.example.shellinglaptopapp.ui.bert

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Bert
import com.example.shellinglaptopapp.data.repository.BertRepository
import com.example.shellinglaptopapp.util.Coroutines


class BertViewModel(
    val repository: BertRepository
) : ViewModel(){

    val bert = MutableLiveData<Bert>()
    private val _bert = Bert()
    var progress = MutableLiveData<Int>()
    var txtAnswer = MutableLiveData<Int>()

    private fun getBert(){
        Coroutines.ioThenMain(
            { repository.getBert() },
            {
                _bert.answer = it
                bert.value = _bert
            }
        )
    }

    private fun postBert(bert: Bert){
        Coroutines.ioThenMain(
            { repository.postBert(bert) },
            {
                getBert()
            }
        )
    }

    fun sendMessageOnClick(view: View){
        progress.postValue(0) //View.VISIBLE
        txtAnswer.postValue(8)
        _bert.context = "Hello. My name shop is LAPTOP ABC. My phone number is 0987654321 and it's address is Ha Noi." +
                "I have many laptops. I have the Surface Lap top 3. I have the Surface Lap top 2, I have the Dell Vostro 559070197465," +
                "I have the Dell XPS 13 9300, I have the Laptop Asus ZenBook, I have the Laptop Asus VivoBook, I have the MacBook Air 2020, I have the MacBook Pro 2020."
        postBert(bert = _bert)
    }

    val questionTextWatcher: TextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            _bert.question = p0.toString()
        }
        override fun afterTextChanged(p0: Editable?) {}
    }
}