insert into users (userid, role, user_name, userpassword) values
    (NEXTVAL('user_seq'), 0, 'Administrator', 'adminpass'),
    (NEXTVAL('user_seq'), 1,  'UserOne', 'user1234'),
    (NEXTVAL('user_seq'), 1, 'UserTwo', 'user2345');

insert into suppliers values
    (NEXTVAL('supplier_seq'), 'Spain','Ikea'),
    (NEXTVAL('supplier_seq'), 'Spain','Conforama');

insert into price_reductions values
    (NEXTVAL('pricereduction_seq'),50, '2021-02-04', '2021-02-07'),
    (NEXTVAL('pricereduction_seq'),60, '2021-03-04', '2021-03-07'),
    (NEXTVAL('pricereduction_seq'),70, '2021-04-04', '2021-04-07');

insert into products values
    (NEXTVAL('product_seq'),06003, 3, '2020-01-22', 'Wooden wardrobe', 600, 0, 3),
    (NEXTVAL('product_seq'),06004, 3, '2020-01-22', 'Mattress', 512, 0, 2),
    (NEXTVAL('product_seq'),06005, 0, '2020-01-28', 'Individual bed', 512, 1, 3);



