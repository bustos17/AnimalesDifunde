package com.davidbustos.animalesdifundekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MenuPrincipalActivity : AppCompatActivity() {
    val db=FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        var usuarioBienvenida:TextView=findViewById<TextView>(R.id.txtNombreUsuarioBienvenida)
        var cerrarSesion:TextView=findViewById<TextView>(R.id.btnCerrarSesion)
        val bundle: Bundle? =intent.extras
        var user:String?=bundle?.getString("user")

        usuarioBienvenida.setText(user)

        cerrarSesion.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            //Enviarme a menu de inicio de login
        }

        var btnNuevoAnimal: Button =findViewById<Button>(R.id.btnNuevoAnimal)

        btnNuevoAnimal.setOnClickListener(){
            nuevoAnimal()
        }

        var btnTodosLosAnimales:Button=findViewById<Button>(R.id.btnTodosLosAnimales)

        btnTodosLosAnimales.setOnClickListener(){
            todosLosAnimales()
        }

        var btnSubirImagen:Button=findViewById<Button>(R.id.btnSubirImagen)

        btnSubirImagen.setOnClickListener(){
            subirNuevaImagen()
        }

        var btnMapa:Button=findViewById(R.id.btnMapa)

        btnMapa.setOnClickListener(){
            ubicacionAnimal()
        }

    }

    private fun ubicacionAnimal() {
        var intent= Intent(this,SubirUbicacionAnimalActivity::class.java)
        startActivity(intent)
    }

    private fun nuevoAnimal(){
        var intent= Intent(this,SubirNuevoAnimalActivity::class.java)
        startActivity(intent)

    }

    private fun eliminarAnimal(){


    }
    private fun todosLosAnimales(){
        var intent= Intent(this,AnimalesActivity::class.java)
        startActivity(intent)

    }
    private fun subirNuevaImagen(){
        var intent=Intent(this,SubirImagenActivity::class.java)
        startActivity(intent)
    }

}