package com.example.jaroslav.itmodbep;

// Education program database schema
public class EPDatabaseSchema {
    // Education program table
    public static final class EPTable {
        public static final String NAME = "EPs"; // Education programs

        public static final class Cols {
            public static final String NAME_PROGRAM = "name_program";
            public static final String ID_EDUCATION_GROUP = "id_education_group";
            public static final String NUMBER_BP = "number_bp"; //number of budget places
            public static final String NUMBER_CP = "number_cp"; //number of contract places
            public static final String PROGRAM_MANAGER = "program_manager";
            public static final String CONTACT_PERSON = "contact_person";
            public static final String ENTRANCE_TESTS = "entrance_tests";
        }
    }

    public static final class StudentTable {
        public static final String NAME = "Student";

        public static final class Cols {
            public static final String NAME_STUDENT = "name_student";
            public static final String ID_EDUCATION_GROUP = "id_education_group";
            public static final String ID_SCIENCE_PROJECT = "id_science_project";
            public static final String BIRTH_DAY = "birth_day";
        }
    }

    public static final class LecturerTable {
        public static final String NAME = "Lecturer";

        public static final class Cols {
            public static final String NAME_LECTURER = "name_lecturer";
            public static final String ID_SCIENCE_PROJECT = "id_science_project";
            public static final String POSITION = "position";
        }
    }

    public static final class SPTable {
        public static final String NAME = "ScienceProject";

        public static final class Cols {
            public static final String NAME_PROJECT = "name_science_project";
            public static final String ID_SCIENCE_PROJECT = "id_science_project";
            public static final String DESCRIPTION = "description";
        }
    }

}
