package com.xyz.order_dispatcher.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class OrderDTO {

    private Integer id;
    private Long timestamp;
    @NotNull(message = "deliveryLocation cannot be null")
    private String deliveryLocation;


    public OrderDTO(Integer id, Long timestamp, String deliveryLocation) {
        this.id = id;
        this.timestamp = timestamp;
        this.deliveryLocation = deliveryLocation;
    }

    public OrderDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }
}
