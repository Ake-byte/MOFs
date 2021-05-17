INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion A', 'Materiales dentro de la clasificacion A')
INSERT INTO clasificacionmof (nombre_clasificacion, descripcion_clasificacion) VALUES('Clasificacion B', 'Materiales dentro de la clasificacion B')

INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto A', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, formula, aplicaciones, metodo_sintesis, articulo, titulo_tesis, url_tesis, fecha, id_clasificacion) VALUES('Compuesto B', 'Clasificacion B', 'CB', 'Las aplicaciones son...', 'Los metodos de sintesis son', 'Articulo asass', 'Tesis A', 'url', NOW(), 2)
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto C', 'Clasificacion A', 1);
INSERT INTO mof (nombre_compuesto, nombre_clasificacion, id_clasificacion) VALUES('Compuesto D', 'Clasificacion A', 1);
 

INSERT INTO expedientes (nombre_expediente, fecha, mof_id) VALUES('Expediente 1 del compuesto B', NOW(), 2)

INSERT INTO detalle_expediente (nombre_prueba, descripcion_prueba, expediente_id) VALUES('Prueba espectroscopica', 'Se le realizaron pruebas espectroscopicas al MOF B', 1)
INSERT INTO detalle_expediente (nombre_prueba, descripcion_prueba, expediente_id) VALUES('Prueba electrica', 'Se le realizaron pruebas electricas al MOF B', 1)
INSERT INTO detalle_expediente (nombre_prueba, descripcion_prueba, expediente_id) VALUES('Prueba magneticas', 'Se le realizaron pruebas magneticas al MOF B', 1)
INSERT INTO detalle_expediente (nombre_prueba, descripcion_prueba, expediente_id) VALUES('Prueba termicas', 'Se le realizaron pruebas termicas al MOF B', 1)

INSERT INTO expedientes (nombre_expediente, fecha, mof_id) VALUES('Expediente 2 del compuesto B', NOW(), 2)

INSERT INTO detalle_expediente (nombre_prueba, descripcion_prueba, expediente_id) VALUES('Prueba espectroscopica', 'Se le realizaron pruebas espectroscopicas al MOF B', 2)
INSERT INTO detalle_expediente (nombre_prueba, descripcion_prueba, expediente_id) VALUES('Prueba electrica', 'Se le realizaron pruebas electricas al MOF B', 2)
