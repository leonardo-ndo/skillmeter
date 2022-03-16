package br.com.lno.skillmeter.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.databinding.ActivityInputBinding
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.viewmodel.SkillViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputSkillActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityInputBinding

    private val skillViewModel: SkillViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSave.setOnClickListener(this)

        intent.getSerializableExtra("skill")?.let {

            val skill = it as Skill

            binding.skillName.setText(skill.name)
            binding.skillLevel.value = skill.level

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View) {

        when (view.id) {

            R.id.bt_save -> {

                when {
                    binding.skillName.text.isNullOrEmpty() -> {

                        Toast.makeText(
                            this,
                            getString(R.string.field_required, getString(R.string.skill_name)),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    binding.skillLevel.value == 0F -> {

                        Toast.makeText(
                            this,
                            getString(
                                R.string.skill_greater_zero,
                                getString(R.string.knowledge_level)
                            ),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {

                        try {

                            if (intent.hasExtra("skill")) {

                                val skill = intent.getSerializableExtra("skill") as Skill

                                skillViewModel.update(
                                    Skill(
                                        id = skill.id,
                                        name = binding.skillName.text.toString(),
                                        level = binding.skillLevel.value
                                    )
                                )

                            } else {
                                skillViewModel.create(
                                    Skill(
                                        name = binding.skillName.text.toString(),
                                        level = binding.skillLevel.value
                                    )
                                )
                            }

                            finish()

                        } catch (e: Exception) {
                            handleException(e)
                        }
                    }
                }
            }
        }
    }

    /**
     * Handling the exceptions.
     *
     * @param exception [Throwable] object to be handled.
     */
    private fun handleException(exception: Throwable) {

        exception.printStackTrace()

        Toast.makeText(applicationContext, exception.message, Toast.LENGTH_LONG).show()

    }
}