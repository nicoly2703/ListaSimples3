package com.example.listasimples3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val novaTarefa = findViewById<EditText>(R.id.edtnovatarefa)
        val adicionar = findViewById<Button>(R.id.bntadd)
        val tvtitulo = findViewById<TextView>(R.id.tvtitulo)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            tvtitulo.isVisible = false
            novaTarefa.isVisible = true
            novaTarefa.isEnabled = true
            adicionar.isVisible = true
        }

        val tarefas = findViewById<ListView>(R.id.lvtarefas)
        val listaTarefas: ArrayList<String> = ArrayList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTarefas)
        tarefas.adapter = adapter

        adicionar.setOnClickListener {
            if (novaTarefa.text.isNullOrEmpty()) {
                Toast.makeText(this, "Digite uma tarefa...", Toast.LENGTH_SHORT).show()
            } else {
                listaTarefas.add(novaTarefa.text.toString())
                adapter.notifyDataSetChanged()
                novaTarefa.setText("")
                novaTarefa.isVisible = false
                novaTarefa.isEnabled = false
                adicionar.isVisible = false
                tvtitulo.isVisible = true

            }
        }

        tarefas.setOnItemLongClickListener() { _, _, position, _ ->
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atenção")
            alerta.setMessage("Deseja mesmo excluir esse item?")
            alerta.setPositiveButton("Confirmar") { dialog, _ ->

                listaTarefas.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            alerta.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            alerta.create().show()
            true
        }

    }
}