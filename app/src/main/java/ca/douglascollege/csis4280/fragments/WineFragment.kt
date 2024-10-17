package ca.douglascollege.csis4280.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.douglascollege.csis4280.fragments.data.Wine


class WineFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WineAdapter
    private lateinit var wineList: List<Wine>

    interface OnWineSelectedListener {
        fun onWineSelected(wine: Wine)
    }

    private var callback: OnWineSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as? OnWineSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wine, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewWine)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        wineList = arguments?.getParcelableArrayList("wineList") ?: listOf()

        adapter = WineAdapter(wineList) { selectedWine ->
            callback?.onWineSelected(selectedWine)
        }
        recyclerView.adapter = adapter

        return view
    }
}