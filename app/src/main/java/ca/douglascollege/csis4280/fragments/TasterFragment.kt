package ca.douglascollege.csis4280.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.douglascollege.csis4280.fragments.data.Taster
import ca.douglascollege.csis4280.fragments.data.Wine


class TasterFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TasterAdapter
    private lateinit var tasterList: List<Taster>

    interface OnTasterSelectedListener {
        fun onTasterSelected(taster: Taster)
    }

    private var callback: OnTasterSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as? OnTasterSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_taster, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewTaster)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val wineList = arguments?.getParcelableArrayList<Wine>("wineList") ?: listOf()

        tasterList = wineList.map { it.taster }.distinctBy { it.name }

        tasterList = tasterList.filter { it.name != "Unknown Taster" }

        adapter = TasterAdapter(tasterList) { selectedTaster ->
            callback?.onTasterSelected(selectedTaster)
        }
        recyclerView.adapter = adapter

        return view
    }
}
