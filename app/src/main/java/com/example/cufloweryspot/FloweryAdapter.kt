package com.example.cufloweryspot

import android.content.Context
import android.media.ExifInterface
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.listview_row.view.*

class FloweryAdapter(val context: Context,val item: MutableList<Flowery>)
    : RecyclerView.Adapter<FloweryAdapter.ViewHolder>() {
    private var latlong: FloatArray = FloatArray(2)
    private val items: List<Flowery>

    init {
        items = item
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(items[p1].imageid)


        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.listview_row, p0, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {


        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(this.adapterPosition, v!!.getId(), 0, "Call");//groupId, itemId, order, title
            menu?.add(this.adapterPosition, v!!.getId(), 0, "SMS");

        }

        init {
            itemView.setOnCreateContextMenuListener(this)

        }
        fun bind(value: Int) {
           itemView.iv.setImageResource(value)
        }
    }
}