package az.codeacademy.codeacademy.login.system;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Murad on 03/31/2018.
 */

public class UserSuccessData implements Serializable {


        @SerializedName("Id")
        @Expose
        private int id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Surname")
        @Expose
        private String surname;
        @SerializedName("FatherName")
        @Expose
        private String fatherName;
        @SerializedName("Phone")
        @Expose
        private String phone;
        @SerializedName("Gender")
        @Expose
        private String gender;
        @SerializedName("Grup")
        @Expose
        private String grup;
        @SerializedName("Group_Schedule")
        @Expose
        private String groupSchedule;
        @SerializedName("LessonTime")
        @Expose
        private String lessonTime;
        @SerializedName("LessonBeginTime")
        @Expose
        private String lessonBeginTime;
        @SerializedName("LessonEndTime")
        @Expose
        private String lessonEndTime;
        @SerializedName("AttendanceRatio")
        @Expose
        private String attendanceRatio;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getGrup() {
            return grup;
        }

        public void setGrup(String grup) {
            this.grup = grup;
        }

        public String getGroupSchedule() {
            return groupSchedule;
        }

        public void setGroupSchedule(String groupSchedule) {
            this.groupSchedule = groupSchedule;
        }

        public String getLessonTime() {
            return lessonTime;
        }

        public void setLessonTime(String lessonTime) {
            this.lessonTime = lessonTime;
        }

        public String getLessonBeginTime() {
            return lessonBeginTime;
        }

        public void setLessonBeginTime(String lessonBeginTime) {
            this.lessonBeginTime = lessonBeginTime;
        }

        public String getLessonEndTime() {
            return lessonEndTime;
        }

        public void setLessonEndTime(String lessonEndTime) {
            this.lessonEndTime = lessonEndTime;
        }

        public String getAttendanceRatio() {
            return attendanceRatio;
        }

        public void setAttendanceRatio(String attendanceRatio) {
            this.attendanceRatio = attendanceRatio;
        }

    }
