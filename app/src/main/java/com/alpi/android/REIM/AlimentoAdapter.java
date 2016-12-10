package com.alpi.android.REIM;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpi.android.REIM.helper.ItemTouchHelperAdapter;
import com.alpi.android.REIM.helper.ItemTouchHelperViewHolder;
import com.alpi.android.REIM.helper.OnStartDragListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class AlimentoAdapter extends RecyclerView.Adapter<AlimentoAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    private ArrayList<Alimento> alimentos;
    private Context context;
    private final OnStartDragListener mDragStartListener2;

    public String[] getMatrizInicial() {
        String [] matrizInicial = new String[alimentos.size()];
        for(int i = 0; i < alimentos.size(); i++) {
            matrizInicial[i] = alimentos.get(i).getNombreAlimento();
        }
        return matrizInicial;
    }

        AlimentoAdapter(Context context, ArrayList<Alimento> alimentos, OnStartDragListener dragStartListener) {
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
        if(alimentos.get(position).getCorrespondeAlimento()) {
            final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.no_corresponde);
            mediaPlayer.start();

        } else {
            final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.corresponde);
            mediaPlayer.start();
        }
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
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.animacion_items);
            itemView.setAnimation(animation);
        }


        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public String[] getMatrizFinal() {
        if(alimentos.size() == 0) {
            String[] matrizFinal = new String[1];
            matrizFinal[0] = "Vacío";
            return matrizFinal;
        } else {
            String[] matrizFinal = new String[alimentos.size()];
            for (int i = 0; i < matrizFinal.length; i++) {
                matrizFinal[i] = alimentos.get(i).getNombreAlimento();
            }
            return matrizFinal;
        }
    }

    public int getCorrespondeMatrizFinal() {
        int correspondeAlimentos = 0;
        for (int i=0; i < alimentos.size(); i++) {
            if(alimentos.get(i).getCorrespondeAlimento()) {
                correspondeAlimentos = correspondeAlimentos + 1;
            }
        }
        return correspondeAlimentos;
    }
}