package com.chaquo.myapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
//        println("debug_read: this is py instance: $py ")

//        val result = py.run {"python ota.py"}
//        println("debug_read: result: $result")

//        val module = py.getModule("plot")


        val discover_module = py.getModule("discover")
        discover_module.callAttr("main")


        val module = py.getModule("ota")
        println("debug_read: module: $module")

        val file_name = "Main.ino.bin"

        val result = module.callAttr("abc", "47AC1350-635C-C362-B1D8-BBDFA6B2041F", file_name)
        println("debug_read: result: $result")


        findViewById<Button>(R.id.button).setOnClickListener {
            try {

                // python ota.py "47AC1350-635C-C362-B1D8-BBDFA6B2041F" "Main.ino.bin"
//                val result = module.callAttr("abc", "47AC1350-635C-C362-B1D8-BBDFA6B2041F", "Main.ino.bin")
//                println("debug_read: result: $result")
//                val bytes = module.callAttr("plot",
//                                            findViewById<EditText>(R.id.etX).text.toString(),
//                                            findViewById<EditText>(R.id.etY).text.toString())
//                    .toJava(ByteArray::class.java)
//                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//                findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
//
//                currentFocus?.let {
//                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
//                        .hideSoftInputFromWindow(it.windowToken, 0)
//                }
            } catch (e: PyException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}