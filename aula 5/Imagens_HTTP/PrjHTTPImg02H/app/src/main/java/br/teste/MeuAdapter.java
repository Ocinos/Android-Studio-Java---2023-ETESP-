package br.teste;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewholder> {
    final Jooj jooj;
    List<Contatos> lst;

    public MeuAdapter(List<Contatos> lst, Jooj jooj)
    {
        this.lst = lst;
        this.jooj = jooj;
    }

    public MeuViewholder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meu_item_recycler, parent, false);
        return new MeuViewholder(view, jooj);
    }

    @Override
    public void onBindViewHolder(MeuViewholder holder, int position) {
        holder.lblNome.setText(lst.get(position).getNome());
        holder.lblSobrenome.setText(lst.get(position).getSobrenome());
        holder.lblIdade.setText(lst.get(position).getIdade());
        holder.img.setImageResource(lst.get(position).getImagem());
    }

    @Override
    public int getItemCount() {
            return lst.size();
    }

    public class MeuViewholder extends RecyclerView.ViewHolder
    {
        TextView lblNome, lblSobrenome, lblIdade;
        ImageView img;

        public MeuViewholder(View itemView, Jooj jooj)
        {
            super(itemView);
            lblNome = itemView.findViewById(R.id.lblNome);
            lblSobrenome = itemView.findViewById(R.id.lblSobrenome);
            lblIdade = itemView.findViewById(R.id.lblIdade);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(jooj != null) {
                        int posi = getAdapterPosition();
                        if(posi != RecyclerView.NO_POSITION)
                            jooj.onItemClick(posi);
                    }


                }
            });
        }
    }
}


