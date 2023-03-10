package com.example.img.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.img.R;
import com.example.img.controleur.Controle;
import com.example.img.modele.Profile;
import com.example.img.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {

    private ArrayList<Profile> lesProfiles;
    private LayoutInflater inflater;
    private Controle controle;
    private Context context;

    public HistoListAdapter(Context context, ArrayList<Profile> lesProfiles) {
        this.lesProfiles = lesProfiles;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.controle = Controle.getInstance(null);
    }

    /**
     * Retourne le nombre de lignes
     * @return
     */
    @Override
    public int getCount() {
        return lesProfiles.size();
    }

    /**
     * Retourne l'item de la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return lesProfiles.get(i );
    }

    /**
     * Retourne un indice par rapport à la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Retourne la ligne formatée
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Création d'un holder
        ViewHolder holder;
        //Vérifier si la ligne exist
        if (view == null) {
            holder = new ViewHolder();
            //La ligne est construite avec un formatage (inflater) relié à layout_liste_histo
            view = inflater.inflate(R.layout.layout_liste_histo, null);
            //Chaque propriété du holder est reliée à un propriété graphique
            holder.txtListDate = (TextView) view.findViewById(R.id.txtListDate);
            holder.txtListIMG = (TextView) view.findViewById(R.id.txtListIMG);
            holder.btnListSuppr = (ImageButton) view.findViewById(R.id.btnListSuppr);
            //Affecter le holder à la vue
            view.setTag(holder);
        } else {
            //Récupération du holder dans la ligne existante
            holder = (ViewHolder) view.getTag();
        }

        //Valorisation du contenu du holder (donc de la ligne)
        holder.txtListDate.setText(MesOutils.convertDateToString(String.valueOf(lesProfiles.get(i).getDateMesure())));
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfiles.get(i).getImg()));
        holder.btnListSuppr.setTag(i);
        //Clic sur la croix pour supprimer le profil enrégistré
        holder.btnListSuppr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = (int) v.getTag();
                //Demande de suppression au controleur
                controle.delProfile(lesProfiles.get(position));
                //Raffraichir la liste
                notifyDataSetChanged();
            }
        });

        holder.txtListDate.setTag(i);
        //Clic sur le reste de la ligne
        holder.txtListDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = (int) v.getTag();
                //Demande de l'affichage du profil dans CalculActivity
                ((HistoActivity)context).afficheProfile(lesProfiles.get(position));
            }
        });

        holder.txtListIMG.setTag(i);
        //Clic sur le reste de la ligne
        holder.txtListIMG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = (int) v.getTag();
                //Demande de l'affichage du profil dans CalculActivity
                ((HistoActivity)context).afficheProfile(lesProfiles.get(position));
            }
        });
        return view;
    }

    private class ViewHolder {
        ImageButton btnListSuppr;
        TextView txtListDate;
        TextView txtListIMG;
    }
}
