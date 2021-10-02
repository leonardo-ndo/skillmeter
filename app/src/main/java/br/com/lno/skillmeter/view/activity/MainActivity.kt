package br.com.lno.skillmeter.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.databinding.ActivityMainBinding
import br.com.lno.skillmeter.view.fragment.SkillChartFragment
import br.com.lno.skillmeter.view.fragment.SkillListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, InputSkillActivity::class.java))
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SkillListFragment()).commit()
                    true
                }
                R.id.menu_chart -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SkillChartFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}