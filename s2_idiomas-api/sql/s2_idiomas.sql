delete from ComentarioEntity;

insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('Networked', 'velit vivamus vel nulla eget eros', '2019-03-21 00:00:00', 'C', 1);
insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('model', 'iaculis congue vivamus metus arcu adipiscing molestie', '2019-03-21 00:00:00', 'C', 2);
insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('Team-oriented', 'aenean lectus pellentesque eget nunc donec quis', '2019-03-21 00:00:00', 'C', 3);
insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('responsive', 'lobortis ligula sit amet eleifend', '2019-03-21 00:00:00', 'C', 4);

delete from ActividadEntity;

Select * from ActividadEntity;
Select * from ComentarioEntity;
SELECT ID, FECHA, TEXTO, TITULO, ACTIVIDAD_ID, AUTOR_ID, CALIFICACIONES_ID FROM COMENTARIOENTITY;