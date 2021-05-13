package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.graphics.Color
import android.media.MediaSync
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.activity_infochart.*
import kotlinx.android.synthetic.main.activity_vari.*
import org.jetbrains.anko.toast
import java.util.ArrayList
import kotlin.math.log

class BarActivity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance()
    val TAG = ""
    val aPlant = ArrayList<BarEntry>()
    val cPlant = ArrayList<BarEntry>()
    val dPlant = ArrayList<BarEntry>()
    val ePlant = ArrayList<BarEntry>()
    val fPlant = ArrayList<BarEntry>()
    var count = 1
    var Week = 0
    var Week1 = 0
    var  THighPlant = 0.00
    var  TClause = 0.00
    var  TDiameter = 0.00
    var  TWideLeft = 0.00
    var  TQuantityLeft = 0.00
    var  THighPlant1 = 0.00
    var  TClause1 = 0.00
    var  TDiameter1 = 0.00
    var  TWideLeft1 = 0.00
    var  TQuantityLeft1 = 0.00
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_chart)
        var intent = getIntent()
        var name1 = intent.getStringExtra("TopicNameA")
        var year = intent.getStringExtra("year")
        var mount = intent.getStringExtra("mount")
        var day = intent.getStringExtra("day")
        var year1 = intent.getStringExtra("year1")
        var mount1 = intent.getStringExtra("mount1")
        var day1 = intent.getStringExtra("day1")
        if (day == "01" || day == "02" || day == "03" || day == "04" || day == "05" || day == "06" || day == "07") {
            Week = 1
        } else if (day == "08" || day == "09" || day == "10" || day == "11" || day == "12" || day == "13" || day == "14") {
            Week = 2
        } else if (day == "15" || day == "16" || day == "17" || day == "18" || day == "19" || day == "20" || day == "21") {
            Week = 3
        } else if (day == "22" || day == "23" || day == "24" || day == "25" || day == "26" || day == "27" || day == "28") {
            Week = 4
        } else if (day == "29" || day == "30" || day == "31") {
            Week = 5
        }
        if (day1 == "01" || day1 == "02" || day1 == "03" || day1== "04" || day1 == "05" || day1 == "06" || day1 == "07") {
            Week1 = 1
        } else if (day1 == "08" || day1 == "09" || day1 == "10" || day1 == "11" || day1 == "12" || day1 == "13" || day1 == "14") {
            Week1 = 2
        } else if (day1 == "15" || day1 == "16" || day1 == "17" || day1 == "18" || day1 == "19" || day1 == "20" || day1 == "21") {
            Week1 = 3
        } else if (day1 == "22" || day1 == "23" || day1 == "24" || day1 == "25" || day1 == "26" || day1 == "27" || day1 == "28") {
            Week1 = 4
        } else if (day1 == "29" || day1 == "30" || day1 == "31") {
            Week1 = 5
        }
        textWeek.text = Week.hashCode().toString()
        for (i in 1..25){
            FirebaseFirestore.getInstance()
                .collection("plant")
                .document(year)
                .collection(mount)
                .document(Week.toString())
                .collection(name1)
                .document(i.toString())
                .get()
                .addOnSuccessListener{ documentSnapshot ->
                    var map = documentSnapshot.data as Map<String, Any>
                    var HighPlant = map["HighPlant"].hashCode()
                    var Clause = map["Clause"].hashCode()
                    var Diameter = map["Diameter"].hashCode()
                    var WideLeft = map["WideLeft"].hashCode()
                    var QuantityLeft = map["QuantityLeft"].hashCode()
                    THighPlant= THighPlant+HighPlant
                    TClause= TClause+Clause
                    TDiameter= TDiameter+Diameter
                    TWideLeft= TWideLeft+WideLeft
                    TQuantityLeft= TQuantityLeft+QuantityLeft
                    FirebaseFirestore.getInstance()
                        .collection("plant")
                        .document(year1)
                        .collection(mount1)
                        .document(Week.toString())
                        .collection(name1)
                        .document(i.toString())
                        .get()
                        .addOnSuccessListener{ documentSnapshot ->
                            var map = documentSnapshot.data as Map<String, Any>
                            var HighPlant = map["HighPlant"].hashCode()
                            var Clause = map["Clause"].hashCode()
                            var Diameter = map["Diameter"].hashCode()
                            var WideLeft = map["WideLeft"].hashCode()
                            var QuantityLeft = map["QuantityLeft"].hashCode()
                            THighPlant1= THighPlant1+HighPlant
                            TClause1= TClause1+Clause
                            TDiameter1= TDiameter1+Diameter
                            TWideLeft1= TWideLeft1+WideLeft
                            TQuantityLeft1= TQuantityLeft1+QuantityLeft
                    if (count==25){

                        THighPlant1= THighPlant/25f
                        TClause1= TClause/25f
                        TDiameter1= TDiameter/25f
                        TWideLeft1= TWideLeft/25f
                        TQuantityLeft1= TQuantityLeft/25f
                        THighPlant= THighPlant/25f
                        TClause= TClause/25f
                        TDiameter= TDiameter/25f
                        TWideLeft= TWideLeft/25f
                        TQuantityLeft= TQuantityLeft/25f
                        aPlant.add(BarEntry(1f,THighPlant.toFloat()))
                        cPlant.add(BarEntry(3f, TClause.toFloat()))
                        dPlant.add(BarEntry(5f,TQuantityLeft.toFloat()))
                        ePlant.add(BarEntry(7f, TDiameter.toFloat()))
                        fPlant.add(BarEntry(9f, TWideLeft.toFloat()))
                        aPlant.add(BarEntry(2f,THighPlant.toFloat()))
                        cPlant.add(BarEntry(4f, TClause.toFloat()))
                        dPlant.add(BarEntry(6f,TQuantityLeft.toFloat()))
                        ePlant.add(BarEntry(8f, TDiameter.toFloat()))
                        fPlant.add(BarEntry(10f, TWideLeft.toFloat()))
                        val barDataSet = BarDataSet(aPlant,"ความสูงพืช1,2")
                        val barDataSet1 = BarDataSet(cPlant,"จำนวนข้อ1,2")
                        val barDataSet2 = BarDataSet(dPlant,"จำนวนใบ1,2")
                        val barDataSet3 = BarDataSet(ePlant,"เส้นผ่าศูนย์กลาง1,2")
                        val barDataSet4 = BarDataSet(fPlant,"ขนาดของใบ1,2")
                        val barData = BarData(barDataSet,barDataSet1,barDataSet2,barDataSet3,barDataSet4)
                        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
                        barDataSet.valueTextColor = Color.BLACK
                        barDataSet.valueTextSize = 16f
                        barDataSet1.setColors(*ColorTemplate.COLORFUL_COLORS)
                        barDataSet1.valueTextColor = Color.BLACK
                        barDataSet1.valueTextSize = 16f
                        barDataSet2.setColors(*ColorTemplate.JOYFUL_COLORS)
                        barDataSet2.valueTextColor = Color.BLACK
                        barDataSet2.valueTextSize = 16f
                        barDataSet3.setColors(*ColorTemplate.MATERIAL_COLORS)
                        barDataSet3.valueTextColor = Color.BLACK
                        barDataSet3.valueTextSize = 16f
                        barDataSet4.setColors(*ColorTemplate.COLORFUL_COLORS)
                        barDataSet4.valueTextColor = Color.BLACK
                        barDataSet4.valueTextSize = 16f

                        barChart.setFitBars(true)
                        barChart.data = barData
                        barChart.description.text = "BarPlant"
                        barChart.animateY(2000)
                    }
                    count++

                }.addOnFailureListener {
                    textWeek.text=("Error")
                    Log.d("THighPlant","6666")




                }}

    }}

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Back", Toast.LENGTH_LONG).show()
        var intent = Intent(this,ChartActivity::class.java)
        startActivity(intent)
    }}


