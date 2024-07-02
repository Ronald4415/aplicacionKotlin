package com.ayala.calculadoraapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class PantallaP : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_p)
        val Crud=findViewById<AppCompatButton>(R.id.Crud)
        val Layout=findViewById<AppCompatButton>(R.id.Layout)


        Crud.setOnClickListener {
            val intent= Intent(this, PrincipalPru::class.java)
            startActivity(intent)

        }
        Layout.setOnClickListener {
            val intent= Intent(this, PruebaLayout::class.java)
            startActivity(intent)

        }

    }
}
