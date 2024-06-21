package com.thanhti.academyit.controller;
import com.thanhti.academyit.dto.OrderDTO;
import com.thanhti.academyit.entity.Account;
import com.thanhti.academyit.entity.CartItem;
import com.thanhti.academyit.entity.Product;
import com.thanhti.academyit.service.CartItemService;
import com.thanhti.academyit.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class CartController {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProductService productService;

    @Autowired
    HttpSession httpSession ;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        Account account = (Account) httpSession.getAttribute("account");
        if (account == null) {
            return "redirect:/register";
        }
        // Retrieve the cart items for the logged-in account
        List<CartItem> cartItems = cartItemService.findByAccountEmail(account.getEmail());
        model.addAttribute("cartItems", cartItems);
        OrderDTO order = new OrderDTO();
        order.setPaymentMethod("null");
        model.addAttribute("order",order);

        return "client/cart/Cart"; // Return the view name for the cart page
    }

    @PostMapping("addCart/{productId}")
    public String addCart(@PathVariable("productId") Long productId,
                          @RequestParam(name = "quantityItem", required = false) String quantity, Model model) {

        Optional<Product> otp = productService.findByIdWithCartItems(productId);
        Account account = (Account) httpSession.getAttribute("account");
        if (account == null) {
            return "redirect:/register";
        }

        int quantityToAdd = Integer.parseInt(quantity);

        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
        Optional<CartItem> existingCartItemOpt = cartItemService.findByProductIdAndEmail(productId, account.getEmail());

        if (existingCartItemOpt.isPresent()) {
            // Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
            CartItem existingCartItem = existingCartItemOpt.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantityToAdd);
            cartItemService.save(existingCartItem);
        } else {
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm mới sản phẩm
            CartItem cartItem = new CartItem();
            cartItem.setProduct(otp.get());
            cartItem.setAccount(account);
            cartItem.setQuantity(quantityToAdd);
            cartItemService.save(cartItem);
        }

        return "redirect:/cart";
    }

    @PostMapping("/cart/increase/{itemId}")
    public String increaseCartItem(@PathVariable("itemId") Long itemId) {
        Account account = (Account) httpSession.getAttribute("account");
        if (account == null) {
            return "redirect:/register";
        }

        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
        Optional<CartItem> existingCartItemOpt = cartItemService.findByProductIdAndEmail(itemId, account.getEmail());
        if (existingCartItemOpt.isPresent()) {
            CartItem cartItem = existingCartItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemService.save(cartItem);
        }

        return "redirect:/cart";
    }

    @PostMapping("/cart/decrease/{itemId}")
    public String decreaseCartItem(@PathVariable("itemId") Long itemId) {
        Account account = (Account) httpSession.getAttribute("account");
        if (account == null) {
            return "redirect:/register";
        }

        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
        Optional<CartItem> existingCartItemOpt = cartItemService.findByProductIdAndEmail(itemId, account.getEmail());
        if (existingCartItemOpt.isPresent()) {
            CartItem cartItem = existingCartItemOpt.get();
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemService.save(cartItem);
            }else {
                cartItemService.delete(cartItem);
            }
        }

        return "redirect:/cart";
    }

}
