package com.kipa.javabootcamp.javaservlet.listener;

import com.kipa.javabootcamp.javaservlet.unit.Audit;
import com.kipa.javabootcamp.javaservlet.unit.Auditable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {
    @PrePersist
    public void prePersist(Object object) {
        if(object instanceof Auditable) {
            Audit audit = ((Auditable)object).getAudit();

            if(audit == null) {
                audit = new Audit();
                ((Auditable)object).setAudit(audit);
            }

            audit.setCreatedAt(LocalDateTime.now());
            audit.setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object object) {
        if(object instanceof Auditable) {
            Audit audit = ((Auditable) object).getAudit();
            audit.setUpdatedAt(LocalDateTime.now());
        }
    }
}
