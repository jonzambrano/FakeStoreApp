package com.example.fakestoreapp; // Revisa que sea tu paquete

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Locale;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;
    private Context context;

    public ProductoAdapter(Context context, List<Producto> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        holder.tvTitulo.setText(producto.getTitle());
        holder.tvCategoria.setText("Category: " + producto.getCategory());
        // Formateamos el precio para que se vea bien
        holder.tvPrecio.setText(String.format(Locale.US, "Price: %.2f", producto.getPrice()));
        holder.tvDescripcion.setText(producto.getDescription());

        // Usamos Glide para cargar la imagen desde la URL
        Glide.with(context)
                .load(producto.getImage())
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    // El ViewHolder
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo, tvCategoria, tvPrecio, tvDescripcion;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivProductoImagen);
            tvTitulo = itemView.findViewById(R.id.tvProductoTitulo);
            tvCategoria = itemView.findViewById(R.id.tvProductoCategoria);
            tvPrecio = itemView.findViewById(R.id.tvProductoPrecio);
            tvDescripcion = itemView.findViewById(R.id.tvProductoDescripcion);
        }
    }
}