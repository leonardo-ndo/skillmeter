package br.com.lno.skillmeter.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.databinding.ActivityMainBinding
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.view.adapter.SkillsAdapter
import br.com.lno.skillmeter.viewmodel.SkillViewModel

class MainActivity : AppCompatActivity(), SkillsAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var skillViewModel: SkillViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        skillViewModel = ViewModelProvider(this).get(SkillViewModel::class.java)

        val adapter = SkillsAdapter(this)

        binding.rvSkills.adapter = adapter

        binding.fab.setOnClickListener {
            startActivity(Intent(this, InputSkillActivity::class.java))
        }

        skillViewModel.retrieve().observe(this, {
            adapter.submitList(it)
        })
    }

    override fun onListItemClick(skill: Skill) {
        val intent = Intent(this, InputSkillActivity::class.java)
        intent.putExtra("skill", skill)
        startActivity(intent)
    }

    override fun onListItemDeleteClick(skill: Skill) {
        AlertDialog.Builder(this)
            .setTitle(R.string.delete_skill)
            .setMessage(getString(R.string.delete_skill_message, skill.name))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                skillViewModel.delete(skill)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
            .show()
    }
}