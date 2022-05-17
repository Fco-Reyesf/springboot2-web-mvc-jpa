/* lista de clientes */

INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (1, 'nombre1', 'apellido1', 'mail1@mail.com', '2018-07-19', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at , foto) VALUES (2, 'nombre2', 'apellido2', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (3, 'nombre3', 'apellido3', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (4, 'nombre4', 'apellido4', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (5, 'nombre5', 'apellido5', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (6, 'nombre6', 'apellido6', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (7, 'nombre7', 'apellido7', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (8, 'nombre8', 'apellido8', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (9, 'nombre9', 'apellido9', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (10, 'nombre10', 'apellido10', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (11, 'nombre11', 'apellido11', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (12, 'nombre12', 'apellido12', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (13, 'nombre13', 'apellido13', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (14, 'nombre14', 'apellido14', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (15, 'nombre15', 'apellido15', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (16, 'nombre16', 'apellido16', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (17, 'nombre17', 'apellido17', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (18, 'nombre18', 'apellido18', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (19, 'nombre19', 'apellido19', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (20, 'nombre20', 'apellido20', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (21, 'nombre21', 'apellido21', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (22, 'nombre22', 'apellido22', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (23, 'nombre23', 'apellido22', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (24, 'nombre24', 'apellido24', 'mail2@mail.com', '2019-08-20', '');
INSERT INTO clientes (id, nombre, apellido, mail, create_at, foto) VALUES (25, 'nombre25', 'apellido25', 'mail2@mail.com', '2019-08-20', '');

/* lista de productos */

INSERT INTO productos (nombre, precio, create_at) VALUES ('pro1', 1111, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('pro2', 2222, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('pro3', 3333, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('pro4', 4444, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('pro5', 5555, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('pro6', 6666, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('pro7', 7777, NOW());

/* lista facturas */

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('factura', NULL, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('facturaDos', 'algua cosa', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (3, 2, 6);