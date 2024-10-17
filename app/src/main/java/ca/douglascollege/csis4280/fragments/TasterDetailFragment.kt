package ca.douglascollege.csis4280.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.douglascollege.csis4280.fragments.data.Taster

class TasterDetailFragment : Fragment() {

    private lateinit var taster: Taster

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_taster_detail, container, false)

        taster = arguments?.getParcelable("selected_taster")!!

        val wineNames = taster.wines.joinToString(separator = "\n") { "- ${it.title}" }

        view.findViewById<TextView>(R.id.tasterWineNameTextView).text = wineNames

        return view
    }

    companion object {
        fun newInstance(taster: Taster): TasterDetailFragment {
            val fragment = TasterDetailFragment()
            val args = Bundle()
            args.putParcelable("selected_taster", taster)
            fragment.arguments = args
            return fragment
        }
    }
}