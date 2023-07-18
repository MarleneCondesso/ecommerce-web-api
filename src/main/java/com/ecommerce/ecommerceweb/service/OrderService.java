package com.ecommerce.ecommerceweb.service;

import com.ecommerce.ecommerceweb.lib.dto.OrderDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Value("${BASE_URL}")
    private String base_url;

    @Value("${STRIPE_SECRET_KEY}")
    private String stripe_secret_key;

    public Session create(List<OrderDTO> orderDTOList) throws StripeException {

        String success_url = base_url + "payment/success";

        String failure_url = base_url + "payment/fail";

        Stripe.apiKey = stripe_secret_key;

        List<SessionCreateParams.LineItem> session_item_list = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList){
            session_item_list.add(createSessionLineItem(orderDTO));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failure_url)
                .setSuccessUrl(success_url)
                .addAllLineItem(session_item_list)
                .build();

        return Session.create(params);
    }

    public SessionCreateParams.LineItem createSessionLineItem(OrderDTO orderDTO){
        return SessionCreateParams.LineItem
                .builder()
                .setPriceData(createPriceData(orderDTO))
                .setQuantity(Long.valueOf(orderDTO.getQuantity()))
                .build();
    }

    public SessionCreateParams.LineItem.PriceData createPriceData(OrderDTO orderDTO){
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long)orderDTO.getPrice()*100)
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(orderDTO.getProduct_name())
                        .build())
                .build();
    }
}
