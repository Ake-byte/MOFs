INSERT INTO roles_usuarios (nombre_rol, descripcion_rol, nombre_bd) VALUES('Usuario Registrado', 'Usuarios sin permisos.', 'ROLE_USER1');
INSERT INTO roles_usuarios (nombre_rol, descripcion_rol, nombre_bd) VALUES('Investigador', 'Usuarios con permisos.', 'ROLE_USER2');
INSERT INTO roles_usuarios (nombre_rol, descripcion_rol, nombre_bd) VALUES('Director de Tesis', 'Director de Tesis', 'ROLE_USER3');
INSERT INTO roles_usuarios (nombre_rol, descripcion_rol, nombre_bd) VALUES('Personal Autorizado', 'Personal que puede asignar permisos', 'ROLE_ADMIN');



--insert into users (nombre,apellido_paterno, apellido_materno, email, director1, director2, pwd, enabled,id_roles) values('Alan','Franco','Ake','al.fr.ake@gmail.com','Director 1','Director 2','$2a$10$bGLQnSbjwAW1uV1whnRZbeoejiFRUIBB0zZN.bo/X6DyqEzXGnyQi',TRUE,2);
--insert into users (nombre,apellido_paterno, apellido_materno, email, director1, director2, pwd, enabled,id_roles) values('Juan','Perez','Perez','alfake1@hotmail.com','Director 1','Director 2','$2a$10$Qhe.IJwcADkpUNRd3uiuau1BDfvDnBDlGktODUW39yF3SL8Yw8p9a',TRUE,4);

--INSERT INTO authorities (user_id, authority, id_usuario) VALUES(1, 'ROLE_USER2', 1);
--INSERT INTO authorities (user_id, authority, id_usuario) VALUES(1, 'ROLE_USER2', 1);
--INSERT INTO authorities (user_id, authority, id_usuario) VALUES(1, 'ROLE_ADMIN', 1);

insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. José Manuel', 'Ávila', 'Santos', 'm.avilawinter@gmail.com', '$2a$10$NVQDMv6N0Wa5o7mpR5vNrOeMEFsOwSakhKxDY/CYY8CNf/qZAF8pK', TRUE,4);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(1, 'ROLE_ADMIN', 1);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(1, 'ROLE_USER2', 1);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. José Antonio', 'Irán', 'Díaz Góngora', 'joseantoniodg27@outlook.com', '$2a$10$he2lfq.lCd7huiFWG3/mweXTwSpSTOQu2o37o5tK2xglOy3FW0HKS', TRUE,3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(2, 'ROLE_USER3', 2);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dra. Ana Adela', 'Lemus', 'Santana',	'adelale@gmail.com', '$2a$10$KGERx3pZ9m9DGcnBIeWyhOyMRDeZFJlY3CGGxYM8hTI/vYdDoe2.K', TRUE,4);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(3, 'ROLE_ADMIN', 3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(3, 'ROLE_USER2', 3);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dra. Zenaida Carolina', 'Leyva', 'Inzunza',	'carolina.leyva.inz@gmail.com', '$2a$10$W2uBXGvyJzY16dnqgKE7HOP7QUAdiVmZH.xyD7nxw0E2AfQvj38au', TRUE,4);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(4, 'ROLE_ADMIN', 4);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(4, 'ROLE_USER2', 4);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. Edilso Francisco', 'Reguera', 'Ruíz',	'edilso.reguera@gmail.com', '$2a$10$ikZAVJKUx5tyspO0hxAxZu99X2J1L/yPcEAu.jAkLSIpx5m4tJu2C', TRUE,4);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(5, 'ROLE_ADMIN', 5);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(5, 'ROLE_USER2', 5);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. Carlos Israel', 'Aguirre', 'Vélez',	'c.aguirre.velez@gmail.com', '$2a$10$xaaruwV8x92ds7aPZ93fou9EipWxEBVwloSRK7HwvXlEeCDkpYwt6', TRUE,4);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(6, 'ROLE_ADMIN', 6);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(6, 'ROLE_USER2', 6);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. Oscar Fernando', 'Odio', 'Chacón',	'odiochacon@gmail.com', '$2a$10$LQHQcb3kjb0j.tN.OsRiXO.vQtCIDZ5dBXc9TzxKLBJt1.F.ZIE1u', TRUE,3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(7, 'ROLE_USER3', 7);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dra. Donají', 'Velasco', 'Arias',	'donajivela@gmail.com', '$2a$10$D0p.bs1woGU1/CKALxlbcuk7hQxJMN.gsiZd0GFv9zTsREmiQI0pG',TRUE,3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(8, 'ROLE_USER3', 8);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. Benjamín', 'Portales', 'Martínez',	'ben_portales_mtz@hotmail.com', '$2a$10$fYWKzKVLNXNh7SAksp8.T.IqaU6NlIH0iv3cGzVKt35hhGQe74vnS',TRUE,3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(9, 'ROLE_USER3', 9);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. Próspero', 'Acevedo', 'Peña',	'prosperoster@gmail.com', '$2a$10$40dIdlL1GuOS1kjzd8OSNe7aIICeaaWtevCoUofcp8JihrwYJIjzG',TRUE,3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(10, 'ROLE_USER3', 10);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dra. Marlene', 'González', 'Montiel',	'maglerne@gmail.com', '$2a$10$JySokYqxxZ7XAymaYSgI/ej4W7NMEn0x.eMdODVrgQ0DafFHN1UNO',TRUE,3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(11, 'ROLE_USER3', 11);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Dr. Jesús', 'Vega', 'Moreno',	'jwega01@gmail.com', '$2a$10$ew.jUGEt/W42D2nFBwSF..PaBkYOkUGWuGamGX6QUjBHGbxqfFiTm',TRUE,3);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(12, 'ROLE_USER3', 12);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Edgar Ramón', 'Hernández', 'Martínez',	'edgar70fm@gmail.com', '$2a$10$hhq0CuTPkDoxxbFRvXMQLuBxKTjiip98PhsO3E/Hx66T1vFM4TIWe',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(13, 'ROLE_USER2', 13);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Guadalupe', 'Ramírez', 'Campos',	'lupita.folmt@gmail.com', '$2a$10$D/2qw9OOxfg2i/oeJwOkLOoglB9ohd8SbmhGyJtp0igNI3M4TgQAy',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(14, 'ROLE_USER2', 14);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Víctor Manuel', 'Melgarejo', 'Cázares',	'vmelgarejocaz@gmail.com', '$2a$10$jolsV/dR6BhNVVQ6FV/sPOWa4cHFfevLZQJN5N5WUgVVxtipyqS5S',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(15, 'ROLE_USER2', 15);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Grisel', 'Hernández', 'Cortés',	'ghciqi@gmail.com', '$2a$10$/mUc4/VMQXENpO1t9yMHWe9/mCsHwhOygC1SqIPYL6QG9CKGYPeje',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(16, 'ROLE_USER2', 16);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Rayko', 'Amaro', 'Hernández', 'ramah920425@gmail.com', '$2a$10$eHXHUCQ8lhB4JJCeIEbvFeWWGT1aNh6etQLSq7U4dYBQnjqtSqJMy',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(17, 'ROLE_USER2', 17);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Alfonso Israel', 'Guzmán', 'Montaño', 'alfonso.Guzman21@outlook.com', '$2a$10$E/GuCsjS20ZfZNg7JJ.dRuv4Vcp9DEJlNvdBMHWYZbyWvvt6hjw56',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(18, 'ROLE_USER2', 18);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Josué', 'Lozada', 'Coronel', 'lozadajosue97@gmail.com', '$2a$10$ZhNLouyPvpAcZywRYrlO.O5jdQlHRHJ.jxDzJhvsefB/Bx4tW6rOi',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(19, 'ROLE_USER2', 19);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Karla Scanda', 'Raymundo', 'Silva',	'karlascanda@gmail.com', '$2a$10$zioBoiO7xlXfv59wqEsGeO674ATAIg/EL1YsurZAL3OBpdvrDDw1i',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(20, 'ROLE_USER2', 20);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Camilo', 'Serrano', 'Fuentes',	'camilosf.92@gmail.com', '$2a$10$DKrGizoxOMGu84/NIpq02e6ktGtajQ7JdPfz6uL8jUxjHyIhxWyDm',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(21, 'ROLE_USER2', 21);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Ricardo Alejandro', 'Terrero', 'Figueredo','ricardoterrero92@gmail.com', '$2a$10$lseVlUdPWiu1v1brnPIurubowz5zXh2jUf1pGJ/z24WOjXxFEvqxy',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(22, 'ROLE_USER2', 22);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Yamil Eusebio', 'Divó', 'Matos','yamileusebio@gmail.com','$2a$10$c80VCGlpBvIF9RlM4y8qWOqGZTPnsl1ccCxSO5xs63Bb16NRK4etK',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(23, 'ROLE_USER2', 23);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('David Iván', 'Villalva', 'Mejorada','idavid121.dv@gmail.com', '$2a$10$MCGlelu5znUBO8sA8mLE0OcnIEcnO/c13KOdYf832qABhF0OlFKK6',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(24, 'ROLE_USER2', 24);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Diana Catalina', 'Verduzco','Flores','diana.dcvf@gmail.com', '$2a$10$fEnf/onAVsrY.d9OjtLbJOU0cCZXMl2GiShyMWsb/Rr.dwaxIe64S',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(25, 'ROLE_USER2', 25);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Luis Alberto', 'Díaz', 'Paneque','ladpaneque@gmail.com', '$2a$10$w/Qo6YgO6e4cZP9oPq4FHeaGTIsSOY0lUnC3bvYqhkwtGWwiu/aMy',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(26, 'ROLE_USER2', 26);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Luis Ángel', 'Tavera', 'Carrasco','ltaveraac@gmail.com', '$2a$10$WsQTgJXJYJ.wo9pVyRpUIu9kLSgw2uK91y1LjRpQpFMN7.7INzuS6',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(27, 'ROLE_USER2', 27);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Pilar Amparo', 'Morgado', 'Aucar','pmorgadoaucar1995@gmail.com', '$2a$10$IMpwlhMWdft5A21nFkFns.bYK5ESvPj01MMWoCd8O.DWTHK6F9Pga',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(28, 'ROLE_USER2', 28);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Roxana', 'Paz', 'García','roxanapaz396@gmail.com', '$2a$10$Qbo6RSrxneCOhmF7O5lQpepV.ZMBQpVc0HWsEGpy6dL4cJn8n13UG',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(29, 'ROLE_USER2', 29);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Francisco Daniel', 'Ramírez', 'Apodaca','apodaca9207@gmail.com', '$2a$10$8/n4xz.4radWshhjSMh.huS.RQfbaf04I1exIn/LNOifFav/ijdLe',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(30, 'ROLE_USER2', 30);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Omar', 'Reséndiz', 'Hernández','ommarrehe@gmail.com', '$2a$10$XQMSxigv.GMg/fieUVV.tubVH7NDZkyy.YQ2et0MUQX.wT16Qbtfu',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(31, 'ROLE_USER2', 31);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Edgar Obed', 'Pérez', 'Reyes','edgarp656@gmail.com', '$2a$10$xsku9.4WGNC8LCkuZV0Br.7r/deP.Aw5P23.6N.etAqCmZvitP7EC',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(32, 'ROLE_USER2', 32);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Erik Javier', 'del Angel', 'Gómez',	'erikdelangelgomez@gmail.com', '$2a$10$4wUurl37.dUAnJgzE2f13OpjxhXEYD7THFKA9LsInHreVTNUQ.otu',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(33, 'ROLE_USER2', 33);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Hebert Rodrigo','Mojica','Molina','rodrigo1993mx@gmail.com','$2a$10$NKPFzDeEHT28XwjHQKmDOuur5u3DGDkM3TYIjp3Grml4kbkhLnXJO',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(34, 'ROLE_USER2', 34);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Lucero','Torres','López','lucerolu7@gmail.com','$2a$10$8fp8pIZQ6lpMq0Ll0NNWsu6.p7/uyXXBy6ySi2k8VOMUDE3tgn8VW',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(35, 'ROLE_USER2', 35);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Heraclio','Heredia','Ureta','heracliohu@gmail.com','$2a$10$udLvwI2rhKNi2DFe/KUS9.HuhQGmH7EYlvjusi5GeRw1CexpHNlN6',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(36, 'ROLE_USER2', 36);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Yeisy Clara','López','Conde','ylopezconde@gmail.com','$2a$10$jltWZZP5rl0HjGImFH/XJ.3y.r4EqyG5yAADtzGWZiZiHZ4RJIEIi',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(37, 'ROLE_USER2', 37);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Paula Montserrat','Crespo','Barrera','moncbarrera@gmail.com','$2a$10$ULyEGz8WzjRQkHbF86uwaeAtSX/XMRchqIDRCyGKpCcdAsgUJLzwu',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(38, 'ROLE_USER2', 38);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Yosuan','Ávila','García','yosuan.ag87@gmail.com','$2a$10$p0CFB5troI2tRle2cIjGSeEUi1EjOd4cye7bDPDcSEiVasnd9NA72',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(39, 'ROLE_USER2', 39);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled,id_roles) values('Juvencio','Vázquez','Samperio','juvencio_ipn@hotmail.com','$2a$10$LU1mTnaiMNINwZ4zDyvhYeQljVTFFLW5bsRBCR6u9b7TPrAEtclIO',TRUE,2);
INSERT INTO authorities (user_id, authority, id_usuario) VALUES(40, 'ROLE_USER2', 40); 

INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion A', 'Materiales dentro de la clasificacion A');
INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion B', 'Materiales dentro de la clasificacion B');

INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto A', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, formula, aplicaciones, metodo_sintesis, articulo, titulo_tesis, url_tesis, fecha, id_clasificacion) VALUES('Compuesto B', 'Clasificacion B', 'CB', 'Las aplicaciones son...', 'Los metodos de sintesis son', 'Articulo asass', 'Tesis A', 'url', NOW(), 2);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto C', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto D', 'Clasificacion A', 1);
 

--INSERT INTO expedientes (fecha, mof_id, users_id, nombre_usuario) VALUES(NOW(), 1,1, 'Franco Ake Alan');
--INSERT INTO expedientes (fecha, mof_id, users_id, nombre_usuario) VALUES(NOW(), 2,1, 'Franco Ake Alan');
--INSERT INTO expedientes (fecha, mof_id, users_id, nombre_usuario) VALUES(NOW(), 1,1,'Perez Perez Juan');

--INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion, expediente_id) VALUES('Pruebas electricas', 'Se aplicaron pruebas electricas al material', 1);
--INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion, expediente_id) VALUES('Pruebas magenticas', 'Se aplicaron pruebas electricas al material', 1);
--INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion, expediente_id) VALUES('Pruebas espectroscopicas', 'Se aplicaron pruebas espectroscopicas al material', 1);
--INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion, expediente_id) VALUES('Pruebas termicas', 'Se aplicaron pruebas termicas al material', 1);

--INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion) VALUES('PRUEBAS ELECTRICAS', 'Usuarios sin permisos.', 'ROLE_USER1');



