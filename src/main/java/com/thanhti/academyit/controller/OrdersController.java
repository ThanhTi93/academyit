package com.thanhti.academyit.controller;

import com.thanhti.academyit.dto.OrderDTO;
import com.thanhti.academyit.entity.*;
import com.thanhti.academyit.service.OrderDetailService;
import com.thanhti.academyit.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @RequestMapping("")
    public String showOrders( ModelMap model,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderPage = orderService.findPaginated(pageable);
        model.addAttribute("page", orderPage);
        return "admin/orders/Orders";
    }

    @GetMapping("edit/{orderId}")
    public ModelAndView edit(ModelMap model, @PathVariable("orderId") Long orderId) {

        Optional<Order> opt = orderService.findById(orderId);
        OrderDTO dto = new OrderDTO();
        List<OrderDetail> listOrderDetail = orderDetailService.findByOrderId(orderId);
        if(opt.isPresent()) {
            Order entity = opt.get();

            BeanUtils.copyProperties(entity, dto);

            Double total = listOrderDetail.stream()
                    .mapToDouble(tt -> tt.getUnitPrice() * tt.getQuantity()).sum();
            model.addAttribute("order", dto);
            model.addAttribute("listOrderDetails", listOrderDetail);
            model.addAttribute("total", total);
        }

        return new ModelAndView("admin/orders/OrderDetail", model);
    }

    @PostMapping("addOrder")
    public  ModelAndView addOrder(ModelMap model ,@ModelAttribute OrderDTO order, @ModelAttribute("cartItems") List<CartItem> cartItems) {

        switch (order.getPaymentMethod()) {
            case "PayPay":
                order.setPaymentMethod(String.valueOf(OrderStatus.PAID));
                break;
            case "Payment on delivery":
                order.setPaymentMethod(String.valueOf(OrderStatus.UNPAID));
                break;
            default:
                order.setPaymentMethod(String.valueOf(OrderStatus.UNPAID));
        }
        orderService.createOrder(order, cartItems);

        return new ModelAndView("admin/orders/OrderDetail", model);
    }
}
