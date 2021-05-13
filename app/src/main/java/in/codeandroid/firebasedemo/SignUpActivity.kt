package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import kotlinx.android.synthetic.main.activity_sign_up.tv_password
import org.jetbrains.anko.toast


class SignUpActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Register Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }

        btn_sign_up.setOnClickListener {
            val email = tv_username.text.toString().trim { it <= ' ' }
            val password = tv_password.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                toast("Please enter your email address.")
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                toast("Please enter your password.")
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) {
                        toast("Password too short! Please enter minimum 6 characters.")
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        toast("Authentication Failed: " + task.exception!!.message)
                        Log.d(TAG, "Authentication Failed: " + task.exception!!.message)
                    }
                }
                    if (task.isSuccessful) {
                                Log.d(TAG, "Successfully signed in with email link!")
                                toast("Create account successfully!")
                                Log.d(TAG, "Create account successfully!")

                                // [START send_email_verification]
                                val user = mAuth!!.currentUser

                                user!!.sendEmailVerification()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Log.d(TAG, "Email sent.")
                                        }
                                    }
                            } else {
                                Log.e(TAG, "Error signing in with email link", task.exception)
                            }

                }


                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    finish()
                }



        btn_in.setOnClickListener { startActivity(Intent(this@SignUpActivity, LoginActivity::class.java)) }
    }
}
