package `in`.codeandroid.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main_dashboard.*
import kotlinx.android.synthetic.main.activity_salan.*

class SalanActivity : AppCompatActivity() {
    var motorS = 0
    var motorSS = 0
    var motor =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salan)

        FirebaseDatabase.getInstance().reference
            .child("MotorMode")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var map = p0.value as Map<String, Any>
                    motorSS = map["motorS"].hashCode()



                    if (motorSS==0){
                        Sashowtext.text = "ปิด"

                    }
                    if (motorSS==5){
                        Sashowtext.text = "อัตโนมัติ"

                    }
                }
            })
        btn_openS.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("เปิดสแลน")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    motorS = 5
                    motor = 5
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
        btn_open1.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("เปิดสแลน")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    motorS = 1
                    motor = 1
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
        btn_open2.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("เปิดสแลน")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    motorS = 1
                    motor = 2
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
        btn_closeS.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("ปิดสแลน")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    motorS = 0
                    motor = 0
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
        map["motorS"]= motorS
        map["motor"]= motor
        FirebaseDatabase.getInstance().reference
            .child("MotorMode")
            .setValue(map)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Back", Toast.LENGTH_LONG).show()
        var intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}