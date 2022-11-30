package vttp.workshopDay24.day24Workshop.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.workshopDay24.day24Workshop.models.PurchaseOrder;
import vttp.workshopDay24.day24Workshop.repositories.OrdersRepository;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepo;

    public void createOrder(PurchaseOrder po) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);

        po.setId(orderId);
        System.out.println("ORDER ID >>>>>>>> " + orderId);

        //create the order
        ordersRepo.createPurchaseOrder(po);

        //create the associated items
        ordersRepo.createOrderDetails(po.getItems(), orderId);

    }
    
}
