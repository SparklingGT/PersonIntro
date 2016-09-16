package com.comli.dawnbreaksthrough.personalintro;

import com.comli.dawnbreaksthrough.personalintro.Elements.Age;
import com.comli.dawnbreaksthrough.personalintro.Elements.Thumbnail;

import java.util.Date;
import java.util.UUID;

/**
 * Created by SparklingGT on 9/12/2016.
 */
public class Person
{
    private String mName;
    private String mIntro;
    private String mNote;
    private Date mDate;
    private UUID mUUID;
    private int mGender;

    Person(UUID uuid) {
        mUUID = uuid;
    }

    Person() {
        this(UUID.randomUUID());
        mDate = new Date(System.currentTimeMillis() - Age.TWO_HUNDRED_YEARS);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getIntro() {
        return mIntro;
    }

    public void setIntro(String intro) {
        mIntro = intro;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public String getThumbnailFilename(int size) {
        if (size == Thumbnail.Size.SMALL) {
            return "THUMB" + getUUID() + ".jpg";
        } else {
            return "L_THUMB" + getUUID() + ".jpg";
        }

    }

    public String getPhotoFilename() {
        return getUUID() + ".jpg";
    }
}
