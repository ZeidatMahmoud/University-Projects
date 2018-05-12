package com.example.mahmo.ven;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;

import java.util.HashMap;

public class CustomAdabter extends ArrayAdapter<HashMap<Integer,String>> {
    private HashMap items = new HashMap<Integer,String>() ;
    Context con ;

    public CustomAdabter( Context context, int resource, HashMap<Integer,String> map) {
        super(context, resource);

        items = map ;
        con = context ;
    }
    public int getCount(){
        return items.size();
    }
    public View getView(int position , View convertView , ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View raw = inflater.inflate(R.layout.listitems,null,true);

        TextView text =(TextView)raw.findViewById(R.id.text);
        text.setText(items.get(position).toString());
        return raw ;
    }
}
