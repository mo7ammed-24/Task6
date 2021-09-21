package com.example.task6.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.task6.databinding.FragmentTopBinding
import com.example.task6.ui.util.Constant
import com.example.task6.ui.util.SharedViewModel
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.*

class TopFragment:BaseFragment<FragmentTopBinding>(){

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopBinding=FragmentTopBinding::inflate
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var model=ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        watchEditText(model)
    }

    fun watchEditText(model: SharedViewModel) {

        val replySubject= ReplaySubject.create<String>()

            binding?.textInput?.addTextChangedListener(object :TextWatcher{
                var isTyping=false

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.i("Hamada","before input")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    replySubject.onNext(p0.toString())
                }

                var timer= Timer()
                var delay=1500L

                override fun afterTextChanged(p0: Editable?) {
                    if(!isTyping){isTyping=true}
                    timer.cancel()
                    timer= Timer()
                    timer.schedule(object:TimerTask(){
                        override fun run() {
                            isTyping=false
                            replySubject.subscribe({ requireActivity().runOnUiThread {model.sendMessage(it) } },
                                {e->
                                Log.i("Hamada","${e.message}")
                            })
                        }

                    },delay)
                }

            })
        }



}
