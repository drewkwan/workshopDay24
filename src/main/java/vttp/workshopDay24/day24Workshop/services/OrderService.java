package vttp.workshopDay24.day24Workshop.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.workshopDay24.day24Workshop.models.PurchaseOrder;
import vttp.workshopDay24.day24Workshop.repositories.OrdersRepository;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createOrder(PurchaseOrder po) throws OrderException {

        //generate the orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);

        po.setId(orderId);
        System.out.println("ORDER ID >>>>>>>> " + orderId);

        //create the order
        int count = ordersRepo.createPurchaseOrder(po);

        if (count == 0){
            throw new OrderException("Exception for orderId %s\n".formatted(orderId));

        }

        //create the associated items
        ordersRepo.createOrderDetails(po.getItems(), orderId);

    
    }
    
}
