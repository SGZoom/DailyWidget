package gzoomswiperefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.widgetpro.R;

import java.util.List;

/**
 * Created by gzoom on 2016/11/14.
 */
public class TempAdapter extends RecyclerView.Adapter<TempAdapter.holder>{

    LayoutInflater layoutInflater;
    List<String>data;

    public TempAdapter(Context c, List<String>list)
    {
        layoutInflater=LayoutInflater.from(c);
        data=list;
    }


    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.temp_item,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        Log.e("fish","size=="+data.size());
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder{

        TextView textView;
        public holder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.temp_textview);
            textView.setGravity(Gravity.CENTER);
        }
    }
}
