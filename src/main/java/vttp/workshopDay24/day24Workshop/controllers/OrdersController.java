package vttp.workshopDay24.day24Workshop.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.workshopDay24.day24Workshop.models.Item;
import vttp.workshopDay24.day24Workshop.models.PurchaseOrder;
import vttp.workshopDay24.day24Workshop.services.OrderException;
import vttp.workshopDay24.day24Workshop.services.OrderService;

@Controller
@RequestMapping(path="/order")
public class OrdersController {

    @Autowired
    private OrderService orderSvc;


    @PostMapping
    public String postOrder(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session) throws ParseException, OrderException {

        List<Item> items = (List<Item>) session.getAttribute("order");

        if (items == null) {
            System.out.println("this is a new session");
            System.out.printf(" seission id = %s\n", session.getId());
            items = new LinkedList<>();
            session.setAttribute("order", items);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        PurchaseOrder po = new PurchaseOrder();
        po.setCustomerName(form.getFirst("customerName"));
        po.setShipAddress(form.getFirst("address"));
        po.setNotes(form.getFirst("notes"));
        po.setTax(Float.parseFloat(form.getFirst("tax")));
        // po.setOrderDate(formatter.parse(form.getFirst("date")));
        po.setOrderDate(form.getFirst("date")); 

        String itemName = form.getFirst("itemName");
        Float price = Float.parseFloat(form.getFirst("itemPrice"));
        Float discount = Float.parseFloat(form.getFirst("discount"));
        int quantity = Integer.parseInt(form.getFirst("quantity"));
        items.add(new Item(itemName, price, discount, quantity));

        po.setItems(items);

        session.setAttribute("po", po);


        // //put in the queries
        // orderSvc.createOrder(po);

        model.addAttribute("items", items);
        model.addAttribute("po", po);

        return "index_template";
        
        
    }

    @PostMapping(path="/checkout")
    public String postCheckout(Model model, HttpSession session) throws OrderException {
        List<Item> items = (List<Item>) session.getAttribute("order");
        PurchaseOrder po= (PurchaseOrder) session.getAttribute("po");
        //put the query in for checkout? Check with chuk again how and when to use the svc to checkout.
        orderSvc.createOrder(po);
        session.invalidate();
        String success="Checkout was successful! Thanks for shopping with us!";
        model.addAttribute("success", success);

        return "checkout";
    }

    
}
