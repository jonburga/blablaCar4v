package com.blablacar4v.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blablacar4v.Program;
import com.blablacar4v.R;
import com.blablacar4v.models.Travel;
import com.blablacar4v.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.RecyclerDataHolder> {

    private final List<Travel> listTravel;
    private OnItemClickListener itemListener;
    public UserAdapter(List<Travel> listData, OnItemClickListener onItemClickListener) {
        this.listTravel = listData;
        this.itemListener = onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.RecyclerDataHolder holder, int position) {
        Travel travel = listTravel.get(position);
        holder.assignData(travel , itemListener);
    }

    @Override
    public int getItemCount() {
        if (listTravel != null) {
            Log.e("Success", "///////////Viajes encontrados: " + listTravel.size());
            return listTravel.size();
        } else {
            Log.e("Error", "No se han encontrado viajes");
            return 0; // O devuelve cualquier otro valor predeterminado adecuado
        }
    }

    public static class RecyclerDataHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textName;
        public TextView textDeparturePlace;
        public TextView textDepartureDate;
        public TextView textDepartureHour;
        public TextView textSeats;
        public Button button;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textName);
            textDeparturePlace = itemView.findViewById(R.id.textDeparturePlace);
            textDepartureDate = itemView.findViewById(R.id.textDateTravel);
            textDepartureHour = itemView.findViewById(R.id.textHourDeparture);
            textSeats = itemView.findViewById(R.id.textSeats);
            button = itemView.findViewById(R.id.buttonEntry);
        }

        public void assignData(Travel travel, OnItemClickListener onItemClick) {
            User user = Program.management.getUser(travel.getUserPublicated());
            textName.setText(user.getName());
            textDeparturePlace.setText(travel.getDeparturePlace());
            textDepartureDate.setText((CharSequence) travel.getDepartureDate());
            textDepartureHour.setText(travel.getDepartureHour());
            textSeats.setText(travel.getSeats() + " asientos");
            itemView.setOnClickListener(v -> onItemClick.onItemClick(travel));
            button.setOnClickListener(v -> onItemClick.onItemClick(travel));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Travel travel);
    }
}