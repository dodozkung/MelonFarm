package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget.*
import org.jetbrains.anko.toast


class ForgetActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG: String = "Forgot Activity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)

        btn_sign_up.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        btn_in.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        btn_Forget.setOnClickListener {
            val email = emailname.text.toString()
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Email sent")
                        Log.d(TAG, "Email sent.")
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }
                    if (!!task.isSuccessful) {
                        toast("Error don't have Email")
                        Log.d(TAG, "Error don't have Email")
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }
                }
        }
    }
}
