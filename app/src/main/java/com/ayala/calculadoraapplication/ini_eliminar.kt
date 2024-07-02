package com.ayala.calculadoraapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton

class ini_eliminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ini_eliminar)

        val regresar=findViewById<AppCompatButton>(R.id.regresar)

        regresar.setOnClickListener {
            val intent= Intent(this, PrincipalPru::class.java)
            startActivity(intent)
        }
    }
    fun clicVer(view: View){
        var txtid= findViewById<EditText>(R.id.txtid)
        var intent = Intent(this,Eliminar::class.java)
        intent.putExtra("id",txtid.text.toString())
        startActivity(intent)
    }
}