package br.com.lno.skillmeter.view.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.databinding.FragmentChartBinding
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.viewmodel.SkillViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkillChartFragment : Fragment() {

    private val skillViewModel by viewModels<SkillViewModel>()

    private val binding by lazy {
        FragmentChartBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val description = Description()
        description.text = getString(R.string.chart_description, getString(R.string.app_name))

        binding.pieChart.description = description
        binding.pieChart.legend.isEnabled = false

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        /**
         * We are using LiveData to observe database changes here.
         *
         * To see how to use Flow, check SkillListFragment.
         */
        skillViewModel.skillsLiveData().observe(viewLifecycleOwner) {
            setHasOptionsMenu(it.isNotEmpty())
            fillChart(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.chart_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_save_to_gallery -> {
                if (binding.pieChart.saveToGallery("my_skills_graph")) {
                    Toast.makeText(context, R.string.save_success, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, R.string.save_fail, Toast.LENGTH_LONG).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Fills the chart according to [Skill]'s data
     *
     * @param skills List of [Skill]'s
     */
    private fun fillChart(skills: List<Skill>) {

        val pieEntryList = skills.map {
            PieEntry(it.level, it.name)
        }

        val pieDataSet = PieDataSet(pieEntryList, null)
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.asList()
        pieDataSet.valueTextSize = 12f

        val pieData = PieData(pieDataSet)
        binding.pieChart.data = pieData

        binding.pieChart.invalidate()

    }
}