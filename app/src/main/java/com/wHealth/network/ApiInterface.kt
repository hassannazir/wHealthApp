
import com.wHealth.model.*
import com.wHealth.network.response.*
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    //https://whealthapp.azurewebsites.net/
    //Creating a User
    @POST("api/Users/Create")
    @Headers("Content-Type: application/json")
    suspend fun registerUserApi(
        @Body appUser: AppUser
    ): Response<RegisterResponse>

    //Logged in a User
    @POST("api/Accounts/Login")
    @Headers("Content-Type: application/json")
    suspend fun loginUserApi(
        @Query("username") username: String,
        @Query("pass")pass: String
    ): Response<LoginResponse>

    //Logged in a User
    @GET("api/PasswordHandler/SendCodeToEmail")
    @Headers("Content-Type: application/json")
    suspend fun verifyUserApi(
            @Query("usrname") usrname: String
    ): Response<ForgotPasswordResponse>

    //Logged in a User
    @PUT("api/PasswordHandler/SaveUpdatedPassword")
    @Headers("Content-Type: application/json")
    suspend fun UpdatePassApi(
            @Query("usrname") usrname: String,
            @Query("newpass") newpass: String
    ): Response<UpdatePasswordResponse>

    @PUT("api/Users/Edit")
    @Headers("Content-Type: application/json")
    suspend fun updateUserApi(
        @Query("id") id: Int,
        @Body appUser: AppUser
    ): Response<UpdateProfileResponse>

    @GET("api/Data/Get")
    @Headers("Content-Type: application/json")
    suspend fun getDoctorAvailableClinicsApi(
            @Query("doctorId") doctorId:Int
    ): Response<GetAllClinicsResponse>

    @GET("api/Data/Get")
    @Headers("Content-Type: application/json")
    suspend fun getAvailableClinicsApi(
    ): Response<GetAllClinicsResponse>

    @GET("api/Bookings/PatientsBookingRequests")
    @Headers("Content-Type: application/json")
    suspend fun BookedAppointments(
        @Query("doc_id") did:Int
    ): Response<BookAppointmentResponse>

    @GET("api/Bookings/ListOfPatientAppointments")
    @Headers("Content-Type: application/json")
    suspend fun PatientBookedAppointments(
        @Query("patientId") pid:Int
    ): Response<BookAppointmentResponse>

    @POST("api/Data/ActiveDoctors")
    @Headers("Content-Type: application/json")
    suspend fun  getActiveDoctorsApi(
            @Query("Cid") Cid:Int
    ): Response<GetAllClinicsResponse>

    @POST("api/Requests/clincsOfLoggoedInDoctors")
    @Headers("Content-Type: application/json")
    suspend fun  getWorkingClinicsApi(
            @Query("doc_id") did:Int
    ): Response<GetAllClinicsResponse>

    @DELETE("api/Data/InActiveDoctors")
    @Headers("Content-Type: application/json")
    suspend fun  getInActiveDoctorsApi(
            @Query("Cid") Cid:Int
    ): Response<GetAllClinicsResponse>

    @DELETE("api/Data/InActiveDoctors")
    @Headers("Content-Type: application/json")
    suspend fun  getpendingDoctorsRequestApi(
            @Query("Cid") Cid:Int
    ): Response<GetAllClinicsResponse>

    @GET("api/Requests/DoctorSendRequestToClinic")
    @Headers("Content-Type: application/json")
    suspend fun reqToJoinClinicApi(
            @Query("docId") docId: Int,
            @Query("clinicId") clinicId: Int?
    ): Response<ClinicReqResponse>

    @GET("api/Requests/scheduleOfDoctor")
    @Headers("Content-Type: application/json")
    suspend fun getClinicScheduleListApi(
            @Query("doc_Id") docId: Int,
            @Query("clinicId") clinicId: Int?
    ): Response<ClinicScheduleListResponse>

    @PUT("api/Requests/DoctorClinicApproval")
    @Headers("Content-Type: application/json")
    suspend fun clinicApprovesDocApi(
            @Query("cid") cid: Int,
            @Query("did") did: Int
    ): Response<ClinicReqResponse>

    @POST("api/Requests/setDocSchedule")
    @Headers("Content-Type: application/json")
    suspend fun clinicScheduleApi(
            @Query("doctorId") doctorId: Int,
            @Query("clinicId") clinicId: Int,
            @Query("startTime") startTime: String,
            @Query("endTime") endTime: String,
            @Query("startDate") startDate: String,
            @Query("endDate") endDate: String,
            @Query("recurring") recurring: Boolean,
            @Query("day") day: String,
            @Query("length") length: Int
    ): Response<ClinicReqResponse>

    @POST("api/Bookings/BookAppointment")
    @Headers("Content-Type: application/json")
    suspend fun bookSchedule(
        @Query("patientId") patientId: Int,
        @Query("doctorId") doctorId: Int,
        @Query("clinicId") clinicId: Int,
        @Query("status") status: Int,
        @Query("startTime") startTime: String,
        @Query("date") date: String,
        @Query("endTime") endTime: String
    ): Response<ClinicReqResponse>

    @GET("api/Bookings/ApproveOrCancelPatientRequest")
    @Headers("Content-Type: application/json")
    suspend fun ApproveCancelAppointment(
        @Query("appointmentId") appointmentId: Int,
        @Query("status") status: Boolean
    ): Response<ClinicReqResponse>
}

