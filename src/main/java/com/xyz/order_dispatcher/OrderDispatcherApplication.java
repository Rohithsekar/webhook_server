package com.xyz.order_dispatcher;

import com.xyz.order_dispatcher.dto.OrderDTO;
import com.xyz.order_dispatcher.dto.StructuredOrder;
import com.xyz.order_dispatcher.model.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/home")
public class OrderDispatcherApplication {

    public static final String WAREHOUSE_LOCATION = "G";

    private static final String WEBHOOK_URL = "http://localhost:8090/webhook/sort";

    private static final String WEBHOOK_URL_CUSTOM_SORT = "http://localhost:8090/webhook/custom/sort";
    private static final String AUTHORIZATION_HEADER = "GRANT";

    public static void main(String[] args) {
        SpringApplication.run(OrderDispatcherApplication.class, args);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostMapping
    @RequestMapping("/placeOrder")
    public ResponseEntity<Collection<Order>> dispatchOrderList() throws InterruptedException {

        List<Order> orders = createOrderList();

        RestTemplate restTemplate = restTemplate();
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AUTHORIZATION_HEADER);

        // Create the request entity with headers and payload
        HttpEntity<List<Order>> requestEntity = new HttpEntity<>(orders, headers);

        // Define the HTTP method and the expected response type
        HttpMethod method = HttpMethod.POST;
        // Define the response type using ParameterizedTypeReference
        ParameterizedTypeReference<Collection<Order>> responseType = new ParameterizedTypeReference<Collection<Order>>() {
        };

        // Make the POST request using exchange method
        ResponseEntity<Collection<Order>> responseEntity = restTemplate.exchange(WEBHOOK_URL, method, requestEntity, responseType);

        // You can handle the response as needed
        Collection<Order> sortedOrders = responseEntity.getBody();

        return ResponseEntity.status(HttpStatus.CREATED).body(sortedOrders);
    }

    @PostMapping
    @RequestMapping("/orders")
    public ResponseEntity<StructuredOrder> dispatchOrders(@RequestBody @Valid StructuredOrder orderRequest) {


        //For sending the payload.
        RestTemplate restTemplate = restTemplate();

         int count = orderRequest.getDeliveryLocations().length;
         OrderDTO[] orders = orderRequest.getDeliveryLocations();

        for(int i=0; i<count; i++){
            orders[i].setId(i);
            orders[i].setTimestamp(System.currentTimeMillis());
        }

        orderRequest.setDeliveryLocations(orders);

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AUTHORIZATION_HEADER);


        // Create the request entity with headers and payload
        HttpEntity<StructuredOrder> requestEntity = new HttpEntity<>(orderRequest, headers);

        // Define the HTTP method and the expected response type
        HttpMethod method = HttpMethod.POST;
        // Define the response type using ParameterizedTypeReference
        Class<StructuredOrder> responseType = StructuredOrder.class;

        // Make the POST request using exchange method
        ResponseEntity<StructuredOrder> responseEntity = restTemplate.exchange(WEBHOOK_URL_CUSTOM_SORT, method, requestEntity, responseType);

        // You can handle the response as needed
        StructuredOrder sortedOrders = responseEntity.getBody();

        return ResponseEntity.status(HttpStatus.CREATED).body(sortedOrders);
    }

    public static List<Order> createOrderList() throws InterruptedException {

        String deliveryLocations[] = {"Chennai", "Delhi", "Hyderabad", "Florida", "Zurich", "Hamburg"};
        List<Order> orderList = new ArrayList<>();

        for (int index = 1; index <= deliveryLocations.length; index++) {


            Order order = Order.builder()
                    .id(index)
                    .timestamp(Instant.now().getEpochSecond())
                    .deliveryLocation(deliveryLocations[index - 1])
                    .warehouseLocation(WAREHOUSE_LOCATION)
                    .build();

            orderList.add(order);

        }

        return orderList;

    }


}
