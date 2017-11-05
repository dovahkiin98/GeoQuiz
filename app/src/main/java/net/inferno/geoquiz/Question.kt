package net.inferno.geoquiz

import org.json.JSONObject

class Question(jsonObject: JSONObject) {

    var text = ""
    var type = 0
    var answer = ""
    var options = arrayOf<String>()

    init {
        text = jsonObject.getString("Text")
        type = jsonObject.getInt("Type")
        answer = jsonObject.getString("Answer")
        val options = jsonObject.getJSONArray("Options")

        for (i in 0 until options.length())
            this.options += options.getJSONObject(i).getString("Text")
    }
}