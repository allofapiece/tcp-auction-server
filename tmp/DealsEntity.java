package com.ock.au.service.hojklsdfb;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "deals", schema = "au", catalog = "")
public class DealsEntity {
    private int id;
    private Timestamp createdAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealsEntity that = (DealsEntity) o;
        return id == that.id &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createdAt);
    }
}
