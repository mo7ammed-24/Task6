package com.example.task6.ui.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    val message=MutableLiveData<String>()
    fun sendMessage(mMessage:String){
        message.value=mMessage
    }
}