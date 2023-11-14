package com.xyz.order_dispatcher.dto;

import javax.validation.constraints.NotNull;

public class OrderRequest {

    @NotNull(message = "deliveryLocation cannot be null")
    private String deliveryLocation;

    @NotNull(message = "warehouseLocation cannot be null")
    private String warehouseLocation;
}
