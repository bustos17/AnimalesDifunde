package com.davidbustos.animalesdifundekotlin

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_subir_imagen.*

class SubirImagenActivity : AppCompatActivity() {

    private val REQUEST_GALLERY= 1001

    private val REQUEST_CAMERA=1002

    //Uri-> identificador de un recurso de nuestro móvil. En este caso una foto
    var foto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_imagen)

        btnGaleria.setOnClickListener {
            mostrarGaleria()
        }
        btnCamara.setOnClickListener {
            abreCamera()
        }
    }

    //Abre la ventana donde se abre la galería de fotos
    private fun mostrarGaleria(){
        val intentGaleria=Intent(Intent.ACTION_PICK)
        intentGaleria.type="image/*"
        startActivityForResult(intentGaleria,REQUEST_GALLERY)
    }


    //Detectamos cuando se pulse el botón para abrir la cámara
    private fun abreCamera(){

        //ContentValues - Manejador de contenidos - Crea un espacio en memoria que rellenará con los bits de la foto
        val value= ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "Nueva imagen")
        foto=contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value)
        val camaraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT,foto)
        startActivityForResult(camaraIntent,REQUEST_CAMERA)


    }

    //Subir imagen de galería o Subir imagen de cámara
    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY){
            iv_galeria.setImageURI(data?.data)
        }
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CAMERA){
            iv_camara.setImageURI(foto)
        }

    }

}