package com.andresivan.turistadroid.ui

import android.Manifest
import android.content.pm.PackageManager
<<<<<<< HEAD
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.utils.CircleTransform
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
=======
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

>>>>>>> main
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
<<<<<<< HEAD
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

=======
import com.google.android.gms.maps.model.LatLng
>>>>>>> main

class cercademi : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient//Lo que nos dará la ultima ubicacion
    private lateinit var lastLocation:Location//Para obtener la ultima localización del usuario
    private lateinit var map:GoogleMap//EL mapa que se va a mostrar
<<<<<<< HEAD
    private lateinit var lugar:Lugar
=======
>>>>>>> main

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
<<<<<<< HEAD
        consultar()
=======
>>>>>>> main
        //Esto lo usaremos para acceder a nuestra unicacion actual
        fusedLocationClient= activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!

    }

<<<<<<< HEAD
    private fun consultar(){
        MyApp.listaLugares = mutableListOf()
        MyApp.idLugares = mutableListOf()
        val db = Firebase.firestore
        db.collection("Lugares")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val votoS=document.data.getValue("Votos").toString()
                    MyApp.idLugares.add(document.id)
                    val votos=votoS.toInt()
                    lugar = Lugar(
                        "",
                        document.data.getValue("NombreLugar").toString(),
                        document.data.getValue("Tipo").toString(),
                        "",
                        document.data.getValue("Latitud").toString(),
                        document.data.getValue("Longitud").toString(),
                        document.data.getValue("idImagen").toString(),
                        votos,
                        false,
                        ""
                    )
                    MyApp.listaLugares.add(lugar)
                    sacarFotoLugar(lugar.imgID)
                }

            }
            .addOnFailureListener { exception ->
                Log.d("Consulta", "Error getting documents: ", exception)
            }

    }


=======
>>>>>>> main
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
<<<<<<< HEAD
                MyApp.posicion=lastLocation
=======
>>>>>>> main
                //Asignamos al mapa nuestra ubicacion actual,13f es el zoom
                val currentLatLong=LatLng(location.latitude,location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,13f))
            }
        } }


    }

<<<<<<< HEAD
    private fun sacarFotoLugar(id: String){
        val db = Firebase.firestore
        //TODO
        db.collection("Imagenes")
            .whereEqualTo("id",id)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("buscarFoto", "${document.id} => ${document.data}")
                    var uri=document.data.getValue("uri").toString()
                    Picasso.get().load(uri).transform(CircleTransform()).resize(100,100).into(object : com.squareup.picasso.Target {
                        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                            if (bitmap != null) run {
                                for (elemento in MyApp.listaLugares) {
                                    val lugarMapa = LatLng(
                                        elemento.latitud.toDouble(),
                                        elemento.longitud.toDouble()
                                    )
                                    map.addMarker(
                                        MarkerOptions()
                                            .position(lugarMapa)
                                            .title(elemento.nombre)
                                            .snippet("Lugar del tipo " + elemento.tipo + " con una valoración de " + elemento.valoracion)
                                    )
                                        .setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                }
                            }
                        }

                        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                    })
                }
            }
            .addOnFailureListener { exception ->
                Log.w("buscarFoto", "Error getting documents: ", exception)
            }
    }

=======
>>>>>>> main
}