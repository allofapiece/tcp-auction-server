package com.ock.au.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Class describes the lot of auction. Lot is the main part of the subject
 * area.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
@Entity
@Table(name = "lots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "name")
    @Type(type = "string")
    protected String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    protected LotStatus status;

    protected Timestamp endTime;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
    protected User seller;

    @Column(name = "update_at")
    @Type(type = "timestamp")
    protected Timestamp updateAt;
}
