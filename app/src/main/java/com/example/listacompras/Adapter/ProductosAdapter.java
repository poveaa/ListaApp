package com.example.listacompras.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listacompras.Entity.Productos;
import com.example.listacompras.R;
import com.example.listacompras.VerProducto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder> {


    ArrayList<Productos> listaProductos;
    ArrayList<Productos> listaInicial;



    public ProductosAdapter(ArrayList<Productos> listaProductos){
        this.listaProductos =listaProductos;
        listaInicial = new ArrayList<>();
        listaInicial.addAll(listaProductos);
    }

    @NonNull
    @Override
    public ProductosAdapter.ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, null, false);
        return  new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
        holder.vistaNombre.setText(listaProductos.get(position).getNombre());
        holder.vistaCantidad.setText(listaProductos.get(position).getCantidad());
        holder.vistaCategoria.setText(listaProductos.get(position).getCategoria());
    }

    public void filtrar (String txtBuscar){
        int largo = txtBuscar.length();
        if(largo==0){
            listaProductos.clear();
            listaProductos.addAll(listaInicial);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Productos> conjunto = listaProductos.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(conjunto);
            }else{
                for (Productos c: listaInicial){
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaProductos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder{

        TextView vistaNombre, vistaCantidad, vistaCategoria;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            vistaNombre = itemView.findViewById(R.id.vistaNombre);
            vistaCantidad = itemView.findViewById(R.id.vistaCantidad);
            vistaCategoria = itemView.findViewById(R.id.vistaCategoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerProducto.class);
                    intent.putExtra("ID", listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
