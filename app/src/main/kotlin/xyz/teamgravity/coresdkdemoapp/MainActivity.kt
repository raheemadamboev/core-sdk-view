package xyz.teamgravity.coresdkdemoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import xyz.teamgravity.coresdkdemoapp.databinding.ActivityMainBinding
import xyz.teamgravity.coresdkview.view.EdgeToEdgeUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EdgeToEdgeUtil.setup(binding.root)

        binding.apply {
            progress.setProgress(70, true)
            progress.setProgressBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.black))
        }
    }
}