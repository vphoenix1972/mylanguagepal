package com.mylanguagepal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class DashboardFragment extends android.support.v4.app.Fragment
        implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dashboard, null);

        List<Tag> tags = App.instance().db().tags();

        ArrayAdapter<Tag> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tags);
        ListView listView = (ListView) view.findViewById(R.id.listview_list);
        listView.setAdapter(adapter);

        Button buttonAdd = (Button) view.findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this);
        Button buttonDelete = (Button) view.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        ListView listView = (ListView) getActivity().findViewById(R.id.listview_list);
        ArrayAdapter<Tag> adapter = (ArrayAdapter<Tag>) listView.getAdapter();

        if (id == R.id.button_add) {
            Tag tag = new Tag();
            tag.setName("New tag");
            //App.instance().db().addTag(tag);
            adapter.add(tag);
            adapter.notifyDataSetChanged();
            return;
        }
        if (id == R.id.button_delete) {
            //App.instance().db().removeTag(0);
            if (adapter.getCount() > 0)
                adapter.remove(adapter.getItem(0));
            adapter.notifyDataSetChanged();
            return;
        }
    }
}
