package com.ayala.calculadoraapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Eliminar : AppCompatActivity() {
    var txtnombre: EditText?=null
    var txtapellido: EditText?=null
    var txtlogin: EditText?=null
    var txtcontra: EditText?=null
    var txtcorreo: EditText?=null
    var tvId:TextView?=null
    var id:String?=null
    @SuppressLint("WrongViewCast", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)
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
        id =intent.getStringExtra("id").toString()
        tvId?.setText(id)


        val queue= Volley.newRequestQueue(this)
        val url="http://192.168.0.6/postman/Listar.php?id=$id"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                Toast.makeText(this, "Listado Correctamente", Toast.LENGTH_LONG).show()
                txtnombre?.setText(response.getString("nombres"))
                txtapellido?.setText(response.getString("apellidos"))
                txtlogin?.setText(response.getString("usuario"))
                txtcontra?.setText(response.getString("contrasena"))
                txtcorreo?.setText(response.getString("correo"))

            }, { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun clickBorrar(view:View){
        val url="http://192.168.0.6/postman/borrar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"usuario Eliminado correctamente",Toast.LENGTH_LONG).show()
                val intent= Intent(this, PrincipalPru ::class.java)
                startActivity(intent)
            },
                Response.ErrorListener { error ->
                    Toast.makeText(this,"error al eliminar al usuario $error",Toast.LENGTH_LONG).show()
                }
            ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros=HashMap<String,String>()
                parametros.put("id",id!!)
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}