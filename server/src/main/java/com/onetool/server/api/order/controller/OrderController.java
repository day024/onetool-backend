package com.onetool.server.api.order.controller;

import com.onetool.server.api.order.Orders;
import com.onetool.server.api.order.dto.request.OrderRequest;
import com.onetool.server.global.auth.login.PrincipalDetails;
import com.onetool.server.global.exception.ApiResponse;
import com.onetool.server.api.order.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onetool.server.api.order.dto.response.OrderResponse.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    public ApiResponse<String> createOrders(@AuthenticationPrincipal PrincipalDetails principal,
                                                          @RequestBody OrderRequest request){
        orderService.makeOrder(principal.getContext(), request);
        return ApiResponse.onSuccess("주문 생성이 완료되었습니다.");
    }

    @GetMapping
    public ApiResponse<List<MyPageOrderResponseDto>> getOrders(@AuthenticationPrincipal PrincipalDetails principal){
        return ApiResponse.onSuccess(orderService.getMyPageOrder(principal.getContext()));
    }
}