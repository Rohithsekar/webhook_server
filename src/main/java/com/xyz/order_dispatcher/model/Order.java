package com.xyz.order_dispatcher.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Order {

    private Integer id;
    private Long timestamp;
    private String deliveryLocation;
    private String warehouseLocation;

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

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    // Private constructor to enforce the use of the builder
    // JsonCreator annotation for the builder's build method
    @JsonCreator
    private Order(
            @JsonProperty("id") Integer id,
            @JsonProperty("timestamp") Long timestamp,
            @JsonProperty("deliveryLocation") String deliveryLocation,
            @JsonProperty("warehouseLocation") String warehouseLocation) {
        this.id = id;
        this.timestamp = timestamp;
        this.deliveryLocation = deliveryLocation;
        this.warehouseLocation = warehouseLocation;
    }


    // Builder interface
    public interface Builder {
        Builder id(Integer id);

        Builder timestamp(Long timestamp);

        Builder deliveryLocation(String deliveryLocation);

        Builder warehouseLocation(String warehouseLocation);

        Order build();
    }

    // Concrete implementation of the builder interface
    public static class BuilderImpl implements Builder {
        private Integer id;
        private Long timestamp;
        private String deliveryLocation;
        private String warehouseLocation;

        @Override
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        @Override
        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        @Override
        public Builder deliveryLocation(String deliveryLocation) {
            this.deliveryLocation = deliveryLocation;
            return this;
        }

        @Override
        public Builder warehouseLocation(String warehouseLocation) {
            this.warehouseLocation = warehouseLocation;
            return this;
        }

        @Override
        public Order build() {
            // Validate required fields
            if (id == null) {
                throw new IllegalStateException("Id is required");
            }

            // You can add more validation if needed for other fields

            // Create and return the Order object
            return new Order(id, timestamp, deliveryLocation, warehouseLocation);
        }
    }

    // Static factory method to create a new builder
    /*
    Good Design Pattern: Abstraction: The interface defines a contract for building the object, hiding the
    implementation details. This abstraction allows clients to work with the builder without knowing
    its concrete type.Return an interface type not the concrete type, thereby hiding the
    implementation details from the client classes.
     */
    public static Builder builder() {
        return new BuilderImpl();
    }



}
