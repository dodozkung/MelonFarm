package `in`.codeandroid.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main_dashboard.*
import kotlinx.android.synthetic.main.activity_plant.*

class MainDashboard : AppCompatActivity() {
    var Modepump = 0
    var Modeshow = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

        FirebaseDatabase.getInstance().reference
            .child("PumpMode")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var map = p0.value as Map<String, Any>
                    Modeshow = map["Modepump"].hashCode()



                    if (Modeshow==1){
                        Modeshowtext.text = "สูตร ที่ 1 Ec 1.4-1.6"

                    }
                    if (Modeshow==2){
                        Modeshowtext.text = "สูตร ที่ 2 Ec 1.8-2.2"

                    }
                    if (Modeshow==3){
                        Modeshowtext.text = "สูตร ที่ 2 Ec 2.4-2.6"

                    }
                    if (Modeshow==7){
                        Modeshowtext.text = "ปิด"

                    }
                }
            })
        button1.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("สูตร ที่ 1")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    Modepump = 1
                    saveData()
                }
                // negative button text and action
                .setNegativeButton("ยกเลิก") { dialog, _ ->
                    dialog.cancel()
                }
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("เสูตร ที่ 1 Ec 1.4-1.6")
            // show alert dialog
            alert.show()
        }
        button2.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("สูตร ที่ 2")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    Modepump = 2
                    saveData()
                }
                // negative button text and action
                .setNegativeButton("ยกเลิก") { dialog, _ ->
                    dialog.cancel()
                }
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("เสูตร ที่ 2 Ec 1.8-2.2")
            // show alert dialog
            alert.show()
        }
        button3.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("สูตร ที่ 3")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    Modepump = 3
                    saveData()
                }
                // negative button text and action
                .setNegativeButton("ยกเลิก") { dialog, _ ->
                    dialog.cancel()
                }
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("เสูตร ที่ 1 Ec 2.4-2.6")
            // show alert dialog
            alert.show()
        }
        button4.setOnClickListener {
            Modepump = 4
            saveData()
        }
        button5.setOnClickListener {
            Modepump = 5
            saveData()
        }
        button6.setOnClickListener {
            Modepump = 6
            saveData()
        }
        button7.setOnClickListener {
            Modepump = 7
            saveData()
        }
        button8.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("เปิดสแลน")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    Modepump = 8
                    saveData()
                }
                // negative button text and action
                .setNegativeButton("ยกเลิก") { dialog, _ ->
                    dialog.cancel()
                }
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("เปิด-ปิด สแลน")
            // show alert dialog
            alert.show()
        }


    }

    private fun saveData() {
        var map = mutableMapOf<String, Any>()
        map["Modepump"]= Modepump
            FirebaseDatabase.getInstance().reference
                .child("PumpMode")
                .setValue(map)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Back", Toast.LENGTH_LONG).show()
        var intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }}