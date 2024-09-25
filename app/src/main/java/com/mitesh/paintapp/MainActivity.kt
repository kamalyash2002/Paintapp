package com.mitesh.paintapp
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRed = findViewById<Button>(R.id.btnRed)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnErase = findViewById<Button>(R.id.btnErase)
        val drawingView = findViewById<com.mitesh.paintapp.PaintView>(R.id.drawingView)

        // Set color button listeners
        btnRed.setOnClickListener {
            drawingView.setColor(Color.RED)
            drawingView.disableEraser()
        }

        // Set erase button listener
        btnErase.setOnClickListener {
            drawingView.enableEraser()
        }

        // Set clear button listener
        btnClear.setOnClickListener {
            drawingView.clearCanvas()
        }
    }
}
