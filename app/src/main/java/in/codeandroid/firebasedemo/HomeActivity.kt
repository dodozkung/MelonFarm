package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var authListener: FirebaseAuth.AuthStateListener? = null
    var currentTime: Date? = Calendar.getInstance().time
    var date = Date()

    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("dd-mm-yy HH:mm")

    @RequiresApi(Build.VERSION_CODES.O)
    val formatted = current.format(formatter)
    private val TAG:String = "Result Activity"
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_home)
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val user = auth!!.currentUser

        result_emailData.text = user!!.email
        result_uidData.text = user.uid
        if(auth!!.currentUser == null){
            val d = Log.d(TAG, "Continue" + auth!!.currentUser!!.email)
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        if(user.isEmailVerified==false){
            startActivity(Intent(this@HomeActivity, VariActivity::class.java))
            finish()
        }

        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null) {
                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                finish()
            }
        }
        FirebaseDatabase.getInstance().reference
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var map = p0.value as Map<String, Any>
                    tempC.text = map["tempC"].toString()
                    tempF.text = map["tempF"].toString()
                    humidity.text = map["humidity"].toString()
                }
            })

        btn_info.setOnClickListener{
            startActivity(Intent(this@HomeActivity, InfoActivity::class.java))
            finish()
        }
        Tembtn.setOnClickListener{
            startActivity(Intent(this@HomeActivity, MainDashboard::class.java))
            finish()
        }
       btn_dash.setOnClickListener {
           startActivity(Intent(this@HomeActivity, ChartActivity::class.java))
           finish()
        }
        btn_set.setOnClickListener {
            startActivity(Intent(this@HomeActivity, AdminActivity::class.java))
            finish()
        }
        btn_logout.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("ออกจากระบบ")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("ตกลง") { _, _ ->
                    logout()

                }
                // negative button text and action
                .setNegativeButton("ยกเลิก") { dialog, _ ->
                    dialog.cancel()
                }
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("ออกจากระบบ")
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
        if (authListener != null) { auth!!.removeAuthStateListener { authListener } }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) { moveTaskToBack(true) }
        return super.onKeyDown(keyCode, event)
    }
    fun logout(){
        auth!!.signOut()
         toast("Signed out!")
        Log.d(TAG, "Signed out!")
         startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        finish()}

}
