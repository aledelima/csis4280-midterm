package ca.douglascollege.csis4280.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.douglascollege.csis4280.fragments.data.Wine


class WineDetailFragment : Fragment() {

    private lateinit var wine: Wine

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wine_detail, container, false)

        wine = arguments?.getParcelable("selected_wine")!!

        updateView(view)

        return view
    }

    private fun updateView(view: View) {
        view.findViewById<TextView>(R.id.wineDetailTitle).text = wine.title
        view.findViewById<TextView>(R.id.wineDetailPrice).text = "Price: ${wine.price ?: "N/A"}"
        view.findViewById<TextView>(R.id.wineDetailDescription).text = wine.description
        view.findViewById<TextView>(R.id.wineDetailVariety).text = "Variety: ${wine.variety}"
        view.findViewById<TextView>(R.id.wineDetailProvince).text = "Province: ${wine.province ?: "N/A"}"
        view.findViewById<TextView>(R.id.wineDetailCountry).text = "Country: ${wine.country}"
        view.findViewById<TextView>(R.id.wineDetailWinery).text = "Winery: ${wine.winery}"
    }

    fun updateWineDetails(wine: Wine) {
        this.wine = wine
        view?.let { updateView(it) }
    }

    companion object {
        fun newInstance(wine: Wine): WineDetailFragment {
            val fragment = WineDetailFragment()
            val args = Bundle()
            args.putParcelable("selected_wine", wine)
            fragment.arguments = args
            return fragment
        }
    }
}