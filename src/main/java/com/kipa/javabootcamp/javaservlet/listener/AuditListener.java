package com.kipa.javabootcamp.javaservlet.listener;

import com.kipa.javabootcamp.javaservlet.model.EmbedAudit;
import com.kipa.javabootcamp.javaservlet.model.IAuditable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {
    @PrePersist
    public void prePersist(Object object) {
        if(object instanceof IAuditable) {
            EmbedAudit audit = ((IAuditable)object).getAudit();

            if(audit == null) {
                audit = new EmbedAudit();
                ((IAuditable)object).setAudit(audit);
            }

            audit.setCreatedAt(LocalDateTime.now());
            audit.setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object object) {
        if(object instanceof IAuditable) {
            EmbedAudit audit = ((IAuditable) object).getAudit();
            audit.setUpdatedAt(LocalDateTime.now());
        }
    }
}
