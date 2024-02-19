package com.example.SpringBootTMS.work43;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    @Autowired
    OrderService service;
    @GetMapping("put")
    public String putPage(Model model)
    {  model.addAttribute("order", new Order());
        return "addOrder";
    }
    @PostMapping("/put/order")
    public String put(@ModelAttribute Order order, Model model)
    {
        model.addAttribute("message",service.put(order));
        return "result";
    }
    @GetMapping("/get/order/{id}")
    public String get(@PathVariable("id") Long id,Model model, Order order)
    {
        order =service.read(id);
        if(order!=null){
        model.addAttribute("name",order.getCustomerName());
        model.addAttribute("address",order.getAddress());
        model.addAttribute("type",order.getTypeProduct());
        model.addAttribute("cost",order.getCost());
        return "order";}
        else
        {   model.addAttribute("message","Order is not found");
            return "result";}
    }
    @GetMapping("update/order/{id}/{address}")
    public String update(@PathVariable("id") Long id, @PathVariable("address") String address, Model model)
    {
        model.addAttribute("message",service.update(id,address));
        return "result";
    }
    @GetMapping("delete/order/{id}")
    public String delete(@PathVariable("id") Long id,Model model)
    {
        model.addAttribute("message",service.delete(id));
        return "result";
    }
}
