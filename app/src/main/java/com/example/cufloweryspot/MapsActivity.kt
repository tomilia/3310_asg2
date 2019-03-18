package com.example.cufloweryspot
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.widget.RadioButton
import android.widget.Toast


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {
    override fun onStreetViewPanoramaReady(p0: StreetViewPanorama?) {
        p0!!.setPosition(LatLng(latlong[0].toDouble(),latlong[1].toDouble()))


          }

    private var latlong: FloatArray = FloatArray(2)
    private lateinit var mMap: GoogleMap
    private lateinit var xMap: StreetViewPanorama
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioMap: RadioButton
    private lateinit var radioPano: RadioButton
    private var map_type: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_maps)
        latlong = intent.getFloatArrayExtra("coord")
        map_type = intent.getIntExtra("type",0)
        radioMap = findViewById<RadioButton>(R.id.normalmap)
        radioPano = findViewById<RadioButton>(R.id.streetview)
        Log.d("latlong", latlong[0].toString()+" "+latlong[1].toString())
        radioGroup = findViewById(R.id.ridx)
        radioGroup.clearCheck()
        when(map_type)
        {
            0-> {
                radioGroup.check(radioMap.id)
                normalView()
            }
            1->{
                radioGroup.check(radioPano.id)
                panorama()
            }
        }
    }
    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
        when(radioGroup.indexOfChild(radio))
        {
            0->normalView()
            1->panorama()
        }

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    fun normalView()
    {
        val mapFragment = SupportMapFragment()
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.map,
                mapFragment,"normal_map").addToBackStack("normal_map").commit()
        Log.d("fragcount",supportFragmentManager.backStackEntryCount.toString())
        mapFragment.getMapAsync(this)
    }
    fun panorama()
    {

        val streetViewFragment = SupportStreetViewPanoramaFragment()
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.map,
                streetViewFragment,"street_view").addToBackStack("street_view").commit()
        Log.d("fragcount",supportFragmentManager.backStackEntryCount.toString())
        streetViewFragment.getStreetViewPanoramaAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(latlong[0].toDouble(), latlong[1].toDouble())
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18.0f))


    }

    override fun onBackPressed() {

        if(getSupportFragmentManager().backStackEntryCount>1) {
            getSupportFragmentManager().popBackStack()
            var index = getSupportFragmentManager().backStackEntryCount-2
            val backEntry = supportFragmentManager.getBackStackEntryAt(index)
            val tag = backEntry.name
            when(tag)
            {
                "normal_map"->radioGroup.check(radioMap.id)
                "street_view"->radioGroup.check(radioPano.id)
            }
            Log.d("tagx",tag)



        }
        else {
            super.onBackPressed()
            finish()

        }
    }

}
