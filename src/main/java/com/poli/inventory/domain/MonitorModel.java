package com.poli.inventory.domain;

public class MonitorModel {
    private User userMonitor;
    private User UserModel;

    public User getUserMonitor() {
        return userMonitor;
    }

    public void setUserMonitor(User userMonitor) {
        this.userMonitor = userMonitor;
    }

    public User getUserModel() {
        return UserModel;
    }

    public void setUserModel(User userModel) {
        UserModel = userModel;
    }
}
