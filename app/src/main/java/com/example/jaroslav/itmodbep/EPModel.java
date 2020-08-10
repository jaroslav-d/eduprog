package com.example.jaroslav.itmodbep;

import java.util.Random;

public class EPModel {
    EPTable.Cols[] epTables;
    StudentTable.Cols[] studentTables;
    LecturerTable.Cols[] lecturerTables;
    SPTable.Cols[] spTables;

    public void init() {
        epTables = EPTable.init(2);
        studentTables = StudentTable.init(10);
        lecturerTables = LecturerTable.init(5);
        spTables = SPTable.init(4);
    }

    // Education program table model
    public static class EPTable {
        private static final String NAME = "EPs"; // Education programs
        static Cols cols;

        public static Cols[] init(int number) {
            Cols[] mCols = new Cols[number];
            for (int i = 0; i < number; i++) {
                cols = new Cols();
                cols.nameProgram = "prog #" + new Random().nextInt(10);
                cols.idEducationGroup = new Random().nextInt(100);
                cols.numberBP = new Random().nextInt(15);
                cols.numberCP = new Random().nextInt(15);
                cols.programManager = "manager #" + new Random().nextInt(5);
                cols.contactPerson = "+79" + new Random().nextInt(1000000000);
                cols.entranceTests = "русский язык " + "математика " + "предмет №" + (new Random().nextInt(4) + 1);
                mCols[i] = cols;
            }
            return mCols;
        }

        public static class Cols {
            public String nameProgram;
            public int idEducationGroup;
            public int numberBP; //number of budget places
            public int numberCP; //number of contract places
            public String programManager;
            public String contactPerson;
            public String entranceTests;
        }
    }

    public static class StudentTable {
        private static final String NAME = "Student";
        static Cols cols;

        public static Cols[] init(int number) {
            Cols[] mCols = new Cols[number];
            for (int i = 0; i < number; i++) {
                cols = new Cols();
                cols.nameStudent = "student #" + new Random().nextInt(10);
                cols.idEducationGroup = new Random().nextInt(100);
                cols.idScienceProject = new Random().nextInt(1000);
                cols.birthDay = "20.02.2000";
                mCols[i] = cols;
            }
            return mCols;
        }

        public static class Cols {
            public String nameStudent;
            public int idEducationGroup;
            public int idScienceProject;
            public String birthDay;
        }
    }

    public static class LecturerTable {
        private static final String NAME = "Lecturer";
        static Cols cols;

        public static Cols[] init(int number) {
            Cols[] mCols = new Cols[number];
            for (int i = 0; i < number; i++) {
                cols = new Cols();
                cols.nameLecturer = "lecturer #" + new Random().nextInt(10);
                cols.idScienceProject = new Random().nextInt(1000);
                cols.position = "PhD";
                mCols[i] = cols;
            }
            return mCols;
        }
        public static class Cols {
            public String nameLecturer;
            public int idScienceProject;
            public String position;
        }
    }

    public static class SPTable {
        private static final String NAME = "Science Project";
        static Cols cols;

        public static Cols[] init(int number) {
            Cols[] mCols = new Cols[number];
            for (int i = 0; i < number; i++) {
                cols = new Cols();
                cols.nameProject = "project #" + new Random().nextInt(10);
                cols.idScienceProject = new Random().nextInt(1000);
                cols.description = "20.02.2000";
                mCols[i] = cols;
            }
            return mCols;
        }

        public static class Cols {
            public String nameProject;
            public int idScienceProject;
            public String description;
        }
    }

}
