package com.comli.dawnbreaksthrough.personalintro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import com.comli.dawnbreaksthrough.personalintro.Elements.Databases;
import com.comli.dawnbreaksthrough.personalintro.Elements.Thumbnail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by SparklingGT on 9/12/2016.
 */
public class PersonLab
{
    private static PersonLab sPersonLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PersonLab get(Context context) {
        if (sPersonLab == null) {
            sPersonLab = new PersonLab(context);
        }
        return sPersonLab;
    }

    private PersonLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseCreator(mContext).getWritableDatabase();
    }

    public void addPerson(Person person) {
        ContentValues contentValues = getContentValues(person);
        mDatabase.insert(Databases.PersonTable.NAME, null, contentValues);
    }

    public void deletePerson(UUID uuid) {
        mDatabase.delete(Databases.PersonTable.NAME,
                Databases.PersonTable.Cols.UUID + " = ?", new String[]{uuid.toString()});
    }

    public Person getPerson(UUID uuid) {

        try (PersonCursorWrapper personCursorWrapper =
                     queryPerson(Databases.PersonTable.Cols.UUID + " = ?", new String[]{uuid.toString()})) {
            if (personCursorWrapper.getCount() == 0) {
                return null;
            } else {
                personCursorWrapper.moveToFirst();
                return personCursorWrapper.getPerson();
            }
        }
    }

    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        try (PersonCursorWrapper personCursorWrapper = queryPerson(null, null);) {
            personCursorWrapper.moveToFirst();
            while (!personCursorWrapper.isAfterLast()) {
                personList.add(personCursorWrapper.getPerson());
                personCursorWrapper.moveToNext();
            }
        }
        return personList;
    }



    public File getPhotoFile(Person person) {
        File externalFileDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFileDir == null) {
            return null;
        }
        return new File(externalFileDir, person.getPhotoFilename());
    }

    /**
     * Get the thumbnails path in the SD card
     * @param person the person you're currently working on.
     * @param size Either <strong>Elements.Thumbnail.Size.LARGE</strong>
     *             or <strong>Elements.Thumbnail.Size.SMALL</strong>
     * @return File that contains the filename and dir.
     */
    public File getThumbnailFile(Person person, int size) {
        if (size != Thumbnail.Size.LARGE && size != Thumbnail.Size.SMALL) {
            return null;
        }

        File externalCacheDir = mContext.getExternalCacheDir();
        if (externalCacheDir == null) {
            return null;
        }
        return new File(externalCacheDir, person.getThumbnailFilename(size));
    }

    /**
     * Use this method to update the database of a specific person.
     * @param person the person that you want to update.
     */

    public void updateDatabases(Person person) {
        ContentValues contentValues = getContentValues(person);
        mDatabase.update(Databases.PersonTable.NAME, contentValues,
                Databases.PersonTable.Cols.UUID + " = ?", new String[]{person.getUUID().toString()});
    }

    /**
     * <p><i>Tool that helps putting stuff in databases.</i></p><br>
     * Copy all the info of a specific person to ContentValues. <br>
     * <h6>Note: static here meaning it is independent, it doesn't need non-static members</h6>
     *
     * @param person the person that you want to get info from.
     * @return contentValues that contains the info about this person.
     */
    private static ContentValues getContentValues(Person person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Databases.PersonTable.Cols.UUID, person.getUUID().toString());
        contentValues.put(Databases.PersonTable.Cols.NAME, person.getName());
        contentValues.put(Databases.PersonTable.Cols.GENDER, person.getGender());
        contentValues.put(Databases.PersonTable.Cols.BIRTH, person.getDate().getTime());
        contentValues.put(Databases.PersonTable.Cols.INTRO, person.getIntro());

        return contentValues;
    }

    /**
     *<p><i>Tool that retrieves info about one person/people</i></p> <br>
     *Get the data about one person/people
     *
     *@param where Row identifier (e.g. UUID). <i>Nullable</i>
     *@param whereArgs Args for <i>where</i>. <i>Nullable</i>
     *@return PersonCursorWrapper that contains date about a person/people
    */
    private PersonCursorWrapper queryPerson(String where, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                Databases.PersonTable.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return new PersonCursorWrapper(cursor);
    }
}
