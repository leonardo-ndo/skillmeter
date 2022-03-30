package br.com.lno.skillmeter.model.repository

import android.database.sqlite.SQLiteConstraintException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.lno.skillmeter.model.Skill
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class SkillDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: SkillDatabase

    private lateinit var skillDao: SkillDao

    @Before
    fun before() {
        hiltRule.inject()
        skillDao = database.skillDao()
    }

    @Test
    fun create() = runBlocking {

        val skill1 = Skill(id = 1, name = "skill1", level = 1F)
        val skill2 = Skill(id = 2, name = "skill2", level = 1F)
        val skill3 = Skill(id = 3, name = "skill3", level = 1F)

        skillDao.create(skill1)
        skillDao.create(skill2)
        skillDao.create(skill3)

        val allSkills = skillDao.retrieveFlow().firstOrNull()

        Assert.assertTrue(allSkills?.containsAll(listOf(skill1, skill2, skill3)) ?: false)

    }

    @Test
    fun createSameName() {

        val skill1 = Skill(id = 1, name = "skill1", level = 1F)
        val skill2 = Skill(id = 2, name = "skill1", level = 1F)

        skillDao.create(skill1)

        Assert.assertThrows(SQLiteConstraintException::class.java) {
            skillDao.create(skill2)
        }
    }

    @Test
    fun update() = runBlocking {

        val skill1 = Skill(id = 1, name = "skill1", level = 1F)

        skillDao.create(skill1)

        val updatedSkill = Skill(id = 1, name = "skill2", level = 1F)

        skillDao.update(updatedSkill)

        val allSkills = skillDao.retrieveFlow().firstOrNull()

        Assert.assertTrue(allSkills?.contains(updatedSkill) ?: false)

    }

    @Test
    fun updateSameName() = runBlocking<Unit> {

        val skill1 = Skill(id = 1, name = "skill1", level = 1F)
        val skill2 = Skill(id = 2, name = "skill2", level = 1F)

        skillDao.create(skill1)
        skillDao.create(skill2)

        val updatedSkill = Skill(id = 2, name = "skill1", level = 1F)

        Assert.assertThrows(SQLiteConstraintException::class.java) {
            skillDao.update(updatedSkill)
        }
    }

    @Test
    fun delete() = runBlocking {

        val skill1 = Skill(id = 1, name = "skill1", level = 1F)

        skillDao.create(skill1)

        skillDao.delete(skill1)

        val allSkills = skillDao.retrieveFlow().firstOrNull() ?: listOf()

        Assert.assertTrue(!allSkills.contains(skill1))

    }

    @After
    fun after() {
        database.close()
    }
}