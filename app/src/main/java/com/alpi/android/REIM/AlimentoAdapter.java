package com.alpi.android.REIM;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alpi.android.REIM.helper.ItemTouchHelperAdapter;
import com.alpi.android.REIM.helper.ItemTouchHelperViewHolder;
import com.alpi.android.REIM.helper.OnStartDragListener;
import com.squareup.picasso.Picasso;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class AlimentoAdapter extends RecyclerView.Adapter<AlimentoAdapter.ViewHolder>
implements ItemTouchHelperAdapter {

    private ArrayList<Alimento> alimentos;
    public String alimentosFinales[] = {};
    private Context context;
    private final OnStartDragListener mDragStartListener2;
    int duracionToast = Toast.LENGTH_SHORT;

    public AlimentoAdapter(Context context, ArrayList<Alimento> alimentos, OnStartDragListener dragStartListener) {
        this.context = context;
        this.alimentos = alimentos;
        mDragStartListener2 = dragStartListener;
        long seed = System.nanoTime();
        Collections.shuffle(alimentos, new Random(seed));
    }

    @Override
    public AlimentoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_handler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.nombre.setText(alimentos.get(position).getNombreAlimento());
        Picasso.with(context).load(alimentos.get(position).getImagenAlimento()).resize(85, 85).into(holder.imagen);
        holder.imagen.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.imagen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener2.startSwipe(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        alimentos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(alimentos, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return alimentos.size();
    }

    public void getResult() {
        String[] alimentosFinales = new String[alimentos.size()];
        int index = 0;
        for (Alimento nombreAlimento : alimentos) {
            alimentosFinales[index] = String.valueOf( nombreAlimento );
            index++;
        }
        Toast toast = Toast.makeText(context, alimentosFinales[0], duracionToast);
        toast.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        TextView nombre;
        ImageView imagen;

        public ViewHolder(View view) {
            super(view);

            nombre = (TextView) view.findViewById(R.id.nombre);
            imagen = (ImageView) view.findViewById(R.id.imagen);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
