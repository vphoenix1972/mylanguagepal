package com.mylanguagepal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class TagsFragment extends android.support.v4.app.Fragment
    implements View.OnClickListener {
    private DatabaseManager _db;

    public TagsFragment() {
        _db = App.instance().db();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_tags, null);

        List<Tag> tags = _db.getAllTags();

        ArrayAdapter<Tag> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tags);
        ListView listView = (ListView) view.findViewById(R.id.listview_tags);
        listView.setAdapter(adapter);

        Button buttonAdd = (Button) view.findViewById(R.id.button_add_tag);
        buttonAdd.setOnClickListener(this);
        Button buttonDelete = (Button) view.findViewById(R.id.button_delete_tag);
        buttonDelete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        ListView listView = (ListView) getActivity().findViewById(R.id.listview_tags);
        ArrayAdapter<Tag> adapter = (ArrayAdapter<Tag>) listView.getAdapter();

        if (id == R.id.button_add_tag) {
            Tag tag = new Tag();
            tag.setName("New tag");

            // Insert into db and get id
            tag = _db.createTag(tag);

            // Update view
            adapter.add(tag);
            adapter.notifyDataSetChanged();

            return;
        }
        if (id == R.id.button_delete_tag) {
            if (adapter.getCount() > 0) {
                Tag tag = adapter.getItem(0);

                // Remove from database
                _db.removeTag(tag);

                // Update view
                adapter.remove(tag);
                adapter.notifyDataSetChanged();
            }
            return;
        }
    }
}
