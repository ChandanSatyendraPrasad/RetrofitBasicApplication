package chandan.prasad.retrofitbasicapplication.apiinterface;

import chandan.prasad.retrofitbasicapplication.requestbyapp.UserRequest;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("simpleregister.php")
    Call<UserRequest> saveUsers(
            @Field("user_name") String user_name,
            @Field("user_email") String user_email,
            @Field("user_phone") String user_phone,
            @Field("user_password") String user_password
    );
    // Call<UserResponse> saveUsers(@Body UserRequest userRequest);
}
