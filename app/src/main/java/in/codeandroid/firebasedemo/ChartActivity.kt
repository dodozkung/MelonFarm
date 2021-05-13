package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_infochart.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChartActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infochart)

        val current = LocalDateTime.now()
        val formatday = DateTimeFormatter.ofPattern("dd")
        val formatmount = DateTimeFormatter.ofPattern("MM")
        val formatyear = DateTimeFormatter.ofPattern("YY")
        val forday = current.format(formatday)
        val formount = current.format(formatmount)
        val foryear = current.format(formatyear)
        editTexttype.setText("พันธุ์")
        editTextyear.setText(foryear)
        editTextmount.setText(formount)
        editTextday.setText(forday)

        button.setOnClickListener{
            val intent = Intent(this@ChartActivity, AdminActivity::class.java);
            val name1 = editTexttype.text.toString()
            val year = editTextyear.text.toString()
            val month = editTextmount.text.toString()
            val day = editTextday.text.toString()
            intent.putExtra("TopicNameA", name1)
            intent.putExtra("year", year)
            intent.putExtra("mount", month)
            intent.putExtra("day", day)
            startActivity(intent)
            finish()
        }
        button2.setOnClickListener{
            val intent = Intent(this@ChartActivity, BarActivity::class.java);
            val name1 = editTexttype.text.toString()
            val year = editTextyear.text.toString()
            val month = editTextmount.text.toString()
            val day = editTextday.text.toString()
            val year1 = editTextyear1.text.toString()
            val month1 = editTextmount1.text.toString()
            val day1 = editTextday1.text.toString()
            intent.putExtra("TopicNameA", name1)
            intent.putExtra("year", year)
            intent.putExtra("mount", month)
            intent.putExtra("day", day)
            intent.putExtra("TopicNameA1", name1)
            intent.putExtra("year1", year1)
            intent.putExtra("mount1", month1)
            intent.putExtra("day1", day1)
            startActivity(intent)
            finish()
        }
        button3.setOnClickListener{
            val intent = Intent(this@ChartActivity, MBarActivity::class.java);
            val name1 = editTexttype.text.toString()
            val year = editTextyear.text.toString()
            val month = editTextmount.text.toString()
            val day = editTextday.text.toString()
            intent.putExtra("TopicNameA", name1)
            intent.putExtra("year", year)
            intent.putExtra("mount", month)
            intent.putExtra("day", day)
            startActivity(intent)
            finish()

    }}
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Back", Toast.LENGTH_LONG).show()
        var intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }}
