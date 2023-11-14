package com.xyz.order_dispatcher.dto;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class StructuredOrder {

    @NotNull(message = "deliveryLocation cannot be null")
    private OrderDTO[] deliveryLocations;

    @NotNull(message = "warehouseLocation cannot be null")
    private String warehouseLocation;


    public StructuredOrder( OrderDTO[] deliveryLocations, String warehouseLocation) {
        this.deliveryLocations = deliveryLocations;
        this.warehouseLocation = warehouseLocation;
    }

    public StructuredOrder() {
    }

    public OrderDTO[] getDeliveryLocations() {
        return deliveryLocations;
    }

    public void setDeliveryLocations(OrderDTO[] deliveryLocations) {
        this.deliveryLocations = deliveryLocations;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public String toString() {
        return "StructuredOrder{" +
                "deliveryLocations=" + Arrays.toString(deliveryLocations) +
                ", warehouseLocation='" + warehouseLocation + '\'' +
                '}';
    }
}
