package az.codeacademy.codeacademy.qr.code.sendUrl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Murad on 04/01/2018.
 */

public class QRcodeResponse {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("SuccessMessage")
    @Expose
    private String successMessage;
    @SerializedName("SetNewPassword")
    @Expose
    private Boolean setNewPassword;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public Boolean getSetNewPassword() {
        return setNewPassword;
    }

    public void setSetNewPassword(Boolean setNewPassword) {
        this.setNewPassword = setNewPassword;
    }
}
