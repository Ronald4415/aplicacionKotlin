package com.ayala.calculadoraapplication

import android.app.DownloadManager.Request
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class result_revi : AppCompatActivity() {
    var txtnombre: EditText?=null
    var txtapellido: EditText?=null
    var txtlogin: EditText?=null
    var txtcontra: EditText?=null
    var txtcorreo: EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_revi)

        val regresar=findViewById<AppCompatButton>(R.id.regresar)

        regresar.setOnClickListener {
            val intent= Intent(this, PrincipalPru::class.java)
            startActivity(intent)

        }

        txtnombre=findViewById(R.id.txtnombre)
        txtapellido=findViewById(R.id.txtapellido)
        txtlogin=findViewById(R.id.txtlogin)
        txtcontra=findViewById(R.id.txtcontra)
        txtcorreo=findViewById(R.id.txtcorreo)

        val id =intent.getStringExtra("id").toString()

        val queue=Volley.newRequestQueue(this)
        val url="http://192.168.0.6/postman/Listar.php?id=$id"
        val jsonObjectRequest = JsonObjectRequest(
            com.android.volley.Request.Method.GET,url,null,
            { response ->
                Toast.makeText(this, "Listado Correctamente", Toast.LENGTH_LONG).show()
                txtnombre?.setText(response.getString("nombres"))
                txtapellido?.setText(response.getString("apellidos"))
                txtlogin?.setText(response.getString("usuario"))
                txtcontra?.setText(response.getString("contrasena"))
                txtcorreo?.setText(response.getString("correo"))

            }, { error ->
                    Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}