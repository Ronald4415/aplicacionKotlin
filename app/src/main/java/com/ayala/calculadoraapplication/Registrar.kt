package com.ayala.calculadoraapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Registrar : AppCompatActivity() {
    var txtnombre:EditText?=null
    var txtapellido:EditText?=null
    var txtlogin:EditText?=null
    var txtcontra:EditText?=null
    var txtcorreo:EditText?=null
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)
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
    }
    fun cargarDatos(view: View){
        var url="http://192.168.0.6/postman/Clase.php"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost= object : StringRequest (com.android.volley.Request.Method.POST,url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "Registro adicionado corecctamente", Toast.LENGTH_LONG).show()
                    val intent= Intent(this, PrincipalPru ::class.java)
                    startActivity(intent)

                },Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>?
            {
                val parametros=HashMap<String, String>()
                parametros.put("nombres",txtnombre?.text.toString())
                parametros.put("apellidos",txtapellido?.text.toString())
                parametros.put("usuario",txtlogin?.text.toString())
                parametros.put("contrasena",txtcontra?.text.toString())
                parametros.put("correo",txtcorreo?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
        }
}


