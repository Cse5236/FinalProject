package com.example.whath.ui.api;

import com.example.whath.ui.model.Acceleration;
import com.example.whath.ui.model.TrainingAcceleration;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CassandraRestApi {

    @POST("/acceleration")
    Call<Void> sendAccelerationValues(@Body Acceleration acceleration);


    @POST("/training")
    Call<Void> sendTrainingAccelerationValues(@Body TrainingAcceleration trainingAcceleration);

}
