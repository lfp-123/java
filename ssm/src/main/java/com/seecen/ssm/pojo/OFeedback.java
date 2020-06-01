package com.seecen.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

public class OFeedback implements Serializable {
    private Long id;

    private String name;

    private String job;

    private String email;

    private String phone;

    private String company;

    private String address;

    private String deal;

    private Long operator;

    private Date dealTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal == null ? null : deal.trim();
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", job=").append(job);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", company=").append(company);
        sb.append(", address=").append(address);
        sb.append(", deal=").append(deal);
        sb.append(", operator=").append(operator);
        sb.append(", dealTime=").append(dealTime);
        sb.append("]");
        return sb.toString();
    }
}