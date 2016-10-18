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

import com.alpi.android.REIM.helper.ItemTouchHelperAdapter;
import com.alpi.android.REIM.helper.ItemTouchHelperViewHolder;
import com.alpi.android.REIM.helper.OnStartDragListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ElementosBomberosAdapter extends RecyclerView.Adapter<ElementosBomberosAdapter.ViewHolder>
implements ItemTouchHelperAdapter {

    private ArrayList<ElementosBomberos> elementosBomberos;
    private Context context;
    private final OnStartDragListener mDragStartListener;

    public ElementosBomberosAdapter(Context context, ArrayList<ElementosBomberos> elementosBomberos, OnStartDragListener dragStartListener) {
        this.context = context;
        this.elementosBomberos = elementosBomberos;
        mDragStartListener = dragStartListener;
        long seed = System.nanoTime();
        Collections.shuffle(elementosBomberos, new Random(seed));
    }

    @Override
    public ElementosBomberosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_handler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.nombre.setText(elementosBomberos.get(position).getNombreElementoBomberos());
        Picasso.with(context).load(elementosBomberos.get(position).getImagenElementoBomberos()).resize(85,85).into(holder.imagen);
        holder.imagen.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.imagen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        elementosBomberos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(elementosBomberos, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return elementosBomberos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        TextView nombre;
        ImageView imagen;

        public ViewHolder(View view) {
            super(view);

            nombre = (TextView)view.findViewById(R.id.nombre);
            imagen = (ImageView)view.findViewById(R.id.imagen);
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
