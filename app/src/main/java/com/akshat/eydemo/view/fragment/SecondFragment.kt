package com.akshat.eydemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akshat.eydemo.R
import com.akshat.eydemo.view.adapter.CustomAdapter
import com.akshat.eydemo.viewmodel.FavViewModel


class SecondFragment : Fragment(), CustomAdapter.LongPress {
    var viewModel: FavViewModel? = null
    var root: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_second, container, false)

        viewModel = ViewModelProvider(this)[FavViewModel::class.java]
        viewModel?.mFavList?.observe(viewLifecycleOwner) {
            println("Rajneesh :- " + it.size)
        }
        viewModel?.getFavList()
        viewModel?.mFavList?.observe(viewLifecycleOwner) {
            root?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter = CustomAdapter(it, this)
        }
        viewModel!!.message.observe(viewLifecycleOwner) {
            if (it.isNotBlank())
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getFavList()
    }

    override fun deleteItem(id: String) {
        viewModel?.delete(id)
        viewModel?.getFavList()
        viewModel?.message?.postValue("Item Deleted")
    }
}