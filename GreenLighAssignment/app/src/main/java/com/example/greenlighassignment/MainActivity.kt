package com.example.greenlighassignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.greenlighassignment.databinding.ActivityMainBinding
import com.example.greenlighassignment.ui.ZoneActivity

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding?.btn?.setOnClickListener {
            val intent = Intent(this@MainActivity, ZoneActivity::class.java)
            startActivity(intent)
        }
    }


}