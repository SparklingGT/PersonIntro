package com.comli.dawnbreaksthrough.personalintro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LicenseFragment extends Fragment
{


    public LicenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_license, container, false);

        List<Integer> list = null;
        try {
            list = getList();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_license);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new LicenseAdapter(list));

        return view;
    }


    private class LicenseHolder extends RecyclerView.ViewHolder
    {
        TextView mTitle;
        TextView mLink;
        TextView mContent;

        public LicenseHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title_license);
            mLink = (TextView) itemView.findViewById(R.id.link_license);
            mContent = (TextView) itemView.findViewById(R.id.content_license);
        }

        public void showContent(int filenameId) {
            String container;
            String content = "";
            boolean addNewLine = false;
            InputStream inputStream = getActivity().getResources().openRawResource(filenameId);

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((container = bufferedReader.readLine()) != null) {
                    if (addNewLine) {
                        content += "\n";
                    }
                    content += container;
                    content += "\n";
                    addNewLine = true;
                }
            } catch (IOException ioe) {
                Log.e("Why?", "Error loading file!", ioe);
            }
            mContent.setText(content);
        }
    }

    private class LicenseAdapter extends RecyclerView.Adapter<LicenseHolder>
    {
        List<Integer> list;

        public LicenseAdapter(List<Integer> list) {
            this.list = list;
        }

        @Override
        public LicenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new LicenseHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_license, parent, false));
        }

        @Override
        public void onBindViewHolder(LicenseHolder holder, int position) {
            holder.showContent(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }

    private List<Integer> getList() throws IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();

        for (Field field : fields) {

            if (!field.isSynthetic() && !field.getName().equals("serialVersionUID")) {
                list.add(field.getInt(field));
            }
        }
        return list;
    }

}

