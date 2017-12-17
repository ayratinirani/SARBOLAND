package ir.ounegh.vardast;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aseme on 15/12/2017.
 */

public class MlocationAdapter extends Adapter<MlocationAdapter.Mviewholder>{

    ArrayList<Mlocation>items;
    Map<String,Integer> logos;
    Context context;
    private static MyClickListener myClickListener;
    public MlocationAdapter(ArrayList<Mlocation>data, Context c){

        this.items=data;
        this.context=c;

    }
    @Override
    public Mviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loc, parent, false);

        Mviewholder dataObjectHolder = new Mviewholder(view);


        return dataObjectHolder;
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }


    @Override
    public void onBindViewHolder(Mviewholder holder, int position) {
        holder.txname.setText(items.get(position).getName());
        holder.txphone.setText(items.get(position).getPhone());
      //  holder.txcat.setText(items.get(position).getCategory());
       // holder.imglogo.setImageResource();

    }

   private void setlogos(){

   }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
     class Mviewholder extends RecyclerView.ViewHolder{
         TextView txname;
         ImageView imglogo;
         TextView txcat;
         TextView txphone;
        public Mviewholder(View itemView) {
            super(itemView);
            txname=itemView.findViewById(R.id.item_name);
          //  txcat=itemView.findViewById(R.id.item_cat);
            txphone=itemView.findViewById(R.id.item_phone);
          // imglogo=itemView.findViewById(R.id.item_logo);
        }

         public void onClick(View v) {
             myClickListener.onItemClick(getPosition(), v);
         }
    }
}
