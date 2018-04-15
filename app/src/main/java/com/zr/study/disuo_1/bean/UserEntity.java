package com.zr.study.disuo_1.bean;

/**
 * Created by duray on 17-10-17.
 */

public class UserEntity {

    String Phone;
    String Password;
    String RepulationValue;
    String Name;
    String NickName;
    String Sex;
    String Birthday;
    String Identity;
    String IdentityCard;
    String Wechat;
    String QQ;
    String CarNumber;
    String Balance;
    String CashPledge;
    String UserCondition;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRepulationValue() {
        return RepulationValue;
    }

    public void setRepulationValue(String repulationValue) {
        RepulationValue = repulationValue;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String getIdentityCard() {
        return IdentityCard;
    }

    public void setIdentityCard(String identityCard) {
        IdentityCard = identityCard;
    }

    public String getWechat() {
        return Wechat;
    }

    public void setWechat(String wechat) {
        Wechat = wechat;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public void setCarNumber(String carNumber) {
        CarNumber = carNumber;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getCashPledge() {
        return CashPledge;
    }

    public void setCashPledge(String cashPledge) {
        CashPledge = cashPledge;
    }

    public String getUserCondition() {
        return UserCondition;
    }

    public void setUserCondition(String userCondition) {
        UserCondition = userCondition;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "Phone='" + Phone + '\'' +
                ", Password='" + Password + '\'' +
                ", RepulationValue='" + RepulationValue + '\'' +
                ", Name='" + Name + '\'' +
                ", NickName='" + NickName + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", Identity='" + Identity + '\'' +
                ", IdentityCard='" + IdentityCard + '\'' +
                ", Wechat='" + Wechat + '\'' +
                ", QQ='" + QQ + '\'' +
                ", CarNumber='" + CarNumber + '\'' +
                ", Balance='" + Balance + '\'' +
                ", CashPledge='" + CashPledge + '\'' +
                ", UserCondition='" + UserCondition + '\'' +
                '}';
    }
}
