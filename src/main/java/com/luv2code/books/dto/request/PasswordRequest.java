package com.luv2code.books.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordRequest {


    @NotEmpty(message = "Password is mandatory")
    @Size(min = 5 , max = 30,message = "password must be at least 5 characters long")
    private String oldPassword;

    @NotEmpty(message = "New Password is mandatory")
    @Size(min = 5 , max = 30,message = "password must be at least 5 characters long")
    private String newPassword;

    @NotEmpty(message = "Confirmed Password is mandatory")
    @Size(min = 5 , max = 30,message = "password must be at least 5 characters long")
    private String newPasswordConfirm;

    public PasswordRequest(String oldPassword, String newPassword, String newPasswordConfirm) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}
