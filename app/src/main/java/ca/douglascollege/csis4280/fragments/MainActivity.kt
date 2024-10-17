package ca.douglascollege.csis4280.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ca.douglascollege.csis4280.fragments.data.Taster
import ca.douglascollege.csis4280.fragments.data.Wine
class MainActivity : AppCompatActivity(), WineFragment.OnWineSelectedListener, TasterFragment.OnTasterSelectedListener {

    private lateinit var wineList: List<Wine>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = loadJsonFromAssets("winemag-data-v2.json")
        wineList = parseWines(jsonString)

        val spinner: Spinner = findViewById(R.id.type_spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.menu, // Mapped in res/values/strings.xml as "Wines" and "Tasters"
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> { // Show WineFragment
                        val wineFragment = WineFragment()
                        val bundle = Bundle()
                        bundle.putParcelableArrayList("wineList", ArrayList(wineList))  // Pass the wineList
                        wineFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.left_container, wineFragment)
                            .commit()
                    }
                    1 -> {
                        val tasterFragment = TasterFragment()
                        val bundle = Bundle()
                        bundle.putParcelableArrayList("wineList", ArrayList(wineList))  // Pass the wineList
                        tasterFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.left_container, tasterFragment)
                            .commit()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                val wineFragment = WineFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("wineList", ArrayList(wineList))  // Pass the wineList
                wineFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.left_container, wineFragment)
                    .commit()
            }
        }

        if (savedInstanceState == null) {
            val wineFragment = WineFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("wineList", ArrayList(wineList))  // Pass the wineList
            wineFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.left_container, wineFragment)
                .commit()
        }
    }

    override fun onWineSelected(wine: Wine) {
        val wineDetailFragment = WineDetailFragment.newInstance(wine)
        supportFragmentManager.beginTransaction()
            .replace(R.id.right_container, wineDetailFragment)
            .commit()
    }

    override fun onTasterSelected(taster: Taster) {
        val tasterDetailFragment = TasterDetailFragment.newInstance(taster)
        supportFragmentManager.beginTransaction()
            .replace(R.id.right_container, tasterDetailFragment)
            .commit()
    }

    private fun loadJsonFromAssets(fileName: String): String {
        val inputStream = assets.open(fileName)
        return inputStream.bufferedReader().use { it.readText() }
    }
}
