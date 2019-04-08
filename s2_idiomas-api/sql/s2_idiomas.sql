delete from ComentarioEntity;

insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('demand-driven', 'lobortis ligula sit amet eleifend pede', '2019-03-21 00:00:00', 'C', 1);
insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('matrices', 'quam', '2019-03-21 00:00:00', 'C', 2);
insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('Fundamental', 'nulla pede ullamcorper augue a', '2019-03-21 00:00:00', 'C', 3);
insert into COMENTARIOENTITY (titulo, texto, fecha, typeofcomentario, id) values ('emulation', 'purus sit amet', '2019-03-21 00:00:00', 'C', 4);


select * from COMENTARIOENTITY;
SELECT ID, typeofcomentario, FECHA, TEXTO, TITULO, AUTOR_ID, CALIFICACIONES_ID, ACTIVIDAD_ID FROM COMENTARIOENTITY