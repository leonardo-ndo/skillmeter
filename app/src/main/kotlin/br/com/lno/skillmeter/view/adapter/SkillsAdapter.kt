package br.com.lno.skillmeter.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.model.Skill
import com.google.android.material.progressindicator.LinearProgressIndicator

class SkillsAdapter(private val itemClickListener: OnItemClickListener?) :
    ListAdapter<Skill, SkillsAdapter.ViewHolder>(SkillsDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.skill_row, parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class ViewHolder(itemView: View, private val itemClickListener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {

        /**
         * Binds the [Skill] object to the views.
         *
         * @param skill [Skill] object to be bound.
         */
        fun bindView(skill: Skill) {

            itemView.setOnClickListener {
                itemClickListener?.onListItemClick(skill)
            }

            itemView.findViewById<ImageView>(R.id.iv_delete).setOnClickListener {
                itemClickListener?.onListItemDeleteClick(skill)
            }

            val tvSkillName = itemView.findViewById<TextView>(R.id.tv_skill_name)
            val tvSkillLevel = itemView.findViewById<LinearProgressIndicator>(R.id.pb_skill_level)

            tvSkillName.text = skill.name
            tvSkillLevel.progress = skill.level.toInt() * 10
        }
    }

    interface OnItemClickListener {
        /**
         * Action performed when an item of the list is clicked
         */
        fun onListItemClick(skill: Skill)

        /**
         * Action performed when the delete item of the list is clicked
         */
        fun onListItemDeleteClick(skill: Skill)
    }

    class SkillsDiff : DiffUtil.ItemCallback<Skill>() {

        override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
            return oldItem == newItem
        }
    }
}