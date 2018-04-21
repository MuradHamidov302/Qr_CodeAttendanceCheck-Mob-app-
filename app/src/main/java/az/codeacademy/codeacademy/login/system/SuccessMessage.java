package az.codeacademy.codeacademy.login.system;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuccessMessage {

    @SerializedName("AttendanceTable")
    @Expose
    private List<AttendanceTable> attendanceTable = null;
    @SerializedName("AttendanceInfo")
    @Expose
    private String attendanceInfo;

    public List<AttendanceTable> getAttendanceTable() {
        return attendanceTable;
    }

    public void setAttendanceTable(List<AttendanceTable> attendanceTable) {
        this.attendanceTable = attendanceTable;
    }

    public String getAttendanceInfo() {
        return attendanceInfo;
    }

    public void setAttendanceInfo(String attendanceInfo) {
        this.attendanceInfo = attendanceInfo;
    }

}
