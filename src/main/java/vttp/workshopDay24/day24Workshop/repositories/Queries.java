package vttp.workshopDay24.day24Workshop.repositories;

public class Queries {
    public static final String SQL_INSERT_PURCHASE_ORDER="INSERT INTO orders(order_id, order_date, customer_name, ship_address, notes) VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_INSERT_ORDER_DETAILS="INSERT INTO order_item(order_id, item_name, unit_price, discount, quantity) values(?, ?, ?, ?, ?)";
    
}
