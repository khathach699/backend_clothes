package com.example.backend_clothes.mapper;

import com.example.backend_clothes.dto.response.OrderItemResponse;
import com.example.backend_clothes.dto.response.OrderResponse;
import com.example.backend_clothes.entity.OrderItem;
import com.example.backend_clothes.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    @Mapping(source = "paymentMethod.name", target = "paymentMethodName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderResponse orderToOrderResponse(Orders order);

    List<OrderResponse> ordersToOrderResponses(List<Orders> orders);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "productColorSize.color.id", target = "colorId")
    @Mapping(source = "productColorSize.size.id", target = "sizeId")
    OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem);

    List<OrderItemResponse> orderItemsToOrderItemResponses(List<OrderItem> orderItems);
}
