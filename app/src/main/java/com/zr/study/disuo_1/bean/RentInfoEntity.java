package com.zr.study.disuo_1.bean;

public class RentInfoEntity {

    String Phone;
    String PLNo;
    String RentNo;
    String Fee;
    String BookTime;
    String StartTime;
    String EndTime;
    String Renewal;

    public RentInfoEntity(String fee,String time,String plno){
        this.Fee=fee;
        this.StartTime=time;
        this.PLNo=plno;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPLNo() {
        return PLNo;
    }

    public void setPLNo(String PLNo) {
        this.PLNo = PLNo;
    }

    public String getRentNo() {
        return RentNo;
    }

    public void setRentNo(String rentNo) {
        RentNo = rentNo;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
    }

    public String getBookTime() {
        return BookTime;
    }

    public void setBookTime(String bookTime) {
        BookTime = bookTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getRenewal() {
        return Renewal;
    }

    public void setRenewal(String renewal) {
        Renewal = renewal;
    }

    @Override
    public String toString() {
        return "RentInfoEntity{" +
                "Phone='" + Phone + '\'' +
                ", PLNo='" + PLNo + '\'' +
                ", RentNo='" + RentNo + '\'' +
                ", Fee='" + Fee + '\'' +
                ", BookTime='" + BookTime + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", Renewal='" + Renewal + '\'' +
                '}';
    }
}
