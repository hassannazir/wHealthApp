
import com.wHealth.model.*
import com.wHealth.network.response.*
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

    @PUT("api/Users")
    @Headers("Content-Type: application/json")
    suspend fun updateUserApi(
        @Query("id") id: Int,
        @Body appUser: AppUser
    ): Response<UpdateProfileResponse>

    @GET("api/Data")
    @Headers("Content-Type: application/json")
    suspend fun getActiveClinicsApi(
    ): Response<GetAllClinicsResponse>

    @POST("api/Data")
    @Headers("Content-Type: application/json")
    suspend fun  getActiveDoctorsApi(
            @Query("Cid") Cid:Int
    ): Response<GetAllClinicsResponse>

    @POST("api/Requests")
    @Headers("Content-Type: application/json")
    suspend fun  getWorkingClinicsApi(
            @Query("doc_id") did:Int
    ): Response<GetAllClinicsResponse>

    @DELETE("api/Data")
    @Headers("Content-Type: application/json")
    suspend fun  getInActiveDoctorsApi(
            @Query("Cid") Cid:Int
    ): Response<GetAllClinicsResponse>

    @DELETE("api/Data")
    @Headers("Content-Type: application/json")
    suspend fun  getpendingDoctorsRequestApi(
            @Query("Cid") Cid:Int
    ): Response<GetAllClinicsResponse>

    @GET("api/Requests")
    @Headers("Content-Type: application/json")
    suspend fun reqToJoinClinicApi(
            @Query("docId") docId: Int,
            @Query("clinicId") clinicId: Int?
    ): Response<ClinicReqResponse>

    @PUT("api/Requests")
    @Headers("Content-Type: application/json")
    suspend fun clinicApprovesDocApi(
            @Query("cid") cid: Int,
            @Query("did") did: Int
    ): Response<ClinicReqResponse>

}

