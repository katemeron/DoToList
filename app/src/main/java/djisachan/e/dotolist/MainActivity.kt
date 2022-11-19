package djisachan.e.dotolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import djisachan.e.dotolist.databinding.MainActivityLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}