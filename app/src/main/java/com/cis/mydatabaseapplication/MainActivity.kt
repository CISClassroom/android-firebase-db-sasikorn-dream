package com.cis.mydatabaseapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ListView
import android.view.View
import android.widget.ListAdapter
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mDB = FirebaseDatabase.getInstance().reference
        var listviewitem = findViewById<View>(R.id.list_view) as ListView

        var todoItemList = mutableListOf<TodoItem>()
        var adapter = TODOAdapter(this, todoItemList!!)
        listviewitem!!.setAdapter(adapter)
        mDB.orderByKey().addListenerForSingleValueEvent(itemListener)

        fab.setOnClickListener { view ->
            addNewTODO()
        }
    }

    private fun TODOAdapter(
        mainActivity: MainActivity,
        todoItemList: MutableList<TodoItem>
    ): ListAdapter? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var itemListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            addDataTolist(p0)
        }

        override fun onCancelled(p0: DatabaseError) {

        }
    }

    private fun addDataTolist(dataSnapshot: DataSnapshot) {
        val item = dataSnapshot.children.iterator()
        if (item.hasNext()) {
            val todoListIndex = item.next()
            val itemsIterator = todoListIndex.children.iterator()

            while (itemsIterator.hasNext()) {
                val currentItem = itemsIterator.next()
                val map = currentItem.getValue() as HashMap<String, Any>
                val todoItem = TodoItem.create()
                todoItem.objectID = currentItem.key
                todoItem.status = map.get("Status") as Boolean
                todoItem.todoName = map.get("Todo Name") as String


            }
        }
    }

    lateinit var mDB: DatabaseReference

    private fun addNewTODO() {
        val dialog = AlertDialog.Builder(this)
        val et = EditText(this)

        dialog.setMessage("Add new TODO")
        dialog.setTitle("Enter TODO")
        dialog.setView(et)

        dialog.setPositiveButton("Submit") { dialog, posisitiveButton ->
            val newTODO = TodoItem.create()
            newTODO.todoName = et.text.toString()
            newTODO.status = false
            val newItemDB = mDB.child("TODO_item").push()
            newTODO.objectID = newItemDB.key
            newItemDB.setValue(newTODO)
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
