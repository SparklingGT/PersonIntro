package com.comli.dawnbreaksthrough.personalintro;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.comli.dawnbreaksthrough.personalintro.Elements.Thumbnail;

import java.io.File;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonListFragment extends Fragment
{
    private RecyclerView mRecyclerView;
    private AdapterPersonList mAdapter;

    public PersonListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("sparkling", "ListFragment onCreateView called");
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.person_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume() {

        List<Person> personList = PersonLab.get(getActivity()).getPersonList();

        if (mAdapter == null) {
            mAdapter = new AdapterPersonList(personList);
        } else {
            mAdapter.updatePersonList(personList);
            mAdapter.notifyDataSetChanged();
        }
        mRecyclerView.setAdapter(mAdapter);

        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_person_list_fragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_person:
                Person person = new Person();
                PersonLab.get(getActivity()).addPerson(person);
                Intent intent = ViewPagerActivity.newIntent(person.getUUID(), getActivity());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ViewHolderPerson extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener
    {
        Person mPerson;
        TextView name;
        ImageView profile;

        public ViewHolderPerson(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_view_person_name_card);
            profile = (ImageView) itemView.findViewById(R.id.circle_image_profile);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bindPerson(Person person) {
            File profileCache = PersonLab.get(getActivity()).getThumbnailFile(person, Thumbnail.Size.SMALL);
            if (profileCache.exists()) {
                int profileSizeInDip = 80;
                //// TODO: 9/16/2016 I should make a preference file, and access that size in one place
                //// TODO: 9/16/2016  Two places that use the same setting, here and the pic inside 
                int profileSizeInPixel = PictureUtils.dipToPixel(profileSizeInDip, getActivity());
                profile.getLayoutParams().width = profileSizeInPixel;
                profile.getLayoutParams().height = profileSizeInPixel;
                profile.setImageBitmap(BitmapFactory.decodeFile(profileCache.getPath()));
            } else {
                profile.setImageDrawable(null);
            }
            name.setText(person.getName());
            mPerson = person;
        }

        @Override
        public void onClick(View view) {
            Intent intent = ViewPagerActivity.newIntent(mPerson.getUUID(), getActivity());
            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            //// TODO: 9/13/2016 mark fav checkbox 
            return false;
        }
    }

    private class AdapterPersonList extends RecyclerView.Adapter<ViewHolderPerson>
    {
        List<Person> mPersonList;

        public AdapterPersonList(List<Person> personList) {
            mPersonList = personList;
        }

        @Override
        public ViewHolderPerson onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.fragment_person_cardview, parent, false);
            return new ViewHolderPerson(view);
        }

        @Override
        public void onBindViewHolder(ViewHolderPerson holder, int position) {
            Person person = mPersonList.get(position);
            holder.bindPerson(person);
        }

        @Override
        public int getItemCount() {
            return mPersonList.size();
        }

        public void updatePersonList(List<Person> personList) {
            mPersonList = personList;
        }
    }
}
