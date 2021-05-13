
package `in`.codeandroid.firebasedemo


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_rely.*
import kotlinx.android.synthetic.main.activity_type.*

import java.util.*


class InfoActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var authListener: FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)
        auth = FirebaseAuth.getInstance()

        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        button1.setOnClickListener{
            val intent = Intent(this@InfoActivity, RowActivity::class.java);
            val name1 = "พันธุ์"
            intent.putExtra("TopicNameA", name1)
            startActivity(intent)
            finish()
        }
        button2.setOnClickListener{
            val intent = Intent(this@InfoActivity, GeneActivity::class.java);
            val name1 = "พันธุ์"
            intent.putExtra("TopicNameA", name1)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Back",Toast.LENGTH_LONG).show()
        var intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
}}