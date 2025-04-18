package com.example.marketplace.mapper;

import com.example.marketplace.dto.OrderDto;
import com.example.marketplace.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends Mappable<Order, OrderDto> {
}
