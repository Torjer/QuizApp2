package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_user_config.*

class UserConfig : AppCompatActivity() {

    companion object{
        const val RESULT_SETTINGS_CONFIG = RESULT_FIRST_USER
        const val EXTRA_SELECTED_USER = "com.example.quizapp2.selected_user"
        const val EXTRA_LIST_USERS = "com.example.quizapp2.list_user"

        fun createIntent(packageContext: Context, SelUser:String, Users : Array<String>): Intent {
            return Intent(packageContext, UserConfig::class.java).apply {
                putExtra(EXTRA_SELECTED_USER, SelUser)
                putExtra(EXTRA_LIST_USERS, Users)
            }
        }
    }


    private lateinit var LView: ListView
    private lateinit var AddBtn: Button
    private lateinit var CnBtn: Button

    private var Usuarios = mutableListOf<String>()
    private var SelectedItem:String = " "
    private var SelectedUser:String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_config)

        val LOfUsers = intent.getStringArrayExtra(EXTRA_LIST_USERS)

        LOfUsers?.forEach {
            Usuarios.add(it)
        }

        LView = findViewById(R.id.list_view)
        AddBtn = findViewById(R.id.add_btn)
        CnBtn = findViewById(R.id.cn_btn)

       // DltBtn.isEnabled = false

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Usuarios)
        LView.adapter = myAdapter

        AddBtn.setOnClickListener { _ ->
            val dialog = AlertDialog.Builder(this)
            val dialogLayout = layoutInflater.inflate(R.layout.add_user_layout, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.new_user)

            with(dialog){
                setTitle("Add username")
                setPositiveButton("OK"){dialog, which ->
                    Usuarios.add(editText.text.toString())
                    myAdapter.notifyDataSetChanged()
                }
                setNegativeButton("Cancel"){dialog, which ->
                    Log.d("Main","Negative")
                }
                setView(dialogLayout)
                show()
            }
        }

        LView.setOnItemClickListener { parent, view, position, id ->
            SelectedItem = LView.getItemAtPosition(position).toString()
            val dialog = AlertDialog.Builder(this)
            with(dialog){
                dialog.setTitle("What do you want to do now?")
                setPositiveButton("Select"){dialog, which ->
                    SelectedUser = SelectedItem
                }
                setNegativeButton("Delete"){dialog, which ->
                    Usuarios.removeAt(position)
                    myAdapter.notifyDataSetChanged()
                }
                show()
            }
        }

        CnBtn.setOnClickListener { _ ->
            setResult(Options.RESULT_SETTINGS_CONFIG, Intent().apply {
                putExtra(EXTRA_SELECTED_USER, SelectedUser)
                putExtra(EXTRA_LIST_USERS, Usuarios.toTypedArray())
            })
            finish()
        }
    }
}