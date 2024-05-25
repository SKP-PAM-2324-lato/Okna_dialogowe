package com.example.oknadialogowe

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var selectedOption = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Komunikat")
            builder.setMessage("To jest mój komunikat")
            builder.show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Komunikat")
            builder.setMessage("Czy chcesz przejść dalej?")
            builder.setCancelable(false)
            builder.setPositiveButton("Tak"){ dialog, which ->
                Toast.makeText(applicationContext, "Wyraziłeś zgodę", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Nie"){ dialog, which ->
                Toast.makeText(applicationContext, "Nie wyraziłeś zgody", Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Anuluj"){ dialog, which ->
               dialog.dismiss()
            }
            builder.show()
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            val options = arrayOf("Łatwy", "Średni", "Trudny")

            var option: Int = 0

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Wybierz poziom trudności")
            builder.setSingleChoiceItems(options, selectedOption){dialog, which ->
               option = which
            }

            builder.setPositiveButton("OK"){ dialog, which ->
                selectedOption = option
                Toast.makeText(applicationContext, "Wybrałeś poziom trudności ${options[selectedOption]}",
                    Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Anuluj"){dialog, which ->
                dialog.dismiss()
            }

            builder.setNeutralButton("Przywróć domyślny"){ dialog, which ->
                selectedOption = 0
            }

            // Przywróć domyślne
            builder.show()
        }
    }
}