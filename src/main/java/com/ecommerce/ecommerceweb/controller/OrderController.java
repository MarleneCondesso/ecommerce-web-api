package com.ecommerce.ecommerceweb.controller;

import com.ecommerce.ecommerceweb.lib.dto.OrderDTO;
import com.ecommerce.ecommerceweb.lib.stripe.StripeResponse;
import com.ecommerce.ecommerceweb.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<StripeResponse> create(@RequestBody List<OrderDTO> orderDTOList) throws StripeException {

        Session session = orderService.create(orderDTOList);

        return new ResponseEntity<>(new StripeResponse(session.getId()), HttpStatus.OK);
    }
}
