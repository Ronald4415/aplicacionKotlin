package com.ayala.calculadoraapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class PrincipalPru : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_pru)
        val Añadir=findViewById<AppCompatButton>(R.id.Añadir)
        val Editar=findViewById<AppCompatButton>(R.id.Editar)
        val Borrar=findViewById<AppCompatButton>(R.id.Borrar)
        val Listar=findViewById<AppCompatButton>(R.id.Listar)


        Añadir.setOnClickListener {
            val intent= Intent(this, Registrar::class.java)
            startActivity(intent)

        }
        Editar.setOnClickListener {
            val intent= Intent(this, Editar::class.java)
            startActivity(intent)

        }
        Borrar.setOnClickListener {
            val intent= Intent(this, ini_eliminar::class.java)
            startActivity(intent)

        }
        Listar.setOnClickListener {
            val intent= Intent(this, Revisar::class.java)
            startActivity(intent)

        }
    }
}