package br.com.lno.skillmeter

import android.app.Application
import com.github.mikephil.charting.utils.Utils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SkillmeterApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        Utils.init(this)

    }
}