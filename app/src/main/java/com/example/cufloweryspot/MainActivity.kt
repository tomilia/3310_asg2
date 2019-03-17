package com.example.cufloweryspot

import android.content.Intent
import android.media.ExifInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem


class MainActivity : AppCompatActivity() {
    private val items: MutableList<Flowery>  = mutableListOf<Flowery>()
    private var latlong: FloatArray = FloatArray(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initImage()
        var recyclerView = findViewById<RecyclerView>(R.id.floweryrecycler)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = FloweryAdapter(applicationContext,items)
        registerForContextMenu(recyclerView);
    }
    fun initImage(){
       var f1 = Flowery(R.drawable.image01)
        var f2 = Flowery(R.drawable.image02)
        var f3 = Flowery(R.drawable.image03)
        var f4 = Flowery(R.drawable.image04)
        var f5 = Flowery(R.drawable.image05)
        var f6 = Flowery(R.drawable.image06)
        var f7 = Flowery(R.drawable.image07)
        var f8 = Flowery(R.drawable.image08)
        var f9 = Flowery(R.drawable.image09)
        var f10 = Flowery(R.drawable.image10)
        items.add(f1)
        items.add(f2)
        items.add(f3)
        items.add(f4)
        items.add(f5)
        items.add(f6)
        items.add(f7)
        items.add(f8)
        items.add(f9)
        items.add(f10)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val exif = ExifInterface(resources.openRawResource(items[item!!.groupId].imageid))
        if (exif.getLatLong(latlong))
        {

            val intent = Intent(this,MapsActivity::class.java)
            intent.putExtra("coord", latlong)

            Log.d("tpye",item.itemId.toString())
            intent.putExtra("type",item.itemId)
            startActivity(intent);
        }
        return super.onContextItemSelected(item)
    }
}
