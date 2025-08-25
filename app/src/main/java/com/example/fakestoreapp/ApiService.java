package com.example.fakestoreapp; // Asegúrate que este sea tu paquete

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products")
    Call<List<Producto>> getProductos();

}