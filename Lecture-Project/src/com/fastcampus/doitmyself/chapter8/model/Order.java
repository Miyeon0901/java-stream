package com.fastcampus.doitmyself.chapter8.model;

import com.fastcampus.doitmyself.chapter6.model.OrderLine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private long id;
    private LocalDateTime createAt;
    private long createdByUserId;
    private OrderStatus stauts;
    private BigDecimal amount;
    private List<com.fastcampus.doitmyself.chapter6.model.OrderLine> orderLines;

    public enum OrderStatus {
        CREATED,
        IN_PROGRESS,
        ERROR,
        PROCESSED
    }

    public long getId() {
        return id;
    }

    public Order setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Order setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public long getCreatedByUserId() {
        return createdByUserId;
    }

    public Order setCreatedByUserId(long createdByUserId) {
        this.createdByUserId = createdByUserId;
        return this;
    }

    public OrderStatus getStauts() {
        return stauts;
    }

    public Order setStauts(OrderStatus stauts) {
        this.stauts = stauts;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Order setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public List<com.fastcampus.doitmyself.chapter6.model.OrderLine> getOrderLines() {
        return orderLines;
    }

    public Order setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", createdByUserId=" + createdByUserId +
                ", stauts=" + stauts +
                ", amount=" + amount +
                ", orderLines=" + orderLines +
                '}';
    }
}
