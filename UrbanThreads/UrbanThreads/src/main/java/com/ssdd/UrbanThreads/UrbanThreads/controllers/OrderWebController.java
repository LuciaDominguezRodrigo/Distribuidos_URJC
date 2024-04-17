package com.ssdd.UrbanThreads.UrbanThreads.controllers;

import com.ssdd.UrbanThreads.UrbanThreads.entities.Order;
import com.ssdd.UrbanThreads.UrbanThreads.entities.OrderStatus;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Product;
import com.ssdd.UrbanThreads.UrbanThreads.entities.Size;
import com.ssdd.UrbanThreads.UrbanThreads.services.OrderService;
import com.ssdd.UrbanThreads.UrbanThreads.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

   /* @PostMapping("/newProductInOrder")
    public String addToOrder(@RequestParam("id") int productId,
                             @RequestParam("selectedSize") String size,
                             @RequestParam("selectedColor") String color,
                             @RequestParam("quantity") int quantity) {
        Optional<Product> product = productService.findProduct(productId);
        orderService.addProductToCurrentOrder(productId, product.get(), size, color, quantity);

        return "redirect:/orderPage";
    }

    @GetMapping("/orderPage")
    public String showOrderPage(Model model) {
        Order currentOrder = orderService.getCurrentOrder();
        if(currentOrder == null){
            currentOrder = new Order();
            orderService.addNewOrder(currentOrder);
            orderService.changeCurrentOrder(currentOrder.getOrderId());
        }
        List<Product> orderProducts = currentOrder.getProducts();
        for (Product p : orderProducts) {
            p.setSelectedSizeAvailableUnits(productService.findProduct(p.getId()).getAvailableSizes().get(Size.valueOf(p.getSize())));
        }
        model.addAttribute("orderId", currentOrder.getOrderId());
        model.addAttribute("allOrdersId", orderService.getAllPendingOrdersId());
        model.addAttribute("productList", orderProducts);
        return "orderPage";
    }

    @PostMapping("/cancelOrder")
    public String cancelOrder() {
        orderService.deleteCurrentOrder();
        //When an order is removed, the current order changes to next created order or, if there´s no more orders created, a new one is created and marked as current order.
        List<Integer> allOrdersId = orderService.getAllPendingOrdersId();
        if(!allOrdersId.isEmpty()){
            orderService.changeCurrentOrder(allOrdersId.get(0));
        } else{
            int newOrderId = orderService.addNewOrder(new Order());
            orderService.changeCurrentOrder(newOrderId);
        }

        return "redirect:/";
    }

    @PostMapping("/orderReady")
    public String makeOrder(Model model) {
        Order currentOrder = orderService.getCurrentOrder();
        if (currentOrder != null) {
            model.addAttribute("productList", currentOrder.getProducts());
            currentOrder.setOrderStatus(OrderStatus.COMPLETED);
            productService.updateProductsQuantity(currentOrder.getProducts());

            //When an order is removed, the current order changes to next created order or, if there´s no more orders created, a new one is created and marked as current order.
            List<Integer> allOrdersId = orderService.getAllPendingOrdersId();
            if(!allOrdersId.isEmpty()){
                orderService.changeCurrentOrder(allOrdersId.get(0));
            } else{
                int newOrderId = orderService.addNewOrder(new Order());
                orderService.changeCurrentOrder(newOrderId);
            }

            return "orderMade";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editOrder")
    public String newProduct(@RequestParam int productId,
                             @RequestParam String productSize,
                             @RequestParam String productColor,
                             @RequestParam("quantity") int quantity) {

        Order currentOrder = orderService.getCurrentOrder();
        Product changedProduct = new Product();
        for (Product orderProduct : currentOrder.getOrderProducts()) { //If product is ordered, must be found
            if(orderProduct.getId() == productId && orderProduct.getSize().equals(productSize) && orderProduct.getColor().equals(productColor)){
                changedProduct = orderProduct;
            }
        }
        changedProduct.setQuantity(quantity);

        return "redirect:/orderPage";
    }

    @PostMapping("/changeOrder")
    public String changeOrder(@RequestParam int selectedOrder) {
        orderService.changeCurrentOrder(selectedOrder);
        return "redirect:/orderPage";
    }

    @PostMapping("/newOrder")
    public String newOrder() {
        Order newOrder = new Order();
        orderService.addNewOrder(newOrder);
        orderService.changeCurrentOrder(newOrder.getOrderId());
        return "redirect:/orderPage";
    }



    @PostMapping("/deleteProductOrder")
    public String  eliminarProductoDePedido(@RequestParam("orderId") int pedidoId, @RequestParam("productId") int productoId) {
        orderService.deleteOrderedProduct(pedidoId, productoId);
        Product productoEliminado = orderService.getDeletedProduct(productoId);
        productService.updateProduct(productoId,productoEliminado);
        return "redirect:/orderPage";
    }*/
}

