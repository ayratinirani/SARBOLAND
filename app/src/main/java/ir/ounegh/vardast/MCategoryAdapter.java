package ir.ounegh.vardast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aseme on 15/12/2017.
 */

public class MCategoryAdapter extends BaseAdapter {
    ArrayList<Category>items;
    Context context;
    public MCategoryAdapter (ArrayList<Category>i, Context c)
    {
        this.context=c;
        this.items=i;


    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_cat, parent, false);
        }

        // get current item to be displayed
        Category currentItem = (Category) getItem(i);
        int resID = context.getResources().getIdentifier(Venum.icons.get(currentItem.getName()),
                "drawable", context.getPackageName());
        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.item_cat_name);
        ImageButton img=convertView.findViewById(R.id.item_cat_image);
        img.setImageResource(resID);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getName());


        // returns the view for the current row
        return convertView;
}
}
