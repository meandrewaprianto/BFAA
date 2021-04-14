package com.andrewaprianto.mybarvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.andrewaprianto.mybarvolume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener(this)

        //Can be using with this method too
//        btnCalculate.setOnClickListener {
//          val inputLength = edtLength.text.toString().trim()
//          val inputWidth = edtWidth.text.toString().trim()
//          val inputHeight = edtHeight.text.toString().trim()
//        }

        if(savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            binding.tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, binding.tvResult.text.toString())
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_calculate) {
            //trim (remove space in inputtext)
            val inputLength = binding.edtLength.text.toString().trim()
            val inputWidth = binding.edtWidth.text.toString().trim()
            val inputHeight = binding.edtHeight.text.toString().trim()

            var isEmptyField = false

            if(inputLength.isEmpty()){
                isEmptyField = true
                binding.edtLength.error = "Field ini tidak boleh kosong!"
            }

            if(inputWidth.isEmpty()){
                isEmptyField = true
                binding.edtWidth.error = "Field ini tidak boleh kosong!"
            }

            if(inputHeight.isEmpty()){
                isEmptyField = true
                binding.edtHeight.error = "Field ini tidak boleh kosong!"
            }

            if(!isEmptyField){
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                binding.tvResult.text = volume.toString()
            }
        }
    }
}