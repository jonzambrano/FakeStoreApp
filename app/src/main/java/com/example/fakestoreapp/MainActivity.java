package com.example.fakestoreapp; // Revisa que sea tu paquete

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inicializar el RecyclerView desde el layout
        recyclerView = findViewById(R.id.rvProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 2. Obtener los datos de la API
        obtenerProductosDeApi();
    }

    private void obtenerProductosDeApi() {
        // Definimos un TAG para filtrar nuestros logs fácilmente
        final String TAG = "API_CALL";

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Producto>> call = apiService.getProductos();

        Log.d(TAG, "Iniciando llamada a la API...");

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                // Este bloque se ejecuta cuando el servidor responde
                Log.d(TAG, "onResponse - El servidor ha respondido.");
                Log.d(TAG, "Código de respuesta: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    List<Producto> productos = response.body();
                    Log.d(TAG, "Respuesta exitosa. Número de productos recibidos: " + productos.size());

                    if (productos.isEmpty()) {
                        Log.w(TAG, "La lista de productos está vacía.");
                        Toast.makeText(MainActivity.this, "No se encontraron productos.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Configurar el adaptador y mostrar la lista
                    productoAdapter = new ProductoAdapter(MainActivity.this, productos);
                    recyclerView.setAdapter(productoAdapter);
                    Log.d(TAG, "Adaptador configurado y asignado al RecyclerView.");

                } else {
                    // Si la respuesta no fue exitosa
                    Log.e(TAG, "Respuesta no exitosa. Código: " + response.code());
                    Toast.makeText(MainActivity.this, "Error al obtener los productos. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                // Este bloque se ejecuta si hay un error de conexión
                Log.e(TAG, "onFailure - Error en la conexión.", t);
                Toast.makeText(MainActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}