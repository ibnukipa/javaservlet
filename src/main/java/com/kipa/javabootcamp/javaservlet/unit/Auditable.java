package com.kipa.javabootcamp.javaservlet.unit;

public interface Auditable {
    Audit getAudit();
    void setAudit(Audit audit);
}
