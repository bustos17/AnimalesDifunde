package com.davidbustos.animalesdifundekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AutentificacionActivity : AppCompatActivity() {

    private val auth= Firebase.auth
    lateinit var email:EditText
    lateinit var contrasena:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autentificacion)

        email=findViewById<EditText>(R.id.etEmail)
        contrasena=findViewById<EditText>(R.id.etContrasena)

        var login: Button =findViewById<Button>(R.id.btnEntrar)
        var registro:TextView=findViewById<TextView>(R.id.btnRegistro)
        var salir:TextView=findViewById<Button>(R.id.btnSalirAplicacion)


        login.setOnClickListener(){
            login()
        }

        registro.setOnClickListener(){
            registro()
        }

        salir.setOnClickListener(){

        }
    }


    private fun login(){
        if(email.text.isNotEmpty() && contrasena.text.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.text.toString(),
                    contrasena.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        var intent=Intent(this,MenuPrincipalActivity::class.java)
                        intent.putExtra("user",email.text.toString())
                        startActivity(intent)

                    }else{
                        showAlert()
                    }
                }
        }
    }
    private fun registro(){
        // Toast.makeText(this,"Redireccionado a registro de usuarios",Toast.LENGTH_LONG).show()

        var intent= Intent(this,RegistroActivity::class.java)
        startActivity(intent)

    }
    private fun showAlert(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }
}