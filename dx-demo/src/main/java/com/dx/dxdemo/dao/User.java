package com.dx.dxdemo.dao;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tWX579831
 * @date 2020/3/19 23:02
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private Long telnum;
    private String address;
    private String otherMsg;
    private Long startWorkTime;
    private Long endWorkTime;
    @Lob
//    @Column(columnDefinition = "text")
    private String bankCardBase64;
    @Lob
//    @Column(columnDefinition = "text")
    private String idCardBase64;
    @Lob
//    @Column(columnDefinition = "text")
    private String photoBase64;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTelnum() {
        return telnum;
    }

    public void setTelnum(Long telnum) {
        this.telnum = telnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }

    public Long getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(Long startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Long getEndWorkTime() {
        return endWorkTime;
    }

    public void setEndWorkTime(Long endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public String getBankCardBase64() {
        return bankCardBase64;
    }

    public void setBankCardBase64(String bankCardBase64) {
        this.bankCardBase64 = bankCardBase64;
    }

    public String getIdCardBase64() {
        return idCardBase64;
    }

    public void setIdCardBase64(String idCardBase64) {
        this.idCardBase64 = idCardBase64;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }
}
