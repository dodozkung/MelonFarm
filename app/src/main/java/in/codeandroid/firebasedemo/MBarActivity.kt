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

class MBarActivity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance()
    val TAG = ""
    val aPlant = ArrayList<BarEntry>()
    val cPlant = ArrayList<BarEntry>()
    val dPlant = ArrayList<BarEntry>()
    val ePlant = ArrayList<BarEntry>()
    val fPlant = ArrayList<BarEntry>()
    var count = 1
    var Week = 0
    var  THighPlant = 0.00
    var  TClause = 0.00
    var  TDiameter = 0.00
    var  TWideLeft = 0.00
    var  TQuantityLeft = 0.00
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_chart)
        var intent = getIntent()
        var name1 = intent.getStringExtra("TopicNameA")
        var year = intent.getStringExtra("year")
        var mount = intent.getStringExtra("mount")
        var day = intent.getStringExtra("day")

        textWeek.text = mount.toString()
        for (i in 1..25){
            FirebaseFirestore.getInstance()
                .collection("plant")
                .document(year)
                .collection(mount)
                .document("1")
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
                        .document(year)
                        .collection(mount)
                        .document("1")
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
                                .document(year)
                                .collection(mount)
                                .document("1")
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
                                        .document(year)
                                        .collection(mount)
                                        .document("1")
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
                                                .document(year)
                                                .collection(mount)
                                                .document("1")
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
                            if (count==25){
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
                                val barDataSet = BarDataSet(aPlant,"??????????????????????????????")
                                val barDataSet1 = BarDataSet(cPlant,"????????????????????????")
                                val barDataSet2 = BarDataSet(dPlant,"?????????????????????")
                                val barDataSet3 = BarDataSet(ePlant,"????????????????????????????????????????????????")
                                val barDataSet4 = BarDataSet(fPlant,"???????????????????????????")
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

        }}}}}

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Back", Toast.LENGTH_LONG).show()
        var intent = Intent(this,ChartActivity::class.java)
        startActivity(intent)
    }}