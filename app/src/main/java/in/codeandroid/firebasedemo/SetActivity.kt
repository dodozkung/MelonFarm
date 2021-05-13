package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_plant.*
import kotlinx.android.synthetic.main.activity_plant.set_button
import kotlinx.android.synthetic.main.activity_plant.text1
import kotlinx.android.synthetic.main.activity_salan.*
import kotlinx.android.synthetic.main.activity_set.*
import org.jetbrains.anko.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
@RequiresApi(Build.VERSION_CODES.O)
class SetActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var authListener: FirebaseAuth.AuthStateListener? = null
    var currentTime: Date? = Calendar.getInstance().time
    var Week = 0
    val current = LocalDateTime.now()
    val formatday = DateTimeFormatter.ofPattern("dd")
    val formatmount = DateTimeFormatter.ofPattern("MM")
    val formatyear = DateTimeFormatter.ofPattern("YY")
    val forday = current.format(formatday)
    val formount = current.format(formatmount)
    val foryear = current.format(formatyear)
    private val TAG: String = "Result Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_set)
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        val intent = getIntent()
        var name1 = intent.getStringExtra("TopicNameA")
        var Topic = intent.getStringExtra("TopicName")
        editTextgene.setText(name1)


        set_button.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("บันทึก ต้นที่ :"+Topic)
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    saveData()
                }
                // negative button text and action
                .setNegativeButton("ยกเลิก") { dialog, _ ->
                    dialog.cancel()
                }
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("บันทึก")
            // show alert dialog
            alert.show()
        }
    }

    override fun onStart() {
        super.onStart()
        auth!!.addAuthStateListener { authListener }
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth!!.removeAuthStateListener { authListener }
        }
    }
    fun saveData() {
        var year = intent.getStringExtra("year")
        var mount = intent.getStringExtra("mount")
        var day = intent.getStringExtra("day")
        if (day == "01" || day == "02" || day == "03" || day == "04" || day == "05" || day == "06" || day == "07") {
            Week = 1
        } else if (day == "08" || day == "09" || day == "10" || day == "11" || day == "12" || day == "13" || day == "14") {
            Week = 2
        } else if (day == "15" || day == "16" || day == "17" || day == "18" || day == "19" || day == "20" || day == "21") {
            Week = 3
        } else if (day == "22" || day == "23" || day == "24" || day == "25" || day == "26" || day == "27" || day == "28") {
            Week = 4
        } else if (day == "29" || day == "30" || day == "31") {
            Week = 5 }
        val intent = getIntent()
        var name2 = intent.getStringExtra("TopicNameA")
        var name = intent.getStringExtra("TopicName")
        var editTextLetf: EditText = findViewById(R.id.editTextLetf)
        var editTextHigh: EditText = findViewById(R.id.editTextHigh)
        var editTextage: EditText = findViewById(R.id.editTextage)
        var editTextWideLeft: EditText = findViewById(R.id.editTextWideLeft)
        var editTextClause: EditText = findViewById(R.id.editTextClause)
        var editTextDiameter: EditText = findViewById(R.id.editTextDiameter)
        var editTextChlorophyll: EditText = findViewById(R.id.editTextChlorophyll)
        var map = mutableMapOf<String, Any>()
        map["QuantityLeft"] = editTextLetf.text.toString().toInt()
        map["HighPlant"] = editTextHigh.text.toString().toInt()
        map["age"] = editTextage.text.toString().toInt()
        map["WideLeft"] = editTextWideLeft.text.toString().toInt()
        map["Clause"] = editTextClause.text.toString().toInt()
        map["Diameter"] = editTextDiameter.text.toString().toInt()
        map["Chlorophyll"] = editTextChlorophyll.text.toString().toInt()


        FirebaseFirestore.getInstance()
            .collection("plant")
            .document(year)
            .collection(mount)
            .document(Week.toString())
            .collection(name2.toString())
            .document(name.toString())
            .set(map).addOnSuccessListener { documentSnapshot ->
                val intent1 = Intent(this@SetActivity, RowActivity::class.java);
                val name1 = editTextgene.text.toString()
                intent1.putExtra("TopicNameA", name1)
                startActivity(intent1)
                Toast.makeText(this,"เสร็จสิ้น", Toast.LENGTH_LONG).show()

            }

    }
 override fun onBackPressed() {
    super.onBackPressed()
    Toast.makeText(this,"Back", Toast.LENGTH_LONG).show()
    var intent = Intent(this,InfoActivity::class.java)
    startActivity(intent)
}}