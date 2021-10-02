package br.com.lno.skillmeter.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import br.com.lno.skillmeter.model.Skill

class SkillRepository {

    companion object {

        /**
         * Inserts a [Skill] to the database.
         *
         * @param context Application context
         * @param skill [Skill] object to be inserted.
         */
        fun create(context: Context, skill: Skill) {
            SkillDatabase.getDatabase(context).also {
                it.skillDao().create(skill)
            }
        }

        /**
         * Retrieves all [Skill]'s from the database, ordering the results by name ASC
         *
         * @param context Application context
         *
         * @return A [LiveData] object containing a list of [Skill]'s
         */
        fun retrieveOrderByLevelDesc(context: Context): LiveData<List<Skill>> {
            SkillDatabase.getDatabase(context).also {
                return it.skillDao().retrieveOrderByLevelDesc()
            }
        }

        /**
         * Retrieves all [Skill]'s from the database, ordering the results by level DESC.
         *
         * @param context Application context
         *
         * @return A [LiveData] object containing a list of [Skill]'s
         */
        fun retrieveOrderByNameAsc(context: Context): LiveData<List<Skill>> {
            SkillDatabase.getDatabase(context).also {
                return it.skillDao().retrieveOrderByNameAsc()
            }
        }

        /**
         * Updates a Skill in the database.
         *
         * @param context Application context
         * @param skill [Skill] object to be inserted.
         */
        fun update(context: Context, skill: Skill) {
            SkillDatabase.getDatabase(context).also {
                it.skillDao().update(skill)
            }
        }

        /**
         * Deletes a [Skill] from the database.
         *
         * @param context Application context
         * @param skill [Skill] object to be deleted.
         */
        fun delete(context: Context, skill: Skill) {
            SkillDatabase.getDatabase(context).also {
                it.skillDao().delete(skill)
            }
        }
    }
}