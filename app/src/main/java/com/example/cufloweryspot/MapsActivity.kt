package com.example.cufloweryspot
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.R
import android.widget.RadioButton




class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {
    override fun onStreetViewPanoramaReady(p0: StreetViewPanorama?) {
        p0!!.setPosition(LatLng(latlong[0].toDouble(),latlong[1].toDouble()   ))


          }

    private var latlong: FloatArray = FloatArray(2)
    private lateinit var mMap: GoogleMap
    private lateinit var xMap: StreetViewPanorama
    private lateinit var radioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)
        latlong = intent.getFloatArrayExtra("coord")
        Log.d("latlong",latlong[0].toString())
        radioGroup = findViewById(R.id.ridx)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        normalView()

     //   mapFragment.getMapAsync(this)

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
                mapFragment).commit()
        mapFragment.getMapAsync(this)
    }
    fun panorama()
    {
        val streetViewFragment = SupportStreetViewPanoramaFragment()
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.map,
                streetViewFragment).commit()
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
}
