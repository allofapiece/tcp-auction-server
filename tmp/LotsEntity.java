package com.ock.au.service.hojklsdfb;

import com.ock.au.entity.LotStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "lots", schema = "au", catalog = "")
public class LotsEntity {
    private int id;
    private String sellerId;
    private LotStatus status;
    private Timestamp endTime;
    private Integer betTime;
    private String message;
    private Timestamp updateAt;
    private UsersEntity seller;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "status")
    public LotStatus getStatus() {
        return status;
    }

    public void setStatus(LotStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "bet_time")
    public Integer getBetTime() {
        return betTime;
    }

    public void setBetTime(Integer betTime) {
        this.betTime = betTime;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotsEntity that = (LotsEntity) o;
        return id == that.id &&
                Objects.equals(sellerId, that.sellerId) &&
                status == that.status &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(message, that.message) &&
                Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sellerId, status, endTime, betTime, message, updateAt);
    }

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getSeller() {
        return seller;
    }

    public void setSeller(UsersEntity seller) {
        this.seller = seller;
    }
}
