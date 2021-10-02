--USUARIO AUTORIZADO

insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. José Manuel', 'Ávila', 'Santos', 'm.avilawinter@gmail.com', '$2a$10$NVQDMv6N0Wa5o7mpR5vNrOeMEFsOwSakhKxDY/CYY8CNf/qZAF8pK', TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dra. Ana Adela', 'Lemus', 'Santana',	'adelale@gmail.com', '$2a$10$KGERx3pZ9m9DGcnBIeWyhOyMRDeZFJlY3CGGxYM8hTI/vYdDoe2.K', TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dra. Zenaida Carolina', 'Leyva', 'Inzunza',	'carolina.leyva.inz@gmail.com', '$2a$10$W2uBXGvyJzY16dnqgKE7HOP7QUAdiVmZH.xyD7nxw0E2AfQvj38au', TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Edilso Francisco', 'Reguera', 'Ruíz',	'edilso.reguera@gmail.com', '$2a$10$ikZAVJKUx5tyspO0hxAxZu99X2J1L/yPcEAu.jAkLSIpx5m4tJu2C', TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Carlos Israel', 'Aguirre', 'Vélez',	'c.aguirre.velez@gmail.com', '$2a$10$xaaruwV8x92ds7aPZ93fou9EipWxEBVwloSRK7HwvXlEeCDkpYwt6', TRUE);

INSERT INTO roles (user_id, authority, authority_name) VALUES(1, 'ROLE_ADMIN', 'Personal Autorizado');
INSERT INTO roles (user_id, authority, authority_name) VALUES(2, 'ROLE_ADMIN', 'Personal Autorizado');
INSERT INTO roles (user_id, authority, authority_name) VALUES(3, 'ROLE_ADMIN', 'Personal Autorizado');
INSERT INTO roles (user_id, authority, authority_name) VALUES(4, 'ROLE_ADMIN', 'Personal Autorizado');
INSERT INTO roles (user_id, authority, authority_name) VALUES(5, 'ROLE_ADMIN', 'Personal Autorizado');

--DIRECTOR DE TESIS

insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. José Antonio Irán', 'Díaz', 'Góngora', 'joseantoniodg27@outlook.com', '$2a$10$he2lfq.lCd7huiFWG3/mweXTwSpSTOQu2o37o5tK2xglOy3FW0HKS', TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Oscar Fernando', 'Odio', 'Chacón',	'odiochacon@gmail.com', '$2a$10$LQHQcb3kjb0j.tN.OsRiXO.vQtCIDZ5dBXc9TzxKLBJt1.F.ZIE1u', TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dra. Donají', 'Velasco', 'Arias',	'donajivela@gmail.com', '$2a$10$D0p.bs1woGU1/CKALxlbcuk7hQxJMN.gsiZd0GFv9zTsREmiQI0pG',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Benjamín', 'Portales', 'Martínez',	'ben_portales_mtz@hotmail.com', '$2a$10$fYWKzKVLNXNh7SAksp8.T.IqaU6NlIH0iv3cGzVKt35hhGQe74vnS',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Próspero', 'Acevedo', 'Peña',	'prosperoster@gmail.com', '$2a$10$40dIdlL1GuOS1kjzd8OSNe7aIICeaaWtevCoUofcp8JihrwYJIjzG',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dra. Marlene', 'González', 'Montiel',	'maglerne@gmail.com', '$2a$10$JySokYqxxZ7XAymaYSgI/ej4W7NMEn0x.eMdODVrgQ0DafFHN1UNO',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Dr. Jesús', 'Vega', 'Moreno',	'jwega01@gmail.com', '$2a$10$ew.jUGEt/W42D2nFBwSF..PaBkYOkUGWuGamGX6QUjBHGbxqfFiTm',TRUE);

