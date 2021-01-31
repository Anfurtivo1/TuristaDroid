package com.andresivan.turistadroid.actividades

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.sesion.Sesion
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.sesion.SesionDTO
import com.andresivan.turistadroid.entidades.sesion.SesionMapeado
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.entidades.usuario.UsuarioControlador
import com.andresivan.turistadroid.entidades.usuario.UsuarioDTO
import com.andresivan.turistadroid.entidades.usuario.UsuarioMapeado
import com.andresivan.turistadroid.entidades.usuario.servicios.UsuarioAPI
import com.andresivan.turistadroid.entidades.usuario.servicios.UsuarioClient
import com.andresivan.turistadroid.utils.CifradorContrasena
import com.andresivan.turistadroid.utils.servicios.MisSitiosAPI
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.*
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class Login : AppCompatActivity() {

    private lateinit var usuario: Usuario
    private lateinit var sesionRemota: Sesion
    private val tiempoConexionSesion = 180
    private var hayUsuarioActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPermisos()

        login_link_registrarse.setOnClickListener { abrirRegistrarse() }
        btnIniciarSesion.setOnClickListener { iniciarSesion() }

        initUI()

    }

    private fun initUI() {
        cargarSesion()
    }

    private fun cargarSesion() {
        try {
            //usuario = SesionController.getSesionActual(this)!!
            //comprobarSesionesActivas(usuario)
        }catch (ex: Exception){
            Log.i("LOGIN", "ERRORES: " + ex.localizedMessage)
        }
    }

    private fun comprobarSesionesActivas(usuario: Usuario) {
        val clienteREST = MisSitiosAPI.service
        val call: Call<SesionDTO> = clienteREST.getSesionByID(usuario.id)
        call.enqueue((object :retrofit2.Callback<SesionDTO>{
            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful){
                    Log.i("TuristaREST", "getSesionByID RESPUESTA OK")
                    var sesionRemota = SesionMapeado.fromDTO(response.body() as SesionDTO)
                    sesionRemota = Sesion()
                    sesionRemota.fromSesion(sesionRemota)
                    // Si la obtiene comparamos
                    compararSesiones()
                }
            }

            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }

        }))
    }

    private fun compararSesiones() {
        val tiempo_actual =Instant.now()
        val tiempo_sesion = Instant.parse(sesionRemota.tiempo_acceso)
        val segundosDiferencia = ChronoUnit.SECONDS.between(tiempo_sesion,tiempo_actual)
        /*if (segundosDiferencia <= tiempoConexionSesion ){
            (this.application as MyApp).SESION_USUARIO = usuario
            actualizarSesion()
            abrirPantallaPrincipal()
        }*/
    }

    private fun actualizarSesion() {
        sesionRemota.tiempo_acceso = Instant.now().toString()
        val sesionDTO = SesionMapeado.toDTO(sesionRemota)
        val clienteREST = MisSitiosAPI.service
        val call: Call<SesionDTO> = clienteREST.updateSesion(sesionRemota.idUsuarioActivo, sesionDTO)
        call.enqueue((object : Callback<SesionDTO> {

            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful) {
                    Log.i("TuristaREST", "updateSesion RESPUESTA OK")
                }
            }

            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }
        }))
    }

    private fun initPermisos() {
        if (!(this.application as MyApp).PERMISOS)
            (this.application as MyApp).initPermisos()
    }

    private fun abrirRegistrarse() {
        val intent = Intent(this, Registrarse::class.java)
        startActivity(intent)
    }

    private fun iniciarSesion() {

        if (isFormularioRelleno()) {
            var usu = Usuario("999","1","1","1","","")
            var correo = login_et_correo.text.toString()
            var contrasena = login_et_contrasena.text.toString()
            val pass = CifradorContrasena.convertirHash(contrasena)
            usuario = Usuario("1","1","1","1","","")

            UsuarioControlador.obtenerUsuarioById(usu.id)
            if (UsuarioControlador.abrir){
                MyApp.USUARIO_ACTIVO=usu
                (application as MyApp).SESION_USUARIO = usu
                abrirPantallaPrincipal()
            }else{
                print("No te pudiste meter")
            }

        }

        /*
        var CORREO = login_et_correo.text.toString()
        var CONTRASENA = login_et_contrasena.text.toString()

        if (CORREO == "" || CONTRASENA == "") {
            Toast.makeText(
                this,
                "Rellene todos los campos necesarios para iniciar sesión",
                Toast.LENGTH_SHORT
            )
        } else {
            if (iniciarSesionUsuario(this, CORREO, CONTRASENA)) {
                var usuario = selectByCorreo(CORREO)
                if (usuario != null) {
                    Log.i("INICIO SESION", "Id usuario: "+usuario.id)
                    MyApp.USUARIO_ACTIVO = usuario
                    var sesion = Sesion(
                    idUsuarioActivo = usuario.id
                    )
                    SesionController.deleteSesion(sesion.idUsuarioActivo)
                    SesionController.insert(sesion)
                    Log.i("CREACION SESION", sesion.toString())
                    Toast.makeText(this, "ID del usuario: "+usuario.id, Toast.LENGTH_SHORT)
                }
                abrirPantallaPrincipal()
            } else {
                Toast.makeText(this, "Correo o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
         */
    }

    private fun errorMsg() {
        Snackbar.make(
            findViewById(android.R.id.content),
            "MAIL O CORREO INCORRECTO",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun guardarSesion() {
        if (hayUsuarioActivo) {
            eliminarSesionRemotaActiva()
        }
        /*val sesion = Sesion(
            idUsuarioActivo = usuario.id,
            tiempo_acceso = Instant.now().toString(),
            token = UUID.randomUUID().toString()
        )*/
        val clienteREST = MisSitiosAPI.service
        //val call: Call<SesionDTO> = clienteREST.addSesion(SesionMapeado.toDTO(sesion))
/*        call.enqueue((object : retrofit2.Callback<SesionDTO> {

            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful) {
                    Log.i("TuristaREST", "addSesion RESPUESTA OK")
                    (application as MyApp).SESION_USUARIO = usuario
                    abrirPantallaPrincipal()
                }
            }

            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }
        }))*/
    }

    private fun eliminarSesionRemotaActiva() {
        val clienteREST = MisSitiosAPI.service
        /*val call: Call<SesionDTO> = clienteREST.deleteSesion(usuario.id)
        call.enqueue((object : retrofit2.Callback<SesionDTO> {

            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful) {
                    Log.i("TuristaREST", "deleteSesion RESPUESTA OK")
                    hayUsuarioActivo = false
                }
            }

            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }
        }))*/
    }

    private fun isFormularioRelleno(): Boolean {

        var valorDevuelto = true

        if (login_et_correo.text!!.isEmpty()) {
            login_et_correo.error = "Campo vacío"
            valorDevuelto = false
        }

        if (login_et_contrasena.text!!.isEmpty()) {
            login_et_contrasena.error = "Campo vacío"
            valorDevuelto = false
        }

        return valorDevuelto

    }

    private fun abrirPantallaPrincipal() {
        val intent = Intent(this, PantallaPrincipal::class.java)
        startActivity(intent)
    }


    /**
     * En este metodo lo que hacemos es que eliminamos los fragment de la pila y cuando no haya nos llama al metodo de confirmarSalir
     */
    override fun onBackPressed() {
        try {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStackImmediate()
            else
                confirmarSalir()
        } catch (ex: Exception) {
            confirmarSalir()
        }
    }

    /**
     * Este es el metodo que se lanzará en el metodo anterior
     */
    fun confirmarSalir() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.app_name))
            .setMessage(getString(R.string.Salir))
            .setPositiveButton(getString(R.string.Si)) { dialog, which -> finish() }
            .setNegativeButton(getString(R.string.No), null)
            .show()
    }

}