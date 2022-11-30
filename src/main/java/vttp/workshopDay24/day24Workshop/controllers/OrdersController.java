package vttp.workshopDay24.day24Workshop.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.workshopDay24.day24Workshop.models.Item;
import vttp.workshopDay24.day24Workshop.models.PurchaseOrder;
import vttp.workshopDay24.day24Workshop.services.OrderService;

@Controller
@RequestMapping(path="/order")
public class OrdersController {

    @Autowired
    private OrderService orderSvc;


    @PostMapping
    public String postOrder(@RequestBody MultiValueMap<String, String> form, Model model) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        PurchaseOrder po = new PurchaseOrder();
        po.setCustomerName(form.getFirst("customerName"));
        po.setShipAddress(form.getFirst("address"));
        po.setNotes(form.getFirst("notes"));
        po.setTax(Float.parseFloat(form.getFirst("tax")));
        po.setOrderDate(formatter.parse(form.getFirst("date")));

        List<Item> items = new LinkedList<>();
        
        String itemName = form.getFirst("itemName");
        Float price = Float.parseFloat(form.getFirst("itemPrice"));
        Float discount = Float.parseFloat(form.getFirst("discount"));
        int quantity = Integer.parseInt(form.getFirst("quantity"));
        items.add(new Item(itemName, price, discount, quantity));

        po.setItems(items);


        //put in the queries
        orderSvc.createOrder(po);

        if (!orderSvc.createOrder(po)) {
            String error = "Order couldn't be created :(";
            model.addAttribute("error", error);
        } else{
            String success="Order was created successfully!";
            model.addAttribute("success", success);
            model.addAttribute("purchaseOrder", po);
        }

    

        return "/add";
        
        
    }
    
}
