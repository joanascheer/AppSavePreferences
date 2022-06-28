package br.com.zup.appsavepreferences.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.zup.appsavepreferences.NextActivity
import br.com.zup.appsavepreferences.main.model.UserModel
import br.com.zup.appsavepreferences.databinding.ActivityMainBinding
import br.com.zup.appsavepreferences.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val user = UserModel()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observers()
        viewModel.getSavedUserData()
        binding.button.setOnClickListener {
            getData()
        }

        binding.button.setOnClickListener {
            getData()
        }
    }

    private fun enter() {
        val intent = Intent(this, NextActivity::class.java)
        startActivity(intent)
    }

    private fun getData() {
        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString().toInt()
        val user = UserModel(name, age)
        viewModel.getUserData(user, binding.btnSwitch.isChecked)
        enter()
    }

    private fun observers() {
        viewModel.savedData.observe(this) {
            binding.etName.setText(it.name)
            if (it.age == 35) {
                binding.etAge.setText("")
            } else {
                binding.etAge.setText(it.age.toString())
            }
        }

        viewModel.savedDataFlag.observe(this) {
            binding.btnSwitch.isChecked = it
        }

    }
}