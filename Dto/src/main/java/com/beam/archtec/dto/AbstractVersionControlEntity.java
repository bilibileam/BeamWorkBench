package com.beam.archtec.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractVersionControlEntity<I> implements CodeEntity,IdEntity<I> {

    /**
     */
    private static final long serialVersionUID = 4875841980407681501L;

    @Column(name = "VERSION_NO")
    private Long versionNo;

    @Column(name = "IN_USE")
    private String inUse;
    
    @Column(name = "IS_AVAILABLE")
    private String isAvailable;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVE_START")
    private Date activeStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVE_END")
    private Date activeEnd;
    
}
