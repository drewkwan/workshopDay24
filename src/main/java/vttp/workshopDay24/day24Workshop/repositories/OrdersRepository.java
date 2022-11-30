package vttp.workshopDay24.day24Workshop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.workshopDay24.day24Workshop.models.Item;
import vttp.workshopDay24.day24Workshop.models.PurchaseOrder;

import static vttp.workshopDay24.day24Workshop.repositories.Queries.*;

import java.util.List;

@Repository
public class OrdersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Integer createPurchaseOrder(PurchaseOrder po) {

        return jdbcTemplate.update(SQL_INSERT_PURCHASE_ORDER, 
                                po.getId(),
                                po.getOrderDate(),
                                po.getCustomerName(),
                                po.getShipAddress(),
                                po.getNotes());
    }

    public void createOrderDetails(List<Item> i, String orderId) {

        List<Object[]> data = i.stream()
                        .map(items -> {
                            Object[] l = new Object[5];
                            l[0]= orderId;
                            l[1]=items.getItemName();
                            l[2]=items.getPrice();
                            l[3]=items.getDiscount();
                            l[4]=items.getQuantity();
                            return l;
                        })
                        .toList();

        jdbcTemplate.batchUpdate(SQL_INSERT_ORDER_DETAILS, data);

        // return jdbcTemplate.update(SQL_INSERT_ORDER_DETAILS, 
        //                         orderId,
        //                         i.getItemName(), 
        //                         i.getPrice(), 
        //                         i.getDiscount(),
        //                         i.getQuantity());
        
    }

    public void createOrderDetails(PurchaseOrder po) {
        createOrderDetails(po.getItems(), po.getId());
    }
    
    
}
