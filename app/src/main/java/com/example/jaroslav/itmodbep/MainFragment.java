package com.example.jaroslav.itmodbep;

import android.content.ContentValues;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jaroslav.itmodbep.EPDatabaseSchema.EPTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.StudentTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.LecturerTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.SPTable;

public class MainFragment extends Fragment {

    protected Button educationProgram;
    protected Button student;
    protected Button lecturer;
    protected Button scienceProject;
    private Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.select_fragment, container, false);

        educationProgram = v.findViewById(R.id.button_education_program);
        student = v.findViewById(R.id.button_student);
        lecturer = v.findViewById(R.id.button_lecturer);
        scienceProject = v.findViewById(R.id.button_science_project);
        add = v.findViewById(R.id.add_data);

        educationProgram.setOnClickListener(onClickListener);
        student.setOnClickListener(onClickListener);
        lecturer.setOnClickListener(onClickListener);
        scienceProject.setOnClickListener(onClickListener);
        add.setOnClickListener(clickListenerAdd);

        return v;
    }

    View.OnClickListener clickListenerAdd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // add database test
            EPModel model = new EPModel();
            model.init();
            SQLiteDatabase mDataBase = new EPBaseHelper(getActivity()).getWritableDatabase();
            for (int i = 0; i < model.epTables.length; i++) {
                mDataBase.insert(EPTable.NAME, null, getContentValues(model, EPTable.NAME, i));
            }
            for (int i = 0; i < model.studentTables.length; i++) {
                mDataBase.insert(StudentTable.NAME, null, getContentValues(model, StudentTable.NAME, i));
            }
            for (int i = 0; i < model.lecturerTables.length; i++) {
                mDataBase.insert(LecturerTable.NAME, null, getContentValues(model, LecturerTable.NAME, i));
            }
            for (int i = 0; i < model.spTables.length; i++) {
                mDataBase.insert(SPTable.NAME, null, getContentValues(model, SPTable.NAME, i));
            }
        }
    };

    private static ContentValues getContentValues(EPModel model, String nameTable, int row) {
        ContentValues values = new ContentValues();
        switch (nameTable) {
            case EPTable.NAME:
                values.put(EPTable.Cols.NAME_PROGRAM, model.epTables[row].nameProgram);
                values.put(EPTable.Cols.NUMBER_BP, model.epTables[row].numberBP);
                values.put(EPTable.Cols.NUMBER_CP, model.epTables[row].numberCP);
                values.put(EPTable.Cols.ID_EDUCATION_GROUP, model.epTables[row].idEducationGroup);
                values.put(EPTable.Cols.PROGRAM_MANAGER, model.epTables[row].programManager);
                values.put(EPTable.Cols.CONTACT_PERSON, model.epTables[row].contactPerson);
                values.put(EPTable.Cols.ENTRANCE_TESTS, model.epTables[row].entranceTests);
                break;
            case StudentTable.NAME:
                values.put(StudentTable.Cols.NAME_STUDENT, model.studentTables[row].nameStudent);
                values.put(StudentTable.Cols.ID_EDUCATION_GROUP, model.studentTables[row].idEducationGroup);
                values.put(StudentTable.Cols.ID_SCIENCE_PROJECT, model.studentTables[row].idScienceProject);
                values.put(StudentTable.Cols.BIRTH_DAY, model.studentTables[row].birthDay);
                break;
            case LecturerTable.NAME:
                values.put(LecturerTable.Cols.NAME_LECTURER, model.lecturerTables[row].nameLecturer);
                values.put(LecturerTable.Cols.ID_SCIENCE_PROJECT, model.lecturerTables[row].idScienceProject);
                values.put(LecturerTable.Cols.POSITION, model.lecturerTables[row].position);
                break;
            case SPTable.NAME:
                values.put(SPTable.Cols.NAME_PROJECT, model.spTables[row].nameProject);
                values.put(SPTable.Cols.ID_SCIENCE_PROJECT, model.spTables[row].idScienceProject);
                values.put(SPTable.Cols.DESCRIPTION, model.spTables[row].description);
                break;
        }
        return values;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase mDataBase = new EPBaseHelper(getActivity()).getWritableDatabase();
            Cursor cursor;
            switch (v.getId()) {
                case R.id.button_education_program:
                    cursor = createCursor(mDataBase, EPDatabaseSchema.EPTable.NAME);
                    settingFragment(cursor, R.array.EPsFields, 7);
                    break;
                case R.id.button_student:
                    cursor = createCursor(mDataBase, EPDatabaseSchema.StudentTable.NAME);
                    settingFragment(cursor, R.array.StudentFields, 4);
                    break;
                case R.id.button_lecturer:
                    cursor = createCursor(mDataBase, EPDatabaseSchema.LecturerTable.NAME);
                    settingFragment(cursor, R.array.LecturerFields, 3);
                    break;
                case R.id.button_science_project:
                    cursor = createCursor(mDataBase, EPDatabaseSchema.SPTable.NAME);
                    settingFragment(cursor, R.array.ScienceProjectFields, 3);
                    break;
            }
        }
    };

    private Cursor createCursor(SQLiteDatabase db, String nameTable) {
        return db.query(nameTable,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    private void settingFragment(Cursor cursor, int listFieldsTable, int numberLinesInItem) {
        ListFragment newFragment = new ListFragment();
        TypedArray typedArray = getResources().obtainTypedArray(R.array.items);
        int[] number = new int[numberLinesInItem];
        for (int i = 0; i < numberLinesInItem; i++) {
            number[i] = typedArray.getResourceId(i,0);
        }
        typedArray.recycle();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.item,
                cursor,
                getResources().getStringArray(listFieldsTable),
                number,
                0);
        newFragment.setListAdapter(adapter);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}