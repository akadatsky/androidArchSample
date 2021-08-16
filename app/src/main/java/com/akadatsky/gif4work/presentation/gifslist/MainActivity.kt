package com.akadatsky.gif4work.presentation.gifslist

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akadatsky.gif4work.R
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by inject()
    private lateinit var adapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView = findViewById<EditText>(R.id.searchView)
        val gifList = findViewById<RecyclerView>(R.id.gifList)

        searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(searchView.text.toString())
            }
            true
        }

        viewModel.searchResult.observe(this) {
            adapter.results = it
            adapter.notifyDataSetChanged()
        }

        val layoutManager = LinearLayoutManager(this)
        gifList.layoutManager = layoutManager
        adapter = GifAdapter(this)
        gifList.adapter = adapter
        gifList.setRecyclerListener { holder ->
            val gifViewHolder: GifViewHolder = holder as GifViewHolder
            Glide.with(this@MainActivity).clear(gifViewHolder.gifView)
        }
    }

    private fun performSearch(searchText: String) {
        if (searchText.isBlank()) {
            Toast.makeText(this, R.string.search_text_empty, Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.performSearch(searchText)
    }

}