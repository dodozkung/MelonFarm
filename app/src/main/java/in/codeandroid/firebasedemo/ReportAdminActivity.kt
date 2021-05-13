package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ReportAdminActivity : AppCompatActivity() {
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
    override fun onCreate(savedInstanceState: Bundle?) {5

        setContentView(R.layout.activity_readmin)
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val user = auth!!.currentUser

        result_emailData.text = user!!.email
        result_uidData.text = user.uid
//        Clock.text= formatted
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null) {
                startActivity(Intent(this@ReportAdminActivity, LoginActivity::class.java))
                finish()
            }
        }
        Tembtn.setOnClickListener{
            logout()
        }
//        set_button.setOnClickListener {
//            saveData()
//        }
//        update_button.setOnClickListener {
//            updateData()
//        }
//
//        btn_sign_out.setOnClickListener {
//            logout()
//        }
//       btn_info.setOnClickListener {
//                startActivity(Intent(this,InfoActivity::class.java))
//                finish()
//            }

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
    //    fun saveData(){
//        var setEditTextString = set_edittext.text.toString()
//        var UID=result_uidData.text
//        var map = mutableMapOf<String, Any>()
//        map["UID"] = result_uidData.text
//        map["age"] = setEditTextString
//        map["Clock"] = formatted
//
//        FirebaseDatabase.getInstance().reference
//            .child("Member")
//            .child("User")
//            .child(UID as String)
//            .setValue(map)
//    }
//    fun updateData(){
//        var setEditTextString = set_edittext.text.toString()
//        var UID=result_uidData.text
//        var map = mutableMapOf<String, Any>()
//        map["UID"] = result_uidData.text
//        map["age"] = setEditTextString
//
//        FirebaseDatabase.getInstance().reference
//            .child("Member")
//            .child("User")
//            .child(UID as String)
//            .updateChildren(map)
//    }
    fun logout(){
        auth!!.signOut()
        toast("Signed out!")
        Log.d(TAG, "Signed out!")
        startActivity(Intent(this@ReportAdminActivity, LoginActivity::class.java))
        finish()}
//    fun readObserveData(){
//        FirebaseDatabase.getInstance().reference
//            .addValueEventListener(object : ValueEventListener {
//                override fun onCancelled(p0: DatabaseError) {
//
//                }
//
//                override fun onDataChange(p0: DataSnapshot) {
//                    var map = p0.value as Map<String, Any>
//                    read_observe_textview.text = map["temperature"].toString()
//                }
//
//            })
//    }
}
