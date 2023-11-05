drop database if exists tea_factory_system;
create database if not exists tea_factory_system;

use tea_factory_system;

-- auto-generated definition
create table user
(
    userid   varchar(10) not null
        primary key,
    username varchar(10) not null,
    contac   varchar(20) null,
    password varchar(8)  null,
    constraint username
        unique (username)
);

-- auto-generated definition
create table login_details
(
    userid  varchar(10) not null,
    intime  double      null,
    outtime double      null,
    date    date        null,
    constraint foreign key (userid) references user (userid)
        ON UPDATE CASCADE ON DELETE CASCADE

);

-- auto-generated definition
create table employee
(
    user_id       varchar(10) not null,
    employeeid    varchar(10) not null
        primary key,
    employee_name varchar(20) null,
    address       varchar(30) null,
    contac        varchar(20) null,
    constraint foreign key (user_id) references user (userid)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- auto-generated definition
create table customer
(
    customer_id varchar(10) not null
        primary key,
    emp_id      varchar(10) not null,
    cus_name    varchar(20) null,
    cus_address varchar(30) null,
    cus_cantac  varchar(20) null,
    constraint foreign key (emp_id) references employee (employeeid)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- auto-generated definition
create table orders
(
    order_id     varchar(10) not null
        primary key,
    cus_id       varchar(10) not null,
    o_catogary   varchar(20) null,
    o_date       date        null,
    descrreption varchar(20) null,
    constraint foreign key (cus_id) references customer (customer_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- auto-generated definition
create table packet_stoke
(
    packet_id    varchar(10) not null
        primary key,
    s_catogary   varchar(20) null,
    s_weigth     double      null,
    s_expiredate date        null
);

-- auto-generated definition
create table order_detailse
(
    o_id varchar(10) not null,
    p_id varchar(10) not null,
    constraint foreign key (o_id) references orders (order_id)  ON UPDATE CASCADE ON DELETE CASCADE,
    constraint foreign key (p_id) references packet_stoke (packet_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- auto-generated definition
create table leaves_stoke
(
    leaves_s_id    varchar(10) not null
        primary key,
    l_weigth       double      null,
    l_suppli_date  date        null,
    l_s_expiredate date        null
);

-- auto-generated definition
create table stoke_detailse
(
    p_stoke_id varchar(10) not null,
    l_id       varchar(10) not null,
    constraint foreign key (l_id) references leaves_stoke (leaves_s_id)  ON UPDATE CASCADE ON DELETE CASCADE,
    constraint foreign key (p_stoke_id) references packet_stoke (packet_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- auto-generated definition
create table supplier
(
    supplier_id varchar(10) not null
        primary key,
    sup_name    varchar(20) null,
    sup_address varchar(30) null,
    sup_contac  varchar(20) null
);

-- auto-generated definition
create table supplier_orders
(
    s_orders_id      varchar(10) not null
        primary key,
    sup_id           varchar(10) not null,
    sup_date         date        null,
    sup_stoke_weigth double      null,
    constraint foreign key (sup_id) references supplier (supplier_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- auto-generated definition
create table suppling_detailse
(
    s_ord_id   varchar(10) not null,
    l_stoke_id varchar(10) not null,
    constraint foreign key (l_stoke_id) references leaves_stoke (leaves_s_id)  ON UPDATE CASCADE ON DELETE CASCADE,
    constraint foreign key (s_ord_id) references supplier_orders (s_orders_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- auto-generated definition
create table salory
(
    salory_id varchar(10) not null
        primary key,
    e_id      varchar(10) not null,
    Date      date        null,
    s_count   double      null,
    constraint foreign key (e_id) references employee (employeeid)
        ON UPDATE CASCADE ON DELETE CASCADE
);

