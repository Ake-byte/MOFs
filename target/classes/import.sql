INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion A', 'Materiales dentro de la clasificacion A')
INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion B', 'Materiales dentro de la clasificacion B')

INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto A', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, formula, aplicaciones, metodo_sintesis, articulo, titulo_tesis, url_tesis, fecha, id_clasificacion) VALUES('Compuesto B', 'Clasificacion B', 'CB', 'Las aplicaciones son...', 'Los metodos de sintesis son', 'Articulo asass', 'Tesis A', 'url', NOW(), 2)
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto C', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto D', 'Clasificacion A', 1);
 

INSERT INTO expedientes (nombre_prueba, descripcion_prueba, fecha, imagen, mof_id) VALUES('Prueba Electrica', 'Se realizaron pruebas electricas al compuesto 1', NOW(), '', 1)
INSERT INTO expedientes (nombre_prueba, descripcion_prueba, fecha, imagen, mof_id) VALUES('Prueba Electrica', 'Se realizaron pruebas electricas al compuesto 2', NOW(), '', 2)
INSERT INTO expedientes (nombre_prueba, descripcion_prueba, fecha, imagen, mof_id) VALUES('Prueba Espectroscopica', 'Se realizaron pruebas espectroscopicas al compuesto 2', NOW(), '', 2)

