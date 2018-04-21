package az.codeacademy.codeacademy.login.system;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Murad on 04/04/2018.
 */

public class UserProfile {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("SuccessMessage")
    @Expose
    private UserSuccessData successMessage;
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

    public UserSuccessData getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(UserSuccessData successMessage) {
        this.successMessage = successMessage;
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
