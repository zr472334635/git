package com.zr.study.disuo_1.bean;

public class LockPointEntity {
    String PLNo;
    String PLgroupNo;
    String DamagedCondition;
    String Takenup;
    String ExpiringDate;
    String Profit;
    String Booked;
    String Longitude;
    String Latitude;
    String Position;

    public String getPLNo() {
        return PLNo;
    }

    public void setPLNo(String PLNo) {
        this.PLNo = PLNo;
    }

    public String getPLgroupNo() {
        return PLgroupNo;
    }

    public void setPLgroupNo(String PLgroupNo) {
        this.PLgroupNo = PLgroupNo;
    }

    public String getDamagedCondition() {
        return DamagedCondition;
    }

    public void setDamagedCondition(String damagedCondition) {
        DamagedCondition = damagedCondition;
    }

    public String getTakenup() {
        return Takenup;
    }

    public void setTakenup(String takenup) {
        Takenup = takenup;
    }

    public String getExpiringDate() {
        return ExpiringDate;
    }

    public void setExpiringDate(String expiringDate) {
        ExpiringDate = expiringDate;
    }

    public String getProfit() {
        return Profit;
    }

    public void setProfit(String profit) {
        Profit = profit;
    }

    public String getBooked() {
        return Booked;
    }

    public void setBooked(String booked) {
        Booked = booked;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    @Override
    public String toString() {
        return "LockPointEntity{" +
                "PLNo='" + PLNo + '\'' +
                ", PLgroupNo='" + PLgroupNo + '\'' +
                ", DamagedCondition='" + DamagedCondition + '\'' +
                ", Takenup='" + Takenup + '\'' +
                ", ExpiringDate='" + ExpiringDate + '\'' +
                ", Profit='" + Profit + '\'' +
                ", Booked='" + Booked + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Position='" + Position + '\'' +
                '}';
    }
}
