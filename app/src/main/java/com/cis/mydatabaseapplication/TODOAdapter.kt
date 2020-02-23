import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.cis.mydatabaseapplication.R
import com.cis.mydatabaseapplication.TodoItem

class ToDoItemAdapter(context: Context, toDoItemList: MutableList<TodoItem>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoItemList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // create object from view
        val objectId: String = itemList.get(position).objectID as String
        val itemText: String = itemList.get(position).todoName as String
        val done: Boolean = itemList.get(position).status as Boolean
        val view: View
        val vh: ListRowHolder

        // get list view
        if (convertView == null) {
            view = mInflater.inflate(R.layout.list_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        // add text to view
        vh.label.text = itemText
        vh.isDone.isChecked = done
        return view
    }

    override fun getItem(index: Int): Any {
        return itemList.get(index)
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

    private class ListRowHolder(row: View?) {
        val label: TextView = row!!.findViewById<TextView>(R.id.textView) as TextView
        val isDone: CheckBox = row!!.findViewById<CheckBox>(R.id.checkBox) as CheckBox
        val ibDeleteObject: ImageButton = row!!.findViewById<ImageButton>(R.id.imageButton) as ImageButton
    }
}