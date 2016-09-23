package com.comli.dawnbreaksthrough.personalintro;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    // split MIT license into only 1 file, increment by 1.
    private static final int BOOTSTRAP = 0;
    private static final int MATERIAL_DIALOG = 1;

    // split Apache license into 11 files, increment by 11.
    private static final int ICON = 2;
    private static final int CIRCLE = 13;
    private static final int MATERIAL_DRAWER = 24;
    private static final int PHOTO = 35;
    private static final int ROUNDED = 46;

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
            Log.e("sparklingGT", "Error accessing the files", e);
        }


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_license);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new LicenseAdapter(list));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Snackbar.make(getView(), getString(R.string.suggestion_license), 3000).show(); // 3 sec
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

        public void showContent(int filenameId, int position) {
            StringBuilder title = new StringBuilder();
            StringBuilder linkText = new StringBuilder();
            getTitleLinkText(title, linkText, position);

            mTitle.setText(title);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                mLink.setText(Html.fromHtml(linkText.toString(), 0));
            } else {
                mLink.setText(Html.fromHtml(linkText.toString()));
            }
            // For some reason, if you neither setText in the else block (in other words, the Text is null),
            // nor make their height 0 (height = 0 is just to hide the things mentioned below),
            // you will see different title and linkText CYCLE through every paragraph!!
            // which is not something I expected, though.

            boolean decision = (title.length() > 0 && linkText.length() > 0);
            showOrHideTitlePlusLink(decision);

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
                    addNewLine = true;
                }
            } catch (IOException ioe) {
                Log.e("Why?", "Error loading file!", ioe);
            }
            mContent.setText(content);
        }

        private void getTitleLinkText(StringBuilder title, StringBuilder linkText, int position) {
            switch (position) {
                case BOOTSTRAP:
                    title.append(getString(R.string.title_android_bootstrap));
//                    linkText.append(Html.fromHtml(getString(R.string.link_android_bootstrap)));
                    linkText.append(getString(R.string.link_android_bootstrap));
                    break;
                case MATERIAL_DIALOG:
                    title.append(getString(R.string.title_material_dialog));
                    linkText.append(getString(R.string.link_material_dialog));
                    break;
                case ICON:
                    title.append(getString(R.string.title_android_iconics));
                    linkText.append(getString(R.string.link_android_iconics));
                    break;
                case MATERIAL_DRAWER:
                    title.append(getString(R.string.title_material_drawer));
                    linkText.append(getString(R.string.link_material_drawer));
                    break;
                case PHOTO:
                    title.append(getString(R.string.title_photo_view));
                    linkText.append(getString(R.string.link_photo_view));
                    break;
                case CIRCLE:
                    title.append(getString(R.string.title_circle_image_view));
                    linkText.append(getString(R.string.link_circle_image_view));
                    break;
                case ROUNDED:
                    title.append(getString(R.string.title_rounded_image_view));
                    linkText.append(getString(R.string.link_rounded_image_view));
                    break;
            }
        }

        private void showOrHideTitlePlusLink(boolean decision) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (decision) {
                int e = PictureUtils.dipToPixel(8, getActivity());
                int f = PictureUtils.dipToPixel(4, getActivity());

                lp.setMargins(e, e, e, e);
                mTitle.setLayoutParams(lp);
                lp.setMargins(f, f, f, f);
                mLink.setLayoutParams(lp);

                mLink.setMovementMethod(LinkMovementMethod.getInstance());
                mLink.setLinkTextColor(ContextCompat.getColor(getActivity(), R.color.link_library_license));
            } else {
                lp.setMargins(0, 0, 0, 0);
                mTitle.setLayoutParams(lp);
                mLink.setLayoutParams(lp);
                mTitle.getLayoutParams().height = 0;
                mLink.getLayoutParams().height = 0;
            }

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
            holder.showContent(list.get(position), position);
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

