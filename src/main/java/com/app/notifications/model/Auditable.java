package com.app.notifications.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> implements Serializable {

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private U createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Date lastModifiedDate;

}
