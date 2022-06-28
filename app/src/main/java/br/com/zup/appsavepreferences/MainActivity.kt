package br.com.zup.appsavepreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import br.com.zup.appsavepreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val user = UserModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            getData(user)
        }
    }

    private fun enter() {
        val intent = Intent(this, NextActivity::class.java)
        startActivity(intent)
    }

    private fun getData(user: UserModel) {
        user.name = binding.etName.text.toString()
        user.age = binding.etAge.text.toString()
        enter()
    }

}