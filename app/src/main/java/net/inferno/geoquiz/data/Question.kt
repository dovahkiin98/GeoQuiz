package net.inferno.geoquiz.data

import org.json.JSONObject

class Question(
    var text: String = "",
    var type: Int = 0,
    var answer: String = "",
    var options: Array<String> = arrayOf()
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString("Text"),
        jsonObject.getInt("Type"),
        jsonObject.getString("Answer")
    ) {
        val options = jsonObject.getJSONObject("Options")

        for (i in 0 until options.length()) this.options += options.getString("$i")
    }
}