package com.mylanguagepal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final String TABLE_TAGS = "Tags";

    private final String COL_ID = "_id";
    private final String TABLE_TAGS_COL_NAME = "name";

    private final MigrationsHelper _dbHelper;
    private int _openCounter;
    private SQLiteDatabase _database;

    private List<Tag> _tags = new ArrayList<>();

    public DatabaseManager(MigrationsHelper dbHelper) {
        _dbHelper = dbHelper;
        _openCounter = 0;
        _database = null;
    }

    public List<Tag> getAllTags() {
        openDatabase();
        List<Tag> tags = new ArrayList<>();

        try {
            Cursor cursor = _database.query(TABLE_TAGS,
                    null, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Tag tag = cursorToTag(cursor);
                tags.add(tag);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        } finally {
            closeDatabase();
        }

        return tags;
    }

    public Tag createTag(Tag tag) {
        openDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(TABLE_TAGS_COL_NAME, tag.getName());

            long insertId = _database.insert(TABLE_TAGS, null, values);

            Cursor cursor = _database.query(TABLE_TAGS,
                    null, COL_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            Tag newTag = cursorToTag(cursor);
            cursor.close();

            return newTag;
        } finally {
            closeDatabase();
        }
    }

    public void removeTag(Tag tag) {
        openDatabase();

        try {
            int id = tag.getId();

            _database.delete(TABLE_TAGS, COL_ID
                    + " = " + id, null);
        } finally {
            closeDatabase();
        }
    }

    private synchronized SQLiteDatabase openDatabase() {
        _openCounter++;
        if(_openCounter == 1) {
            // Opening new database
            _database = _dbHelper.getWritableDatabase();
        }
        return _database;
    }

    private synchronized void closeDatabase() {
        _openCounter--;
        if(_openCounter == 0) {
            // Closing database
            _database.close();
        }
    }

    private Tag cursorToTag(Cursor cursor) {
        Tag comment = new Tag();
        comment.setId(cursor.getInt(0));
        comment.setName(cursor.getString(1));
        return comment;
    }
}
