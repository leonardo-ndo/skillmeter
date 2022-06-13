package br.com.lno.skillmeter.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.databinding.FragmentListBinding
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.view.adapter.SkillsAdapter
import br.com.lno.skillmeter.viewmodel.SkillViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SkillListFragment : Fragment(), SkillsAdapter.OnItemClickListener {

    private val skillViewModel by viewModels<SkillViewModel>()

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private val skillsAdapter by lazy {
        SkillsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        binding.rvSkills.adapter = skillsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {

            /**
             * We are using Flow to observe database changes here.
             *
             * To see how to use LiveData, check SkillChartFragment.
             */

            /**
             * When you have only one flow to collect, we can use like this.
             */
            skillViewModel.skillsFlow()
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    skillsAdapter.submitList(it)
                }

//            /**
//             * For multiple collects, use it like this.
//             */
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
//                skillViewModel.skillsFlow().collect {
//                    skillsAdapter.submitList(it)
//                }
//                /**
//                 * Other collects go here
//                 */
//            }
        }
    }

    override fun onListItemClick(skill: Skill) {
        findNavController().navigate(R.id.load_details, bundleOf("skill" to skill))
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