INSERT INTO roles (user_id, authority, authority_name) VALUES(6, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(7, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(8, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(9, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(10, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(11, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(12, 'ROLE_USER2', 'Investigador');

INSERT INTO directores (role_id) VALUES(6);
INSERT INTO directores (role_id) VALUES(7);
INSERT INTO directores (role_id) VALUES(8);
INSERT INTO directores (role_id) VALUES(9);
INSERT INTO directores (role_id) VALUES(10);
INSERT INTO directores (role_id) VALUES(11);
INSERT INTO directores (role_id) VALUES(12);

INSERT INTO investigadores (role_id, director1, director2) VALUES(6, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(7, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(8, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(9, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(10, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(11, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(12, '', '');

--ALUMNOS

insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Edgar Ramón', 'Hernández', 'Martínez',	'edgar70fm@gmail.com', '$2a$10$hhq0CuTPkDoxxbFRvXMQLuBxKTjiip98PhsO3E/Hx66T1vFM4TIWe',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Guadalupe', 'Ramírez', 'Campos',	'lupita.folmt@gmail.com', '$2a$10$D/2qw9OOxfg2i/oeJwOkLOoglB9ohd8SbmhGyJtp0igNI3M4TgQAy',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Víctor Manuel', 'Melgarejo', 'Cázares',	'vmelgarejocaz@gmail.com', '$2a$10$jolsV/dR6BhNVVQ6FV/sPOWa4cHFfevLZQJN5N5WUgVVxtipyqS5S',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Grisel', 'Hernández', 'Cortés',	'ghciqi@gmail.com', '$2a$10$/mUc4/VMQXENpO1t9yMHWe9/mCsHwhOygC1SqIPYL6QG9CKGYPeje',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Rayko', 'Amaro', 'Hernández', 'ramah920425@gmail.com', '$2a$10$eHXHUCQ8lhB4JJCeIEbvFeWWGT1aNh6etQLSq7U4dYBQnjqtSqJMy',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Alfonso Israel', 'Guzmán', 'Montaño', 'alfonso.Guzman21@outlook.com', '$2a$10$E/GuCsjS20ZfZNg7JJ.dRuv4Vcp9DEJlNvdBMHWYZbyWvvt6hjw56',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Josué', 'Lozada', 'Coronel', 'lozadajosue97@gmail.com', '$2a$10$ZhNLouyPvpAcZywRYrlO.O5jdQlHRHJ.jxDzJhvsefB/Bx4tW6rOi',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Karla Scanda', 'Raymundo', 'Silva',	'karlascanda@gmail.com', '$2a$10$zioBoiO7xlXfv59wqEsGeO674ATAIg/EL1YsurZAL3OBpdvrDDw1i',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Camilo', 'Serrano', 'Fuentes',	'camilosf.92@gmail.com', '$2a$10$DKrGizoxOMGu84/NIpq02e6ktGtajQ7JdPfz6uL8jUxjHyIhxWyDm',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Ricardo Alejandro', 'Terrero', 'Figueredo','ricardoterrero92@gmail.com', '$2a$10$lseVlUdPWiu1v1brnPIurubowz5zXh2jUf1pGJ/z24WOjXxFEvqxy',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Yamil Eusebio', 'Divó', 'Matos','yamileusebio@gmail.com','$2a$10$c80VCGlpBvIF9RlM4y8qWOqGZTPnsl1ccCxSO5xs63Bb16NRK4etK',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('David Iván', 'Villalva', 'Mejorada','idavid121.dv@gmail.com', '$2a$10$MCGlelu5znUBO8sA8mLE0OcnIEcnO/c13KOdYf832qABhF0OlFKK6',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Diana Catalina', 'Verduzco','Flores','diana.dcvf@gmail.com', '$2a$10$fEnf/onAVsrY.d9OjtLbJOU0cCZXMl2GiShyMWsb/Rr.dwaxIe64S',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Luis Alberto', 'Díaz', 'Paneque','ladpaneque@gmail.com', '$2a$10$w/Qo6YgO6e4cZP9oPq4FHeaGTIsSOY0lUnC3bvYqhkwtGWwiu/aMy',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Luis Ángel', 'Tavera', 'Carrasco','ltaveraac@gmail.com', '$2a$10$WsQTgJXJYJ.wo9pVyRpUIu9kLSgw2uK91y1LjRpQpFMN7.7INzuS6',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Pilar Amparo', 'Morgado', 'Aucar','pmorgadoaucar1995@gmail.com', '$2a$10$IMpwlhMWdft5A21nFkFns.bYK5ESvPj01MMWoCd8O.DWTHK6F9Pga',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Roxana', 'Paz', 'García','roxanapaz396@gmail.com', '$2a$10$Qbo6RSrxneCOhmF7O5lQpepV.ZMBQpVc0HWsEGpy6dL4cJn8n13UG',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Francisco Daniel', 'Ramírez', 'Apodaca','apodaca9207@gmail.com', '$2a$10$8/n4xz.4radWshhjSMh.huS.RQfbaf04I1exIn/LNOifFav/ijdLe',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Omar', 'Reséndiz', 'Hernández','ommarrehe@gmail.com', '$2a$10$XQMSxigv.GMg/fieUVV.tubVH7NDZkyy.YQ2et0MUQX.wT16Qbtfu',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Edgar Obed', 'Pérez', 'Reyes','edgarp656@gmail.com', '$2a$10$xsku9.4WGNC8LCkuZV0Br.7r/deP.Aw5P23.6N.etAqCmZvitP7EC',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Erik Javier', 'del Angel', 'Gómez',	'erikdelangelgomez@gmail.com', '$2a$10$4wUurl37.dUAnJgzE2f13OpjxhXEYD7THFKA9LsInHreVTNUQ.otu',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Hebert Rodrigo','Mojica','Molina','rodrigo1993mx@gmail.com','$2a$10$NKPFzDeEHT28XwjHQKmDOuur5u3DGDkM3TYIjp3Grml4kbkhLnXJO',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Lucero','Torres','López','lucerolu7@gmail.com','$2a$10$8fp8pIZQ6lpMq0Ll0NNWsu6.p7/uyXXBy6ySi2k8VOMUDE3tgn8VW',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Heraclio','Heredia','Ureta','heracliohu@gmail.com','$2a$10$udLvwI2rhKNi2DFe/KUS9.HuhQGmH7EYlvjusi5GeRw1CexpHNlN6',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Yeisy Clara','López','Conde','ylopezconde@gmail.com','$2a$10$jltWZZP5rl0HjGImFH/XJ.3y.r4EqyG5yAADtzGWZiZiHZ4RJIEIi',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Paula Montserrat','Crespo','Barrera','moncbarrera@gmail.com','$2a$10$ULyEGz8WzjRQkHbF86uwaeAtSX/XMRchqIDRCyGKpCcdAsgUJLzwu',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Yosuan','Ávila','García','yosuan.ag87@gmail.com','$2a$10$p0CFB5troI2tRle2cIjGSeEUi1EjOd4cye7bDPDcSEiVasnd9NA72',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Juvencio','Vázquez','Samperio','juvencio_ipn@hotmail.com','$2a$10$LU1mTnaiMNINwZ4zDyvhYeQljVTFFLW5bsRBCR6u9b7TPrAEtclIO',TRUE);
insert into users (nombre,apellido_paterno, apellido_materno, email, pwd, enabled) values('Alan','Franco','Ake','al.fr.ake@gmail.com','$2a$10$bGLQnSbjwAW1uV1whnRZbeoejiFRUIBB0zZN.bo/X6DyqEzXGnyQi',TRUE);

INSERT INTO roles (user_id, authority, authority_name) VALUES(13, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(14, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(15, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(16, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(17, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(18, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(19, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(20, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(21, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(22, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(23, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(24, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(25, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(26, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(27, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(28, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(29, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(30, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(31, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(32, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(33, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(34, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(35, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(36, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(37, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(38, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(39, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(40, 'ROLE_USER2', 'Investigador');
INSERT INTO roles (user_id, authority, authority_name) VALUES(41, 'ROLE_USER2', 'Investigador');

INSERT INTO investigadores (role_id, director1, director2) VALUES(13, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(14, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(15, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(16, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(17, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(18, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(19, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(20, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(21, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(22, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(23, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(24, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(25, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(26, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(27, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(28, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(29, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(30, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(31, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(32, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(33, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(34, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(35, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(36, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(37, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(38, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(39, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(40, '', '');
INSERT INTO investigadores (role_id, director1, director2) VALUES(41, 'Dr. José Antonio Irán Díaz Góngora', 'Dr. Oscar Fernando Odio Chacón');

--DATOS DE PRUEBA

INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion A', 'Materiales dentro de la clasificacion A');
INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion B', 'Materiales dentro de la clasificacion B');

INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto A', 1);
INSERT INTO mof (nombre_compuesto, formula, aplicaciones, metodo_sintesis, articulo, titulo_tesis, url_tesis, fecha, id_clasificacion, investigador) VALUES('Compuesto B', 'CB', 'Las aplicaciones son...', 'Los metodos de sintesis son', 'Articulo asass', 'Tesis A', 'url', NOW(), 2, 'Alan Franco Ake');
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto B', 2);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto C', 2);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto D', 2);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto E', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto F', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto G', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto H', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto I', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto J', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto K', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto L', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto M', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto N', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto O', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto P', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto Q', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto C', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion) VALUES('Compuesto R', 1);
INSERT INTO mof (nombre_compuesto, id_clasificacion, investigador) VALUES('Compuesto S', 1, 'Alan Franco Ake');
INSERT INTO mof (nombre_compuesto, id_clasificacion, investigador) VALUES('Compuesto T', 1, 'Alan Franco Ake');


INSERT INTO expedientes (fecha, mof_id, nombre_usuario, ultimo_usuario) VALUES(NOW(), 2, 'Dr. Carlos Israel Aguirre Vélez', 'Dr. Carlos Israel Aguirre Vélez');
INSERT INTO expedientes (fecha, mof_id, nombre_usuario, ultimo_usuario) VALUES(NOW(), 2, 'Edgar Ramón Hernández Martínez', 'Edgar Ramón Hernández Martínez');
INSERT INTO expedientes (fecha, mof_id, nombre_usuario, ultimo_usuario) VALUES(NOW(), 2, 'Paula Montserrat Crespo Barrera', 'Paula Montserrat Crespo Barrera');
INSERT INTO permisos (permiso, expediente_id, user_id) VALUES(true, 1, 5);
INSERT INTO permisos (permiso, expediente_id, user_id) VALUES(true, 2, 13);
INSERT INTO permisos (permiso, expediente_id, user_id) VALUES(true, 3, 38);
INSERT INTO permisos (permiso, expediente_id, user_id) VALUES(false, 1, 41);
INSERT INTO permisos (permiso, expediente_id, user_id) VALUES(false, 2, 41);
INSERT INTO permisos (permiso, expediente_id, user_id) VALUES(false, 3, 41);
INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion, expediente_id) VALUES('Pruebas Magnéticas', 'Se realizaron pruebas magnéticas al compuesto', 1);
INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion, expediente_id) VALUES('Pruebas Eléctricas', 'Se realizaron pruebas eléctricas al compuesto', 2);
INSERT INTO secciones_expedientes (nombre_seccion, descripcion_seccion, expediente_id) VALUES('Pruebas Térmicas', 'Se realizaron pruebas térmicas al compuesto', 2);
INSERT INTO pruebas (nombre_prueba, descripcion_prueba, fecha, seccion_expediente_id, imagen) VALUES('Figura 1', 'Se realizó la siguiente prueba', NOW(), 1, '');
INSERT INTO pruebas (nombre_prueba, descripcion_prueba, fecha, seccion_expediente_id, imagen) VALUES('Figura 2', 'Se realizó la siguiente prueba', NOW(), 1, '');
INSERT INTO pruebas (nombre_prueba, descripcion_prueba, fecha, seccion_expediente_id, imagen) VALUES('Figura 3', 'Se realizó la siguiente prueba', NOW(), 1, '');
INSERT INTO pruebas (nombre_prueba, descripcion_prueba, fecha, seccion_expediente_id, imagen) VALUES('Figura 4', 'Se realizó la siguiente prueba', NOW(), 1, '');
INSERT INTO pruebas (nombre_prueba, descripcion_prueba, fecha, seccion_expediente_id, imagen) VALUES('Figura 5', 'Se realizó la siguiente prueba', NOW(), 1, '');
INSERT INTO pruebas (nombre_prueba, descripcion_prueba, fecha, seccion_expediente_id, imagen) VALUES('Figura 6', 'Se realizó la siguiente prueba', NOW(), 1, '');


