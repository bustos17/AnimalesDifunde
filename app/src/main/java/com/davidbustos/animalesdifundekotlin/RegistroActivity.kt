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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity() {

    lateinit var emailRegistro:EditText
    lateinit var contrasenaRegistro:EditText
    lateinit var nombreRegistro:EditText
    lateinit var apellidoRegistro:EditText
    lateinit var telefonoRegistro:EditText

    private val auth= Firebase.auth
    //Iniciar Base de Datos
    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        emailRegistro=findViewById<EditText>(R.id.etCorreoElectronicoRegistro)
        nombreRegistro=findViewById<EditText>(R.id.etNombreRegistro)
        apellidoRegistro=findViewById<EditText>(R.id.etApellidoRegistro)
        contrasenaRegistro=findViewById<EditText>(R.id.etContrasenaRegistro)
        telefonoRegistro=findViewById<EditText>(R.id.etTelefonoRegistro)


        var registroRegistro: TextView =findViewById<TextView>(R.id.btnRegistrarNuevoUsuario)
        var atrasRegistro: TextView =findViewById<Button>(R.id.btnVolverRegistro)

        registroRegistro.setOnClickListener(){
            registroUsuarioBD()
            registroAutentificacion()
        }
    }

    private fun registroAutentificacion(){
        if(emailRegistro.text.isNotEmpty() && contrasenaRegistro.text.isNotEmpty()) {
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emailRegistro.text.toString(),
                            contrasenaRegistro.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            var intent=Intent(this,MenuPrincipalActivity::class.java)
                            intent.putExtra("user",emailRegistro.text.toString())
                            startActivity(intent)

                        }else{
                            showAlert()
                        }
                    }

        }
    }


    private fun registroUsuarioBD(){

        db.collection("usuarios").document(emailRegistro.text.toString())
                .set(
                        hashMapOf("nombre" to nombreRegistro.text.toString(),
                                "apellido" to apellidoRegistro.text.toString(),
                                "telefono" to telefonoRegistro.text.toString()

                        )

                )


    }

    private fun showAlert(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}