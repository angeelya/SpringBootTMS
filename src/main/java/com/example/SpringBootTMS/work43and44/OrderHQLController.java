package com.example.SpringBootTMS.work43and44;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderHQLController {
    @Autowired
    OrderHQLService service;

    @GetMapping("/insert_order")
    public String putPage(Model model) {
        model.addAttribute("order", new Order());
        return "addOrderHQL";
    }

    @PostMapping("/insert/order")
    public String put(@ModelAttribute Order order, Model model) {
        model.addAttribute("message", service.insert(order));
        return "result";
    }

    @GetMapping("get/all/orders")
    public String getAll(Model model) {
        model.addAttribute("orders", service.getAll());
        return "ordersList";
    }

    @GetMapping("get/customer/name/{type}")
    public String getAll(@PathVariable("type") String type, Model model) {
        List<String> names = service.getCustomerName(type);
        if (names.size() != 0) {
            model.addAttribute("names", names);
            return "customerName";
        } else {
            model.addAttribute("message","Nothing found");
                return "result";
        }
    }
    @GetMapping("/update/order/hql/{id}/{address}")
    public String update(@PathVariable("id") Long id, @PathVariable("address") String address, Model model) {
        model.addAttribute("message", service.update(id,address));
        return "result";
    }
    @GetMapping("delete/order/hql/{id}")
    public String delete(@PathVariable("id") Long id,Model model)
    {
        model.addAttribute("message", service.delete(id));
        return "result";
    }
    @GetMapping("get/order/more/than/{sum}")
    public String getOrdersCostOfMoreThan(@PathVariable("sum") Integer sum, Model model)
    {
        List<Order> orders = service.getOrderCostMore(sum);
        if (orders.size() != 0) {
            model.addAttribute("orders", orders);
            return "ordersList";
        } else {
            model.addAttribute("message","Nothing found");
            return "result";
        }
    }
}
