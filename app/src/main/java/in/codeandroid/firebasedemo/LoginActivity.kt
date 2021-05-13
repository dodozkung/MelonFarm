package `in`.codeandroid.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val TAG: String = "Login Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        if(auth!!.currentUser != null){
            val d = Log.d(TAG, "Continue" + auth!!.currentUser!!.email)
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        btn_log_in.setOnClickListener {
            val email = tv_username.text.toString().trim{it<= ' ' }
            val password = tv_password.text.toString().trim{it<= ' ' }
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
            auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) {
                        tv_password.error = "Please check your password. Password must have minimum 6 characters."
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        toast("Authentication Failed: " + task.exception!!.message)
                        Log.d(TAG, "Authentication Failed: " + task.exception!!.message)
                    }
                } else {
                    toast("Sign in successfully!")
                    Log.d(TAG, "Sign in successfully!")
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }
            }

        }
        btn_sign_up.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        btn_for.setOnClickListener {
            startActivity(Intent(this,ForgetActivity::class.java))
            finish()
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?)
    {if(auth!!.currentUser != null){
        val d = Log.d(TAG, "Continue" + auth!!.currentUser!!.email)
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }


}}
