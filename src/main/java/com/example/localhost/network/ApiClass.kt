import com.example.localhost.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClass {

    companion object{
        private var retrofit:Retrofit?=null
        fun init(): ApiService {
            if (retrofit==null){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://192.168.0.12/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }


        }
    }
