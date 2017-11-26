package com.zr.study.disuo_1.bean;

/**
 * Created by duray on 17-10-17.
 */

public class UserEntity {

    String nickname;
    String sex;
    String birthday;
    String identity;
    String telephone;
    String wechat;
    String qq;
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getIdentity() {
        return identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "UserEntity [nickname=" + nickname + ", sex=" + sex
                + ", birthday=" + birthday + ", identity=" + identity
                + ", telephone=" + telephone + ", wechat=" + wechat + ", qq="
                + qq + "]";
    }
}
