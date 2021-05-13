package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_tree.*

class ShowplantActivity : AppCompatActivity() {
    var Week = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)

        val intent = getIntent()
        var name1 = intent.getStringExtra("TopicNameA")
        var name = intent.getStringExtra("TopicName")
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
        } else if (day == "29" || day == "30" || day == "31") { Week = 5 }
        FirebaseFirestore.getInstance()
            .collection("plant")
            .document(year)
            .collection(mount)
            .document(Week.toString())
            .collection(name1)
            .document(name)
            .get().addOnSuccessListener { documentSnapshot ->
                var map = documentSnapshot.data as Map<String, Any>
                text1.text = map["QuantityLeft"].toString()
                text2.text = map["HighPlant"].toString()
                text3.text = map["age"].toString()
                text4.text = map["WideLeft"].toString()
                text5.text = map["Clause"].toString()
                text6.text = map["Diameter"].toString()
                text7.text = map["Chlorophyll"].toString()
                nametype.text = name1
                nametypeE.text = name

            }}

        override fun onBackPressed() {
            super.onBackPressed()
            Toast.makeText(this,"Back", Toast.LENGTH_LONG).show()
            var intent = Intent(this,GeneActivity::class.java)
            startActivity(intent)
        }}