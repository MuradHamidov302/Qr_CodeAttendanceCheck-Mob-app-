package az.codeacademy.codeacademy.login.system;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Murad on 03/30/2018.
 */

public class UserResponse implements Serializable {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("StudentId")
    @Expose
    private String studentId;
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

    public String  getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String  errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSetNewPassword() {
        return setNewPassword;
    }

    public void setSetNewPassword(Boolean setNewPassword) {
        this.setNewPassword = setNewPassword;
    }

}
