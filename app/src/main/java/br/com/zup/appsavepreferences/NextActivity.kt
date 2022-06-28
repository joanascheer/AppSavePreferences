package br.com.zup.appsavepreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.appsavepreferences.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}