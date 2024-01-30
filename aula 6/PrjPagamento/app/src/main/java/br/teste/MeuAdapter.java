package br.teste;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewholder> {
    List<Sus> lst;

    public MeuAdapter(List<Sus> lst)
    {
        this.lst = lst;
    }

    public MeuViewholder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_meu_adapter, parent, false);
        return new MeuAdapter.MeuViewholder(view);
    }

    @Override
    public void onBindViewHolder(MeuViewholder holder, int position) {
        holder.txtNome.setText(lst.get(position).getNome());
        holder.txtHoras.setText(lst.get(position).getHoras());
        holder.txtPagamento.setText(lst.get(position).getPagamento());
        holder.txtTotal.setText(lst.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class MeuViewholder extends RecyclerView.ViewHolder
    {
        EditText txtNome, txtHoras, txtPagamento, txtTotal;

        public MeuViewholder(View itemView)
        {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtHoras = itemView.findViewById(R.id.txtHoras);
            txtPagamento = itemView.findViewById(R.id.txtPagamento);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }
}


