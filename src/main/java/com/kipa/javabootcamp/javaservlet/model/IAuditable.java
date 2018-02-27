package com.kipa.javabootcamp.javaservlet.model;

public interface IAuditable {
    EmbedAudit getAudit();
    void setAudit(EmbedAudit audit);
}
