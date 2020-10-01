package chandan.prasad.retrofitbasicapplication.apiclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import chandan.prasad.retrofitbasicapplication.apiinterface.UserService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit getRetrofit()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://myleader.sparsematrix.co.in/ott/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
        return retrofit;
    }
    public static UserService getUserService()
    {
        UserService userService = getRetrofit().create(UserService.class);
        return  userService;
    }
}
