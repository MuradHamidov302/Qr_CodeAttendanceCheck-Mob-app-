package az.codeacademy.codeacademy.login.system;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Murad on 03/31/2018.
 */

public class NewPassResponse {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("StudentId")
    @Expose
    private Integer studentId;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("SetNewPassword")
    @Expose
    private Boolean setNewPassword;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSetNewPassword() {
        return setNewPassword;
    }

    public void setSetNewPassword(Boolean setNewPassword) {
        this.setNewPassword = setNewPassword;
    }


}
