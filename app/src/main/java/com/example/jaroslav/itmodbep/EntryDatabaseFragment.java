package com.example.jaroslav.itmodbep;

import android.content.ContentValues;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jaroslav.itmodbep.EPDatabaseSchema.EPTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.StudentTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.LecturerTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.SPTable;

public class EntryDatabaseFragment extends Fragment {

    private String nameFragment;
    private View view;
    protected Button okButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        nameFragment = getActivity().getIntent().getStringExtra("fragment");

        if (nameFragment.equals(EPTable.NAME)) {
            view = inflater.inflate(R.layout.fragment_add_ep, container, false);
        } else if (nameFragment.equals(StudentTable.NAME)) {
            view = inflater.inflate(R.layout.fragment_add_student, container, false);
        } else if (nameFragment.equals(LecturerTable.NAME)) {
            view = inflater.inflate(R.layout.fragment_add_lecturer, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_add_science_project, container, false);
        }
        createButtonOk(view);

        return view;
    }

    private void createButtonOk(View view) {
        okButton = view.findViewById(R.id.ok);
        okButton.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TypedArray typedArray;
            if (nameFragment.equals(EPTable.NAME)) {
                typedArray = getResources().obtainTypedArray(R.array.EPs);
            } else if (nameFragment.equals(StudentTable.NAME)) {
                typedArray = getResources().obtainTypedArray(R.array.Student);
            } else if (nameFragment.equals(LecturerTable.NAME)) {
                typedArray = getResources().obtainTypedArray(R.array.Lecturer);
            } else {
                typedArray = getResources().obtainTypedArray(R.array.ScienceProject);
            }
            TextView[] views = new TextView[typedArray.length()];
            for (int i = 0; i < typedArray.length(); i++) {
                views[i] = view.findViewById(typedArray.getResourceId(i,0));
            }
            typedArray.recycle();
            String[] strings = new String[views.length];
            for (int i = 0; i < views.length; i++) {
                strings[i] = views[i].getText().toString();
            }
            SQLiteDatabase mDataBase = new EPBaseHelper(getActivity()).getWritableDatabase();
            mDataBase.insert(nameFragment, null, getContentValues(strings, nameFragment));
            getActivity().finish();
        }
    };

    private static ContentValues getContentValues(String[] model, String nameTable) {
        ContentValues values = new ContentValues();
        switch (nameTable) {
            case EPTable.NAME:
                values.put(EPTable.Cols.NAME_PROGRAM, model[0]);
                values.put(EPTable.Cols.NUMBER_BP, model[1]);
                values.put(EPTable.Cols.NUMBER_CP, model[2]);
                values.put(EPTable.Cols.ID_EDUCATION_GROUP, model[3]);
                values.put(EPTable.Cols.PROGRAM_MANAGER, model[4]);
                values.put(EPTable.Cols.CONTACT_PERSON, model[5]);
                values.put(EPTable.Cols.ENTRANCE_TESTS, model[6]);
                break;
            case StudentTable.NAME:
                values.put(StudentTable.Cols.NAME_STUDENT, model[0]);
                values.put(StudentTable.Cols.ID_EDUCATION_GROUP, model[1]);
                values.put(StudentTable.Cols.ID_SCIENCE_PROJECT, model[2]);
                values.put(StudentTable.Cols.BIRTH_DAY, model[3]);
                break;
            case LecturerTable.NAME:
                values.put(LecturerTable.Cols.NAME_LECTURER, model[0]);
                values.put(LecturerTable.Cols.ID_SCIENCE_PROJECT, model[1]);
                values.put(LecturerTable.Cols.POSITION, model[2]);
                break;
            case SPTable.NAME:
                values.put(SPTable.Cols.NAME_PROJECT, model[0]);
                values.put(SPTable.Cols.ID_SCIENCE_PROJECT, model[1]);
                values.put(SPTable.Cols.DESCRIPTION, model[2]);
                break;
        }
        return values;
    }
}
