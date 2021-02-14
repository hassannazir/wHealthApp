
import com.wHealth.model.*
import com.wHealth.network.response.ForgotPasswordResponse
import com.wHealth.network.response.RegisterResponse
import com.wHealth.network.response.LoginResponse
import com.wHealth.network.response.UpdatePasswordResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    //https://whealthapp.azurewebsites.net/
    //Creating a User
    @POST("api/Users")
    @Headers("Content-Type: application/json")
    suspend fun registerUserApi(
        @Body appUser: AppUser
    ): Response<RegisterResponse>

    //Logged in a User
    @POST("api/Accounts")
    @Headers("Content-Type: application/json")
    suspend fun loginUserApi(
        @Query("username") username: String,
        @Query("pass")pass: String
    ): Response<LoginResponse>



    //Logged in a User
    @GET("api/PasswordHandler")
    @Headers("Content-Type: application/json")
    suspend fun verifyUserApi(
            @Query("usrname") usrname: String
    ): Response<ForgotPasswordResponse>



    //Logged in a User
    @PUT("api/PasswordHandler")
    @Headers("Content-Type: application/json")
    suspend fun UpdatePassApi(
            @Query("usrname") usrname: String,
            @Query("newpass") newpass: String
    ): Response<UpdatePasswordResponse>

    //Once in a lifetime
//    companion object {
//        private var retrofit: Retrofit? = null
//        private const val BASE_URL = "https://whealthapp.azurewebsites.net/"
//
//        //Creating Retrofit Object
//        fun getRetrofitInstance(): ApiInterface? {
//            if (retrofit == null) {
//                //Create New Object for Retrofit
//
//                retrofit = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//            }
//            return retrofit?.create(ApiInterface::class.java)
//        }
//    }


}

