package com.akshat.eydemo.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akshat.eydemo.R
import com.akshat.eydemo.viewmodel.TrendingViewModel
import com.akshat.eydemo.view.adapter.TrendingAdapter


class FirstFragment : Fragment() {
    var viewModel: TrendingViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root = inflater.inflate(R.layout.fragment_first, container, false)
        viewModel = ViewModelProvider(this)[TrendingViewModel::class.java]

        var search = root.findViewById<EditText>(R.id.search)
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty()) {
                    viewModel?.search("" + s)
                } else {
                    viewModel?.getTrendingList()
                }
            }
        })
        viewModel!!.message.observe(viewLifecycleOwner) {
            if (it.isNotBlank())
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        viewModel?.gifList?.observe(viewLifecycleOwner) {
            root?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter =
                TrendingAdapter(it, object : TrendingAdapter.LongPress {
                    override fun add(id: String) {
                        viewModel?.addFav(id)
                    }
                })
        }
        return root
    }


}