package com.mylanguagepal;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final MigrationsHelper _dbHelper;
    private List<Tag> _tags = new ArrayList<>();

    public DatabaseManager(MigrationsHelper dbHelper) {
        _dbHelper = dbHelper;

        Tag tag = new Tag();
        tag.setName("Tag 1");
        _tags.add(tag);

        tag = new Tag();
        tag.setName("Tag 2");
        _tags.add(tag);

        tag = new Tag();
        tag.setName("Tag 3");
        _tags.add(tag);
    }

    public List<Tag> tags() {
        return _tags;
    }

    public void addTag(Tag tag) {
        _tags.add(tag);
    }

    public void removeTag(int i) {
        if (i >= 0 && i < _tags.size())
            _tags.remove(i);
    }
}
