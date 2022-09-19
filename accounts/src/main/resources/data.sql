insert into cliente (direccion, edad, genero, identificacion, nombre, telefono, cliente_id, contrasenia, estado, id) 
values ('Quito', 30, 'Fememino', '1234', 'Veronica', '09912345', 1, '1234', 'A', 1);

insert into cuenta (codigo_cliente, estado, numero_cuenta, saldo_inicial, tipo_cuenta, id) values (1, 'A', '11111', 0.00, 'AHORROS', 1);

insert into movimiento (codigo_cuenta, fecha, saldo, tipo_movimiento, valor, id) values (1, CURRENT_TIMESTAMP(), 20, 1, 20, 1);
insert into movimiento (codigo_cuenta, fecha, saldo, tipo_movimiento, valor, id) values (1, CURRENT_TIMESTAMP(), 50, 1, 30, 2);
insert into movimiento (codigo_cuenta, fecha, saldo, tipo_movimiento, valor, id) values (1, CURRENT_TIMESTAMP(), 45, 0, 5, 3);

update cuenta set saldo_inicial=45 where id=1;

insert into cuenta (codigo_cliente, estado, numero_cuenta, saldo_inicial, tipo_cuenta, id) values (1, 'A', '22222', 0.00, 'AHORROS', 2);
insert into movimiento (codigo_cuenta, fecha, saldo, tipo_movimiento, valor, id) values (2, CURRENT_TIMESTAMP(),70, 1, 70, 4);
insert into movimiento (codigo_cuenta, fecha, saldo, tipo_movimiento, valor, id) values (2, CURRENT_TIMESTAMP(), 40, 0, 30, 5);
insert into movimiento (codigo_cuenta, fecha, saldo, tipo_movimiento, valor, id) values (2, CURRENT_TIMESTAMP(), 30, 0, 10, 6);

update cuenta set saldo_inicial=30 where id=2;