package com.ayala.calculadoraapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class PruebaLayout : AppCompatActivity() {

    var txNombre: EditText? = null
    var txApellido: EditText? = null
    var txUsuario: EditText? = null
    var txPass: EditText? = null
    var txLogin: EditText? = null
    var tblUsuarios: TableLayout? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_layout)

        txNombre = findViewById(R.id.txNombre)
        txApellido = findViewById(R.id.txApellido)
        txUsuario = findViewById(R.id.txUsuario)
        txPass = findViewById(R.id.txPass)
        txLogin = findViewById(R.id.txLogin)
        tblUsuarios = findViewById(R.id.tblUsuarios)
        tblUsuarios?.removeAllViews()
        cargaTabla()
    }
    fun cargaTabla(){
        val url = "http://192.168.0.6/postman/ListarPer.php"
        var queue = Volley.newRequestQueue(this)
        var jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    var jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        var jsonObject = jsonArray.getJSONObject(i)
                        var registro = LayoutInflater.from(this)
                            .inflate(R.layout.activity_tabla_l, null, false)
                        val twNombre = registro.findViewById<View>(R.id.twNombre) as TextView
                        val twApellido =
                            registro.findViewById<View>(R.id.twApellido) as TextView
                        val btnBorrar = registro.findViewById<View>(R.id.btnBorrar)
                        val btnEditar = registro.findViewById<View>(R.id.btnEditar)
                        twNombre.text = jsonObject.getString("nombres")
                        twApellido.text = jsonObject.getString("apellidos")


                        btnBorrar.setOnClickListener {
                            val id = btnBorrar.id
                            eliminarDatos(id)
                            tblUsuarios?.removeView(registro)
                        }
                        btnBorrar.id = jsonObject.getInt("id")
                        btnEditar.id = jsonObject.getInt("id")
                        tblUsuarios?.addView(registro)

                    }
                }, { error ->
                    Toast.makeText(this, "error $error", Toast.LENGTH_LONG).show()
                }
            )
        queue.add(jsonObjectRequest)
    }

    fun cargarDatos(view: View)
    {
        val url="http://192.168.0.6/postman/Clase.php"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object  : StringRequest(com.android.volley.Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Registro adicionado correctamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }){
            override fun getParams():MutableMap<String, String>?{
                val parametros = HashMap<String, String>()
                parametros.put("nombres", txNombre?.text.toString())
                parametros.put("apellidos", txApellido?.text.toString())
                parametros.put("usuario", txUsuario?.text.toString())
                parametros.put("contrasena", txPass?.text.toString())
                parametros.put("correo", txLogin?.text.toString())
                return parametros


            }
        }
        queue.add(resultadoPost)
    }

    fun eliminarDatos(id: Int){
        val url= "http://192.168.0.6/postman/borrar.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost=object : StringRequest(
            Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Registro Eliminado Correctamente", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error Al Eliminar  $error", Toast.LENGTH_LONG).show()
            })  {
            override fun getParams(): MutableMap<String, String>? {
                val parametros=HashMap<String, String>()
                parametros.put("id", id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }

    fun clicEditar(id: Int){
        val url="http://192.168.0.6/postman/editar.php"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Usuario Editado", Toast.LENGTH_LONG).show()
                cargaTabla()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"error al listar$error", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros=HashMap<String,String>()
                parametros.put("id", id.toString())
                parametros.put("nombres", txNombre?.text.toString())
                parametros.put("apellidos", txApellido?.text.toString())
                parametros.put("usuario", txUsuario?.text.toString())
                parametros.put("contrasena", txPass?.text.toString())
                parametros.put("correo", txLogin?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }



}

