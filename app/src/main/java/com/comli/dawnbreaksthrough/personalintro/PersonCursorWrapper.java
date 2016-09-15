package com.comli.dawnbreaksthrough.personalintro;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.comli.dawnbreaksthrough.personalintro.Elements.Databases;

import java.util.Date;
import java.util.UUID;

/**
 * Created by SparklingGT on 9/14/2016.
 */
public class PersonCursorWrapper extends CursorWrapper
{
    public PersonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Person getPerson() {
        String uuidString = getString(getColumnIndex(Databases.PersonTable.Cols.UUID));
        String name = getString(getColumnIndex(Databases.PersonTable.Cols.NAME));
        int gender = getInt(getColumnIndex(Databases.PersonTable.Cols.GENDER));
        long birth = getLong(getColumnIndex(Databases.PersonTable.Cols.BIRTH));
        String intro = getString(getColumnIndex(Databases.PersonTable.Cols.INTRO));

        Person person = new Person(UUID.fromString(uuidString));
        person.setName(name);
        person.setGender(gender);
        person.setDate(new Date(birth));
        person.setIntro(intro);

        return person;
    }

}
