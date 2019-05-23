delete from UsuarioEntity;
delete from CoordinadorEntity;
delete from AdministradorEntity;
delete from AnfitrionEntity;
delete from ComentarioEntity;
delete from CalificacionEntity;
delete from ActividadEntity;

insert into USUARIOENTITY VALUES (1,1234,'usuarioComentario');

insert into COMENTARIOENTITY (titulo, texto, fecha, id, actividad_id) values ('Networked', 'velit vivamus vel nulla eget eros', '2019-03-21 00:00:00', 100,2);
insert into COMENTARIOENTITY (titulo, texto, fecha, id) values ('model', 'iaculis congue vivamus metus arcu adipiscing molestie', '2019-03-21 00:00:00', 2);
insert into COMENTARIOENTITY (titulo, texto, fecha, id) values ('Team-oriented', 'aenean lectus pellentesque eget nunc donec quis', '2019-05-01 00:00:00', 3);
insert into COMENTARIOENTITY (titulo, texto, fecha, id) values ('responsive', 'lobortis ligula sit amet eleifend', '2019-04-29 00:00:00', 4);

insert into CalificacionEntity (id, calificacion, mensaje) values (200, 3, 'Nada mal.');
insert into CalificacionEntity (id, calificacion, mensaje) values (201, 1, 'Un fallo total.');
insert into CalificacionEntity (id, calificacion, mensaje) values (202, 5, 'El mejor de todos.');
insert into CalificacionEntity (id, calificacion, mensaje, grupo_id) values (203, 5, 'El mejor de todos.', 1);
insert into ComentarioEntity (titulo, texto, fecha, id, calificaciones_id) values ('Networked', 'velit vivamus vel nulla eget eros', '2019-03-21 00:00:00', 101, 201);

Select * from ActividadEntity;
Select * from ComentarioEntity;
Select * from UsuarioEntity;
Select * from CalificacionEntity; 

SELECT ID, FECHA, TEXTO, TITULO, ACTIVIDAD_ID, AUTOR_ID, CALIFICACIONES_ID FROM COMENTARIOENTITY;


