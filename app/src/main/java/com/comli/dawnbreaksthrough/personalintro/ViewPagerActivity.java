package com.comli.dawnbreaksthrough.personalintro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class ViewPagerActivity extends AppCompatActivity
{
    private static final String STRING_EXTRA_PERSON_ID = "com.comli.dawnbreaksthrough.personalintro.personalID";

    public static Intent newIntent(UUID personId, Context context) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        intent.putExtra(STRING_EXTRA_PERSON_ID, personId);
        return intent;
    }

    ViewPager mViewPager;
    List<Person> mPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final UUID personId = (UUID) getIntent().getSerializableExtra(STRING_EXTRA_PERSON_ID);
        mPersonList = PersonLab.get(this).getPersonList();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager)
        {
            @Override
            public Fragment getItem(int position) {
                Person person = mPersonList.get(position); //// TODO: 9/12/2016  not quite get this.
                return PersonDetailFragment.newInstance(person.getUUID());
            }

            @Override
            public int getCount() {
                return mPersonList.size();
            }
        });

        for (int i = 0; i < mPersonList.size(); i++) {
            Person person = mPersonList.get(i);
            if (person.getUUID().equals(personId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
