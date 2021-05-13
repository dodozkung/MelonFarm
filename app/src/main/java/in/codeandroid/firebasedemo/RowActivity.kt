package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farm.myapplication.CustomAdapter
import com.farm.myapplication.CustomItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_rely.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class RowActivity : AppCompatActivity() {
    val current = LocalDateTime.now()
    val formatday = DateTimeFormatter.ofPattern("dd")
    val formatmount = DateTimeFormatter.ofPattern("MM")
    val formatyear = DateTimeFormatter.ofPattern("YY")
    val forday = current.format(formatday)
    val formount = current.format(formatmount)
    val foryear = current.format(formatyear)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rely)
        var auth: FirebaseAuth? = null
        var authListener: FirebaseAuth.AuthStateListener? = null
        auth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        var name1 = intent.getStringExtra("TopicNameA")
        var type = name1
        val items: ArrayList<CustomItem> = ArrayList<CustomItem>()
        editTexttype.setText(type)
        editTextyear.setText(foryear)
        editTextmount.setText(formount)
        editTextday.setText(forday)
        items.add(CustomItem("1"))
        items.add(CustomItem("2"))
        items.add(CustomItem("3"))
        items.add(CustomItem("4"))
        items.add(CustomItem("5"))
        items.add(CustomItem("6"))
        items.add(CustomItem("7"))
        items.add(CustomItem("8"))
        items.add(CustomItem("9"))
        items.add(CustomItem("10"))
        items.add(CustomItem("11"))
        items.add(CustomItem("12"))
        items.add(CustomItem("13"))
        items.add(CustomItem("14"))
        items.add(CustomItem("15"))
        items.add(CustomItem("16"))
        items.add(CustomItem("17"))
        items.add(CustomItem("18"))
        items.add(CustomItem("19"))
        items.add(CustomItem("20"))
        items.add(CustomItem("21"))
        items.add(CustomItem("22"))
        items.add(CustomItem("23"))
        items.add(CustomItem("24"))
        items.add(CustomItem("25"))

        val adapter = CustomAdapter(this, items)
        val rcv = findViewById<RecyclerView>(R.id.recyclerView)
        rcv.adapter = adapter
        rcv.layoutManager = LinearLayoutManager(this)
        adapter.setOnClickListener(object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(item: View?, position: Int) {
                val str: String = "ต้นที่" + items[position].text1.toString() + "กรอกข้อมูล "
                Toast.makeText(baseContext, str, Toast.LENGTH_SHORT).show()
                val name = items[position].text1
                val type = editTexttype.text.toString()
                val year = editTextyear.text.toString()
                val month = editTextmount.text.toString()
                val day = editTextday.text.toString()
                val Intent = Intent(this@RowActivity, SetActivity::class.java);
                Intent.putExtra("TopicName", name)
                Intent.putExtra("TopicNameA", type)
                Intent.putExtra("year", year)
                Intent.putExtra("mount", month)
                Intent.putExtra("day", day)
                startActivity(Intent)
            }
        })
    }
        override fun onBackPressed() {
            super.onBackPressed()
            Toast.makeText(this, "Back", Toast.LENGTH_LONG).show()
            var intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }}


