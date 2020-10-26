package com.testapp.fetchrewards.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testapp.fetchrewards.R
import com.testapp.fetchrewards.models.Item
import com.testapp.fetchrewards.models.RecyclerViewSection
import com.testapp.fetchrewards.models.RowType
import kotlinx.android.synthetic.main.header_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.*

class PostAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = arrayListOf<RecyclerViewSection>()

    fun update(itemlist: ArrayList<RecyclerViewSection>){
        items.addAll(itemlist)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when(RowType.values()[viewType]){
            RowType.Details ->
                PostsViewHolder(
                    LayoutInflater.from(
                        context
                    ).inflate(
                        R.layout.item_layout,
                        parent,
                        false
                    )
                )
            RowType.Header ->
                HeaderViewHolder(
                    LayoutInflater.from(
                        context
                    ).inflate(
                        R.layout.header_layout,
                        parent,
                        false
                    )
                )

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val postItem = items[position]){
            is RecyclerViewSection.ItemRV ->
                (holder as PostsViewHolder).bind(postItem)
            is RecyclerViewSection.Header ->
                (holder as HeaderViewHolder).bind(postItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].rowType.ordinal
    }

}

class PostsViewHolder(private val sectionView: View): RecyclerView.ViewHolder(sectionView){
    fun bind(item: RecyclerViewSection.ItemRV){
    sectionView.id_textview.text = item.id.toString()
    sectionView.name_textview.text = item.name
}
}

class HeaderViewHolder(private val headerView: View): RecyclerView.ViewHolder(headerView){
    fun bind(item: RecyclerViewSection.Header){
    headerView.section_header_textView.text = item.name
}
}
