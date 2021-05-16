package com.davidbustos.animalesdifundekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_animales.*

class AnimalesActivity : AppCompatActivity() {

    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("animales")

    var animalesAdapter:AnimalesAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animales)

        setUpRecyclerView()

    }

    fun setUpRecyclerView(){
        val query: Query =collectionReference;
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Animal> = FirestoreRecyclerOptions.Builder<Animal>()
            .setQuery(query,Animal::class.java)
            .build();

        animalesAdapter = AnimalesAdapter(firestoreRecyclerOptions);

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = animalesAdapter

    }

    override fun onStart(){
        super.onStart()
        animalesAdapter!!.startListening()


    }
    override fun onDestroy(){
        super.onDestroy()
        animalesAdapter!!.stopListening()


    }

}