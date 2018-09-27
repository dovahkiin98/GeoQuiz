package net.inferno.geoquiz

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import net.inferno.geoquiz.data.QuestionsData

class GeoQuiz : Application() {

    override fun onCreate() {
        super.onCreate()
        QuestionsData.init(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}