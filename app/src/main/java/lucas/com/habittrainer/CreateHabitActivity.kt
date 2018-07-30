package lucas.com.habittrainer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_create_habit.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {

    private val CHOOSE_IMAGE = 1
    private var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }

    fun storeHabit(v: View) {
        if (et_title.text.toString().isBlank() || et_desc.text.toString().isBlank()) {
            errorMessage("Need a title or description")
        } else if (imageBitMap == null) {
            errorMessage("need to choose an image")
        }

        tv_error.visibility = View.INVISIBLE
        

    }

    private fun errorMessage(message: String) {
        tv_error.text = message
        tv_error.visibility = View.VISIBLE
    }

    fun chooseImage(v: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Choose an Image")
        startActivityForResult(chooser, CHOOSE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE && resultCode == Activity.RESULT_OK
                && data != null && data.data != null) {

            Log.d("Image", "Image Selected")

            val bitmap = tryReadBitMap(data.data)

            bitmap?.let {
                this.imageBitMap = bitmap
                iv_image_preview.setImageBitmap(bitmap)

            }
        }
    }

    fun tryReadBitMap(data: Uri): Bitmap? {
        return try {
            MediaStore.Images.Media.getBitmap(contentResolver, data)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
