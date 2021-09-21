package com.example.task6.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.task6.R
import com.example.task6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var topFragment=TopFragment()
    var botomFragment=BottomFragment()
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        addFragment(topFragment, R.id.fragment_container_top)
        addFragment(botomFragment,R.id.fragment_container_bottom)

    }

    private fun addFragment(fragment: Fragment, fragmentContainerId :Int) {
        var transaction=supportFragmentManager.beginTransaction()
        transaction.add(fragmentContainerId,fragment)
        transaction.commit()
    }

}