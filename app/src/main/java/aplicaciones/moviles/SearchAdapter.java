package aplicaciones.moviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter implements Filterable {


    Context context;
    List<Search> searchList;
    List<Search> searchesOriginal;
    ValueFilter valueFilter;
    String[] search;

    public SearchAdapter(Context context, List<Search> searchList) {
        this.context = context;
        this.searchList = searchList;
        this.searchesOriginal = searchList;
    }

    @Override
    public int getCount() {
        return searchList.size();
    }

    @Override
    public Search getItem(int position) {
        return searchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.card_view_search, null);
        }

        Search item = (Search) getItem(position);

        ((TextView) convertView.findViewById(R.id.text)).setText(item.name);
        switch (item.Drawable) {
            case 1:
                ((ImageView) convertView.findViewById(R.id.img_view)).setBackgroundResource(R.drawable.banana);
                break;
            case 2:
                ((ImageView) convertView.findViewById(R.id.img_view)).setBackgroundResource(R.drawable.manzana);
                break;
        }


        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Search> filterList = new ArrayList<Search>();
                search = constraint.toString().split(" ");
                for (Search item : searchList) {
                    if ((item.name.toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(item);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                search = "".split(" ");
                results.count = searchesOriginal.size();
                results.values = searchesOriginal;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            searchList = (ArrayList<Search>) results.values;
            notifyDataSetChanged();
        }
    }
}


