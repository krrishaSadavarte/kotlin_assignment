package com.example.shoopzi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.util.TimeZone.SystemTimeZoneType
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.room.Room
import com.example.shoopzi.Models.Profile
import com.example.shoopzi.Utils.Utils
import com.example.shoopzi.adapter.CuisineListAdapter
import com.example.shoopzi.database.AppDatabase
import com.example.shoopzi.databinding.ActivityCuisineBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread

class CuisineActivity : AppCompatActivity() {
    lateinit var binding: ActivityCuisineBinding
    var db: AppDatabase? = null
    private var cuisine: Profile? = null
    private lateinit var Madapter: CuisineListAdapter
    private var imageBitmap:Bitmap?=null
    private var imagePath =""
    private val gallaryLauncher= registerForActivityResult(ActivityResultContracts.GetContent()){
      if(it!= null){
          imagePath = ""
          imageBitmap = getBitMap(it)
          imageBitmap?.let { bitmap->
              binding.ivThumbnail.setImageBitmap(bitmap)
          }
            }else{
                Log.d("Pick Media","no media selected")
            }
         }

    private fun getBitMap(uri: Uri): Bitmap? {
        return try{
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
            bitmap
        }catch (e:IOException){
            e.printStackTrace()
            null
        }

    }

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        if(it!= null){
            binding.ivThumbnail.setImageURI(it)
        }else{
            Log.d("Pick Media","no media selected")
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCuisineBinding.inflate(layoutInflater)
        setContentView(binding.root)

       cuisine= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("cuisine",Profile::class.java)
        }else{
            intent.getParcelableExtra("cuisine")

        }

        cuisine?.let {

            binding.btnAddCuisines.text="update cuisine"
            binding.etName.setText(it.U_name)
            binding.etBio.setText(it.U_bio)
            imageBitmap=Utils.getBitmapFromAbsolutePath(cuisine!!.U_img)
            imagePath = cuisine!!.U_img
            binding.ivThumbnail.setImageBitmap(imageBitmap)

        }

        binding.ivThumbnail.setOnClickListener {
            gallaryLauncher.launch("iamge/*")
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly ))
        }





//        binding.recycler
        db=AppDatabase.getInstance(this)

        binding.btnAddCuisines.setOnClickListener {
            var name = binding.etName.text.toString().trim()
            var bio = binding.etBio.text.toString().trim()

            if (imageBitmap == null){
                Toast.makeText(this,"please select image first",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            UpdateRecords(name, bio)
        }
    }
    var msg =""
    private fun UpdateRecords(name: String, bio: String) {
            thread(start = true){
                var PROFILES = Profile(U_name = name,
                    U_bio = bio,
                    U_img = getImapePath(imageBitmap,imagePath)?:  "",
                    U_id = if(cuisine!= null)cuisine!!.U_id else 0,
                    createAt = if(cuisine!= null)cuisine!!.createAt else System.currentTimeMillis()
                )

                try {

                    if(cuisine!=null){
                        //update
                        db!!.ProfileDao().updateProfile(PROFILES)
                        msg="record updated"
                    }else{
                        db!!.ProfileDao().insertProfile(PROFILES)
                        msg="record inserted"

                    }
                    runOnUiThread {
                        db!!.ProfileDao().getAllProfiles()
                        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()
                        onBackPressedDispatcher.onBackPressed()
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
    }
private fun getImapePath(bitmap: Bitmap?,imagePath : String):String?{
   if (imagePath.isEmpty()){
       var root : File = filesDir
       var fileName = "${System.currentTimeMillis()}.png"
       var file = File(root.path,fileName)
       try {
           val fileOutputStream = FileOutputStream(file)
           bitmap?.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream)
           fileOutputStream.flush()
           fileOutputStream.close()
       }catch (e :IOException){
           e.printStackTrace()
           return null
       }
       return file.absolutePath
   }
    return imagePath

}

}