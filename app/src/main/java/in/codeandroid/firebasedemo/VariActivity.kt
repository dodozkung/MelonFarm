package `in`.codeandroid.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_vari.*
import org.jetbrains.anko.toast

class VariActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null

    private val TAG:String = "Result Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vari)
        textView8.setOnClickListener{
            auth!!.signOut()
            toast("Signed out!")
            Log.d(TAG, "Signed out!")
            startActivity(Intent(this@VariActivity, LoginActivity::class.java))
            finish()}
        }
    }
