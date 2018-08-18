package pranay.com.kotlinankodialogs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewOnClick()
    }

    private fun setupViewOnClick() {

        buttonShowToast.setOnClickListener {
            toast("this is normal toast")
            //For long toast
            longToast("this is long toast message")
        }

        buttonSnackBar.setOnClickListener {
            snackbar(linerLayoutParent, "SnackBar is here!", "YES") { toast("Action clicked.") }
        }

        buttonShowYesNoAlert.setOnClickListener {
            alert("Dialog Title here", "Are you sure you want to continue...") {
                yesButton { toast("Yes button clicked") }
                noButton { toast("No button clicked") }
            }.show()
        }
        buttonAlertCustomView.setOnClickListener {
            alert {
                customView {
                    verticalLayout {
                        padding = dip(16)

                        textView(R.string.app_name) { }

                        textView(R.string.alert_with_custom_view) { }

                        textInputLayout {
                            hint = context.getString(R.string.ste_enter_value_here)
                            var editText = textInputEditText {
                                textSize = 14f
                            }
                        }

                    }
                }
            }.show()
        }

        buttonSelectionDialog.setOnClickListener {
            val gender = listOf("Male", "Female", "Other")
            selector("Your gender?", gender) { dialogInterface, i ->
                toast("Your selection is: ${gender[i]}")
                dialogInterface.dismiss()
            }
        }

        buttonProgressDialog.setOnClickListener {
            val dialog = progressDialog(message = "Please wait a bit…", title = "Click any where to cancel.")
            dialog.max=100
            dialog.incrementProgressBy(10)
            dialog.show()
        }
        buttonProgressDialogSec.setOnClickListener {
            val dialog = indeterminateProgressDialog(message = "Please wait a bit…", title = "Click any where to cancel.")
        }

        buttonOpenActivity.setOnClickListener {

            // This is normal code we use for intent to starting activity
            /*val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("test_id", 15)
            intent.setFlag(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)*/

            //This is Anko do...
            startActivity(intentFor<SecondActivity>("test_id" to 15).singleTop())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
