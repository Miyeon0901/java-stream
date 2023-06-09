package com.fastcampus.doitmyself.chapter10;

import com.fastcampus.doitmyself.chapter10.service.OrderProcessStep;
import com.fastcampus.doitmyself.chapter10.model.Order;
import com.fastcampus.doitmyself.chapter10.model.Order.OrderStatus;
import com.fastcampus.doitmyself.chapter10.model.OrderLine;

import java.math.BigDecimal;
import java.util.Arrays;

public class Chapter10Section6 {
    /**
     * Chain of Responsibility Pattern(책임 연쇄 패턴)
     * - 행동 패턴의 하나.
     * - 명령과 명령을 각각의 방법으로 처리할 수 있는 처리 객체들이 있을 때,
     *      - 처리 객체들을 체인으로 엮는다.
     *      - 명령을 처리 객체들이 체인의 앞에서부터 하나씩 처리해보도록 한다.
     *      - 각 처리 객체는 자신이 처리할 수 없을 때 체인의 다음 처리 객체로 명령을 넘긴다.
     *      - 체인의 끝에 다다르면 처리가 끝난다.
     * - 새로운 처리 객체를 추가하는 것으로 매우 간단한 처리 방법을 더할 수 있다.
     */

    public static void main(String[] args) {
        OrderProcessStep initializeStep = new OrderProcessStep(order -> {
            if (order.getStatus() == OrderStatus.CREATED) {
                System.out.println("Start processing order " + order.getId());
                order.setStatus(OrderStatus.IN_PROGRESS);
            }
        });

        OrderProcessStep setOrderAmountStep = new OrderProcessStep(order -> {
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("Setting amount of order " + order.getId());
                order.setAmount(order.getOrderLines().stream().map(OrderLine::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            }
        });

        OrderProcessStep verifyOrderStep = new OrderProcessStep(order -> {
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("Verifying order " + order.getId());
                if (order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    order.setStatus(OrderStatus.ERROR);
                }
            }
        });

        OrderProcessStep processPaymentStep = new OrderProcessStep(order -> {
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("Processing payment of order " + order.getId());
                order.setStatus(OrderStatus.PROCESSED);
            }
        });

        OrderProcessStep handleErrorStep = new OrderProcessStep(order -> {
            if (order.getStatus() == OrderStatus.ERROR) {
                System.out.println("sending out 'Failed to process order' alert for order " + order.getId());
            }
        });

        OrderProcessStep completeProcessingOrderStep = new OrderProcessStep(order -> {
            if (order.getStatus() == OrderStatus.PROCESSED) {
                System.out.println("Finished processing order " + order.getId());
            }
        });

        OrderProcessStep chainedOrderProcessSteps = initializeStep
                .setNext(setOrderAmountStep)
                .setNext(verifyOrderStep)
                .setNext(processPaymentStep)
                .setNext(handleErrorStep)
                .setNext(completeProcessingOrderStep);

        Order order = new Order()
                .setId(1001L)
                .setStatus(OrderStatus.CREATED)
                .setOrderLines(Arrays.asList(new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));

//        chainedOrderProcessSteps.process(order);

        Order failingOrder = new Order()
                .setId(1001L)
                .setStatus(OrderStatus.CREATED)
                .setOrderLines(Arrays.asList(new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(-2000))));

        chainedOrderProcessSteps.process(failingOrder);

    }
}
