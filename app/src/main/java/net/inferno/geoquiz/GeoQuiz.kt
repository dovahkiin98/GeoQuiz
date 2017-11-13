package net.inferno.geoquiz

import android.app.Application
import net.inferno.geoquiz.data.QuestionsData

/**
 * Created by Ahmad Sattout on 13/11/2017.
 */

class GeoQuiz : Application() {

    override fun onCreate() {
        super.onCreate()
        QuestionsData.init(this)
    }
}