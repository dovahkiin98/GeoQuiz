package net.inferno.geoquiz.model

import org.json.JSONObject

class Question(
    var text: String = "",
    var type: Int = 0,
    var answer: String = "",
    var options: MutableList<String> = mutableListOf(),
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString("Text"),
        jsonObject.getInt("Type"),
        jsonObject.getString("Answer")
    ) {
        val options = jsonObject.getJSONObject("Options")

        for (i in 0 until options.length()) this.options.add(options.getString("$i"))

        this.options.shuffle()
    }
}