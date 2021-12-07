package com.werth.getethbalance.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

public class Transaction {
    private Date date;
    private String id;
    private String message;
    private BigInteger amount;

    public Transaction(String message, BigInteger amount) {
        this.date = new Date();
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                '}';
    }
}
