package com.andresivan.turistadroid.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.andresivan.turistadroid.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class cercademi : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient//Lo que nos dará la ultima ubicacion
    private lateinit var lastLocation:Location//Para obtener la ultima localización del usuario
    private lateinit var map:GoogleMap//EL mapa que se va a mostrar

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        //val sydney = LatLng(-34.0, 151.0)
        //googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        //Aqui indicamos que el mapa que tiene este metodo, va a ser ahora map
        map=googleMap
        //Caracteristicas del mapa
        map.uiSettings.isZoomControlsEnabled=true
        //Se inicia el mapa
        setUpMap()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cercademi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        //Esto lo usaremos para acceder a nuestra unicacion actual
        fusedLocationClient= activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!

    }

    /**
     * En este metodo montamos el mapa y optamos por una opción distina a Dexter para comprobar los permisos para probar mas cosas
     */
    private fun setUpMap(){
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
        }
        //Indicamos que mi localizacion va a estar activa
        map.isMyLocationEnabled=true
        //Aqui conseguimos nuestra ultima localizacion
        activity?.let { fusedLocationClient.lastLocation.addOnSuccessListener(it) { location ->
            //Si nuestra ultima localizacion es distinta de nulo(puede ser que haya interferencias)
            if (location!=null){
                //Indicamos que nuestra ultima localizacion va ser la ultima localizacion nuestra
                lastLocation=location
                //Asignamos al mapa nuestra ubicacion actual,13f es el zoom
                val currentLatLong=LatLng(location.latitude,location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,13f))
            }
        } }


    }

}