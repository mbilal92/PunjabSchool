package com.example.bilal.school;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Bilal on 4/23/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "school";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL__SCHOOL_CREATE_ENTRIES =
            "CREATE TABLE school_entry ( id INTEGER PRIMARY KEY, name TEXT, server_id INTEGER)";

    private static final String SQL_Environment_Reporting_CREATE_ENTRIES =
            "CREATE TABLE environment_reporting_entry ( id INTEGER PRIMARY KEY, place_id INTEGER, server_id INTEGER, place_server_id INTEGER, school_community_reporting INTEGER," +
                    "boundry_wall INTEGER, electricity INTEGER, playground INTEGER, clean_classroom INTEGER, class_floor INTEGER," +
                    "janitorial_staff INTEGER, ventilated_classroos INTEGER, tables_chair INTEGER, functional_fans INTEGER," +
                    "clean_water INTEGER, adequate_supply INTEGER, functional_toilets INTEGER, sufficient_number_of_toilets INTEGER," +
                    "running_water INTEGER, drainage_system INTEGER, drainage_system_functional INTEGER, waste_management_plan INTEGER, waste_manag_frequency INTEGER," +
                    "other_comments TEXT, dateadded TEXT)";
    public long add_environment_reporting(int place_id, int server_id, int place_server_id,
                                           int boundry_wall, int electricity, int playground, int clean_classroom, int class_floor,
                                           int janitorial_staff, int ventilated_classroos, int tables_chair, int functional_fans, int clean_water,
                                           int adequate_supply, int functional_toilets, int sufficient_number_of_toilets, int running_water,
                                           int drainage_system, int drainage_system_functional, int waste_management_plan,
                                           int waste_manag_frequency, String other_comments) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("school_community_reporting", 1);
        values.put("place_id", place_id);
        values.put("server_id", server_id);
        values.put("place_server_id", place_server_id);
        values.put("boundry_wall", boundry_wall);
        values.put("electricity", electricity);
        values.put("playground", playground);
        values.put("clean_classroom", clean_classroom);
        values.put("class_floor", class_floor);
        values.put("janitorial_staff", janitorial_staff);
        values.put("ventilated_classroos", ventilated_classroos);
        values.put("tables_chair", tables_chair);
        values.put("functional_fans", functional_fans);
        values.put("clean_water", clean_water);
        values.put("adequate_supply", adequate_supply);
        values.put("functional_toilets", functional_toilets);
        values.put("sufficient_number_of_toilets", sufficient_number_of_toilets);
        values.put("running_water", running_water);
        values.put("drainage_system", drainage_system);
        values.put("drainage_system_functional", drainage_system_functional);
        values.put("waste_management_plan", waste_management_plan);
        values.put("waste_manag_frequency", waste_manag_frequency);
        values.put("other_comments", other_comments);
        values.put("dateadded", DateFormat.getDateTimeInstance().format(new Date()));

        long result = db.insert("environment_reporting_entry", null, values);
        return result;
    }

    private static final String SQL_Child_Health_Reporting_CREATE_ENTRIES =
            "CREATE TABLE child_health_reporting_entry ( id INTEGER PRIMARY KEY, child_id INTEGER,school_community_reporting INTEGER, bmi INTEGER," +
                    "anemia INTEGER, other_physical_deformities INTEGER, mental_disability INTEGER, hearing_problem INTEGER," +
                    "other_ear_problem INTEGER, vision INTEGER, other_eye_problem INTEGER, dental_problem INTEGER, acute_infection INTEGER, skin_problem INTEGER," +
                    "evidence_of_truma INTEGER, any_other_problem_identified TEXT, student_refered INTEGER, follow_up_date TEXT, dateadded TEXT, height TEXT, weight TEXT, number_of_sibling INTEGER)";

    private static final String SQL_Child_CREATE_ENTRIES =
            "CREATE TABLE child_entry ( id INTEGER PRIMARY KEY, school_community_reporting INTEGER, place_id INTEGER," +
                    " server_id INTEGER, place_server_id INTEGER," +
                    "grade INTEGER, name TEXT, parent_name TEXT, father_name TEXT, dob TEXT," +
                    "male INTEGER, dateadded TEXT)"; //, height TEXT, weight TEXT, dateadded TEXT)";

    public long add_child(int school_community_reporting,int place_id, int server_id, int place_server_id,
                          int grade, String name, String parent_name, String father_name, String dob, int male) {
//                          String height, String weight) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("school_community_reporting", school_community_reporting);
        values.put("place_id", place_id);
        values.put("server_id", server_id);
        values.put("place_server_id", place_server_id);
        values.put("grade", grade);
        values.put("name", name);
        values.put("parent_name", parent_name);
        values.put("father_name", father_name);
        values.put("dob", dob);
        values.put("male", male);
//        values.put("height", height);
//        values.put("weight", weight);
        values.put("dateadded", DateFormat.getDateTimeInstance().format(new Date()));
        long result = db.insert("child_entry", null, values);
        return result;
    }

    public long add_child_health_reporting(long child_id,
                                           int school_community_reporting,int place_id, int server_id, int place_server_id, int number_of_sibling,
                                           String height, String weight, int bmi, int anemia, int jaundice, int facial_pffiness, int edema, int other_physical_deformities, int mental_disability,
                                           int hearing_problem, int other_ear_problem, int vision, int other_eye_problem, int dental_problem, int acute_infection,
                                           int skin_problem, int evidence_of_truma, int hoImmunization, int hoAllergy, int Hospitilaztion,
                                           String any_other_problem_identified, int student_refered, String follow_up_date) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("child_id", child_id);
        values.put("school_community_reporting", school_community_reporting);
        values.put("bmi", bmi);
        values.put("anemia", anemia);
        values.put("other_physical_deformities", other_physical_deformities);
        values.put("mental_disability", mental_disability);
        values.put("hearing_problem", hearing_problem);
        values.put("other_ear_problem", other_ear_problem);
        values.put("vision", vision);
        values.put("other_eye_problem", other_eye_problem);
        values.put("acute_infection", acute_infection);
        values.put("skin_problem", skin_problem);
        values.put("dental_problem", dental_problem);
        values.put("evidence_of_truma", evidence_of_truma);
        values.put("any_other_problem_identified", any_other_problem_identified);
        values.put("student_refered", student_refered);
        values.put("number_of_sibling", number_of_sibling);
        values.put("height", height);
        values.put("weight", weight);
        values.put("follow_up_date", follow_up_date);
        values.put("dateadded", DateFormat.getDateTimeInstance().format(new Date()));

        long result =  db.insert("child_health_reporting_entry", null, values);
        return result;
    }

    private static final String SQL_Health_Education_Session_Reporting_CREATE_ENTRIES =
            "CREATE TABLE health_education_session_reporting_entry ( id INTEGER PRIMARY KEY, school_community_reporting INTEGER," +
                    " place_id INTEGER, server_id INTEGER, place_server_id INTEGER," +
                    "number_of_male INTEGER, number_of_female INTEGER, numberofParticipant INTEGER, topic_discussed TEXT, advisor TEXT,st TEXT,et TEXT, date TEXT, dateadded TEXT)";

    public long add_heath_session(int school_community_reporting,int place_id, int server_id, int place_server_id, int number_of_male,
                                  int number_of_female,int numberofParticipant, String topic_discussed, String advisor, String date, String st, String et) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("school_community_reporting", school_community_reporting);
        values.put("place_id", place_id);
        values.put("server_id", server_id);
        values.put("place_server_id", place_server_id);
        values.put("number_of_male", number_of_male);
        values.put("number_of_female", number_of_female);
        values.put("topic_discussed", topic_discussed);
        values.put("advisor", advisor);
        values.put("st", st);
        values.put("et", et);
        values.put("date", date);
        values.put("numberofParticipant", numberofParticipant);
        values.put("dateadded", DateFormat.getDateTimeInstance().format(new Date()));
        long newRowId = db.insert("health_education_session_reporting_entry", null, values);
        return newRowId;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL__SCHOOL_CREATE_ENTRIES);
        db.execSQL(SQL_Child_Health_Reporting_CREATE_ENTRIES);
        db.execSQL(SQL_Environment_Reporting_CREATE_ENTRIES);
        db.execSQL(SQL_Health_Education_Session_Reporting_CREATE_ENTRIES);
        db .execSQL(SQL_Child_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table health_education_session_reporting_entry");
        db.execSQL("drop table child_health_reporting_entry");
        db.execSQL("drop table child_entry");
        db.execSQL("drop table environment_reporting_entry");
        db.execSQL("drop table school_entry");
        db.execSQL(SQL__SCHOOL_CREATE_ENTRIES);
        db.execSQL(SQL_Child_Health_Reporting_CREATE_ENTRIES);
        db.execSQL(SQL_Environment_Reporting_CREATE_ENTRIES);
        db.execSQL(SQL_Health_Education_Session_Reporting_CREATE_ENTRIES);
        db .execSQL(SQL_Child_CREATE_ENTRIES);
    }
}
