package com.Shopping.Backend.User.User.API.DTO.RequstBody;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    UUID userId;
    List<OrderProductDto> orderProductDtoList;
}
