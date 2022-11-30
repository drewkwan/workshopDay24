drop database if exists newstore;

create database newstore;

use newstore;

create table orders (
	-- primary key
    order_id varchar(8) not null,
    order_date date not null,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal (2, 2) default 0.05,
     
    
    primary key(order_id)
);


create table order_item (
    -- primary key 

    item_id int auto_increment not null,
    order_id varchar(8) not null,
    item_name varchar(64),
    unit_price decimal (3,2),
    discount decimal (3,2) default 1.00,
    quantity int default '1',
    
    primary key(item_id),
    constraint fk_order_id
	foreign key (order_id)
    references orders(order_id)

);