INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion A', 'Materiales dentro de la clasificacion A');
INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion B', 'Materiales dentro de la clasificacion B');

INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto A', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, formula, aplicaciones, metodo_sintesis, articulo, titulo_tesis, url_tesis, fecha, id_clasificacion) VALUES('Compuesto B', 'Clasificacion B', 'CB', 'Las aplicaciones son...', 'Los metodos de sintesis son', 'Articulo asass', 'Tesis A', 'url', NOW(), 2);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto C', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto D', 'Clasificacion A', 1);
 

INSERT INTO expedientes (nombre_seccion, descripcion_seccion, fecha, mof_id) VALUES('Prueba Electrica', 'Se realizaron pruebas electricas al compuesto 1', NOW(), 1);
INSERT INTO expedientes (nombre_seccion, descripcion_seccion, fecha, mof_id) VALUES('Prueba Electrica', 'Se realizaron pruebas electricas al compuesto 2', NOW(), 2);
INSERT INTO expedientes (nombre_seccion, descripcion_seccion, fecha, mof_id) VALUES('Prueba Espectroscopica', 'Se realizaron pruebas espectroscopicas al compuesto 2', NOW(), 2);

INSERT INTO pruebas (nombre_prueba, imagen, expediente_id) VALUES('Figura 1','', 2);
INSERT INTO pruebas (nombre_prueba, imagen, expediente_id) VALUES('Figura 2','', 2);
INSERT INTO pruebas (nombre_prueba, imagen, expediente_id) VALUES('Figura 3','', 2);
INSERT INTO pruebas (nombre_prueba, imagen, expediente_id) VALUES('Figura 4','', 3);
INSERT INTO pruebas (nombre_prueba, imagen, expediente_id) VALUES('Figura 5','', 3);

INSERT INTO roles_usuarios (nombre_rol, descripcion_rol) VALUES('Usuario Registrado', 'Usuarios sin permisos.');
INSERT INTO roles_usuarios (nombre_rol, descripcion_rol) VALUES('Investigador', 'Usuarios con permisos.');
INSERT INTO roles_usuarios (nombre_rol, descripcion_rol) VALUES('Director de Tesis', 'Director de Tesis');
INSERT INTO roles_usuarios (nombre_rol, descripcion_rol) VALUES('Personal Autorizado', 'Personal que puede asignar permisos');

insert into users (nombre,apellido_paterno, apellido_materno, email, director1, director2, pwd, enabled,id_roles) values('Franco','Ake','Alan','al.fr.ake@gmail.com','Director 1','Director 2','$2a$10$bGLQnSbjwAW1uV1whnRZbeoejiFRUIBB0zZN.bo/X6DyqEzXGnyQi',TRUE,1);
insert into users (nombre,apellido_paterno, apellido_materno, email, director1, director2, pwd, enabled,id_roles) values('Perez','Perez','Juan','alfake1@hotmail.com','Director 1','Director 2','$2a$10$Qhe.IJwcADkpUNRd3uiuau1BDfvDnBDlGktODUW39yF3SL8Yw8p9a',TRUE,4);

INSERT INTO authorities (user_id, authority, id_usuario) VALUES(1, 'ROLE_USER1', 1);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(2, 'ROLE_USER2', 2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(2, 'ROLE_ADMIN', 2);