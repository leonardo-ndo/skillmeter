package br.com.lno.skillmeter.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.databinding.FragmentListBinding
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.view.activity.InputSkillActivity
import br.com.lno.skillmeter.view.adapter.SkillsAdapter
import br.com.lno.skillmeter.viewmodel.SkillViewModel

class SkillListFragment : Fragment(), SkillsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var skillViewModel: SkillViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        binding = FragmentListBinding.inflate(layoutInflater)

        val adapter = SkillsAdapter(this)

        binding.rvSkills.adapter = adapter

        skillViewModel = ViewModelProvider(this).get(SkillViewModel::class.java)

        skillViewModel.retrieve().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort_level -> {
                skillViewModel.sortBy("level")
                true
            }
            R.id.menu_sort_name -> {
                skillViewModel.sortBy("name")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onListItemClick(skill: Skill) {
        val intent = Intent(context, InputSkillActivity::class.java)
        intent.putExtra("skill", skill)
        startActivity(intent)
    }

    override fun onListItemDeleteClick(skill: Skill) {
        AlertDialog.Builder(requireContext())
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