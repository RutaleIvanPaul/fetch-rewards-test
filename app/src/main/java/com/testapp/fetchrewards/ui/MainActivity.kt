package com.testapp.fetchrewards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.testapp.fetchrewards.R
import com.testapp.fetchrewards.adapter.PostAdapter
import com.testapp.fetchrewards.models.Item
import com.testapp.fetchrewards.models.RecyclerViewSection
import com.testapp.fetchrewards.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapter = PostAdapter(this)
        recycler_view.adapter =  adapter

        viewModel.returnPostsVM().observe(this, Observer {items ->
            val organisedItems = arrayListOf<Item>()
            val groupedandSorted = items.groupBy { it.listId }.toSortedMap()
            for(key in groupedandSorted.keys){
                organisedItems.addAll(groupedandSorted[key]?.sortedWith (
                    compareBy{it.name}
                )?.filter {
                    !it.name.isNullOrEmpty()
                } as Collection<Item>)
            }
            val regroupedItems = organisedItems.groupBy {
                it.listId
            }.flatMap {
                (header,details) ->
                listOf<RecyclerViewSection>(RecyclerViewSection.Header(header.toString())) +
                        details.map {
                            RecyclerViewSection.ItemRV(
                                it.id,
                                it.name
                            )
                        }
            }
            adapter.update(regroupedItems as ArrayList<RecyclerViewSection>)
        })

    }
}