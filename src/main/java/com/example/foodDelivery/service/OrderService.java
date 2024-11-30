package com.example.foodDelivery.service;

import com.example.foodDelivery.converter.OrderConverter;
import com.example.foodDelivery.dao.DishEntity;
import com.example.foodDelivery.dao.OrderEntity;
import com.example.foodDelivery.dao.OrderStatus;
import com.example.foodDelivery.dao.UserEntity;
import com.example.foodDelivery.dishLogic.BasicPizza;
import com.example.foodDelivery.dishLogic.Dish;
import com.example.foodDelivery.dishLogic.ExtraCheeseDecorator;
import com.example.foodDelivery.dto.OrderDto;
import com.example.foodDelivery.exception.CanNotRemoveItemFromConfirmedOrCanceledOrderException;
import com.example.foodDelivery.exception.ResourceNotFoundException;
import com.example.foodDelivery.orderConfirmationLogic.Confirmation;
import com.example.foodDelivery.orderConfirmationLogic.ConfirmationFactory;
import com.example.foodDelivery.repository.DishRepository;
import com.example.foodDelivery.repository.OrderRepository;
import com.example.foodDelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    public void createOrder(String customerPhoneNumber) throws ResourceNotFoundException {

        UserEntity user = userRepository.findByPhoneNumber(customerPhoneNumber).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User with Phone Number %s does not exist", customerPhoneNumber))
        );

        OrderEntity order = OrderConverter.dtoToEntity(OrderDto.builder().userId(user.getId()).orderStatus(OrderStatus.NEW).dishes(new ArrayList<>()).build());

        orderRepository.save(order);
    }

    public List<OrderDto> getOrderByPhoneNumber(String customerPhoneNumber) throws ResourceNotFoundException {
        UserEntity user = userRepository.findByPhoneNumber(customerPhoneNumber).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User with Phone Number %s does not exist", customerPhoneNumber))
        );

        List<OrderEntity> orders = orderRepository.findAllByUserId(user.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("User with Phone Number %s does not exist", customerPhoneNumber))
                );
        return OrderConverter.entityListToDtoList(orders);
    }

    public boolean confirmOrder(Long orderId, String confirmationMethod) throws ResourceNotFoundException {
        OrderEntity orderToConfirm = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Order with Id %s does not exist", orderId))
        );

        orderToConfirm.setOrderStatus(OrderStatus.CONFIRMED);

        orderRepository.save(orderToConfirm);

        Confirmation confirmation = ConfirmationFactory.createConfirmation(confirmationMethod);

        confirmation.send(String.format("Congratulations, your order with Id %s is now CONFIRMED", orderId));

        return true;
    }

    public boolean cancelOrder(Long orderId) throws ResourceNotFoundException {
        OrderEntity orderToConfirm = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Order with Id %s does not exist", orderId))
        );

        orderToConfirm.setOrderStatus(OrderStatus.CANCELED);

        orderRepository.save(orderToConfirm);

        return true;
    }

    public boolean addPizzaToOrder(Long orderId, Boolean extraCheese) throws ResourceNotFoundException {
        OrderEntity orderToAddTo = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Order with Id %s does not exist", orderId))
        );

        Dish pizza = new BasicPizza();
        if (extraCheese) {
            pizza = new ExtraCheeseDecorator(pizza);
        }

        DishEntity pizzaEntity = DishEntity.builder().description(pizza.getDescription()).cost(pizza.getCost()).build();

        pizzaEntity = dishRepository.save(pizzaEntity);

        orderToAddTo.getDishes().add(pizzaEntity);

        orderRepository.save(orderToAddTo);

        return true;

    }

    public boolean removeFromOrder(Long orderId, Long itemId) throws ResourceNotFoundException, CanNotRemoveItemFromConfirmedOrCanceledOrderException {
        OrderEntity orderToRemoveFrom = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Order with Id %s does not exist", orderId))
        );

        OrderStatus orderStatus = orderToRemoveFrom.getOrderStatus();
        if (orderStatus.equals(OrderStatus.CONFIRMED) || orderStatus.equals(OrderStatus.CANCELED)) {
            throw new CanNotRemoveItemFromConfirmedOrCanceledOrderException(String.format("Order with Id %s is already confirmed/canceled and can not be modified", orderId));
        }

        DishEntity dishEntity = dishRepository.findById(itemId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Item with Id %s does not exist", itemId))
        );

        orderToRemoveFrom.getDishes().remove(dishEntity);
        dishRepository.delete(dishEntity);

        orderRepository.save(orderToRemoveFrom);

        return true;
    }
}
