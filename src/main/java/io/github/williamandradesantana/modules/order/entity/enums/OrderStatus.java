package io.github.williamandradesantana.modules.order.entity.enums;

public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    PAID("PAID"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");


    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getStatus().equals(status)) {
                return value;
            }
        }
        throw new IllegalArgumentException("orderStatus need be [CANCELED, SHIPPED, DELIVERED, WAITING_PAYMENT, PAID]");
    }
}
