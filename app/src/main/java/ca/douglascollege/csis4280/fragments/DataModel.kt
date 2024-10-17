package ca.douglascollege.csis4280.fragments


import ca.douglascollege.csis4280.fragments.data.Taster
import ca.douglascollege.csis4280.fragments.data.Wine
import org.json.JSONArray

fun parseWines(jsonString: String): List<Wine> {
    val jsonArray = JSONArray(jsonString)
    val wineList = mutableListOf<Wine>()
    val tasterMap = mutableMapOf<String, Taster>()

    for (i in 0 until jsonArray.length()) {
        val wineObject = jsonArray.getJSONObject(i)

        val tasterName = if (wineObject.has("taster_name") && !wineObject.isNull("taster_name")) {
            wineObject.getString("taster_name")
        } else {
            "Unknown Taster"
        }

        val tasterTwitter = wineObject.optString("taster_twitter_handle", null)

        val taster = tasterMap.getOrPut(tasterName) {
            Taster(
                name = tasterName,
                twitterHandle = tasterTwitter,
                wines = emptyList()
            )
        }

        val price = if (wineObject.isNull("price")) {
            "N/A"
        } else {
            wineObject.getString("price")
        }

        val wine = Wine(
            title = wineObject.getString("title"),
            price = price,
            description = wineObject.getString("description"),
            variety = wineObject.getString("variety"),
            province = wineObject.getString("province"),
            country = wineObject.getString("country"),
            winery = wineObject.getString("winery"),
            taster = taster
        )

        taster.wines += wine

        wineList.add(wine)
    }

    return wineList
}
