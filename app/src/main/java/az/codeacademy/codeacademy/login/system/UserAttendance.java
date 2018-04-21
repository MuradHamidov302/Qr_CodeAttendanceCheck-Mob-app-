package az.codeacademy.codeacademy.login.system;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Murad on 04/02/2018.
 */

public class UserAttendance implements Serializable {


        @SerializedName("Success")
        @Expose
        private Boolean success;
        @SerializedName("SuccessMessage")
        @Expose
        private SuccessMessage successMessage;
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

        public SuccessMessage getSuccessMessage() {
            return successMessage;
        }

        public void setSuccessMessage(SuccessMessage successMessage) {
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



