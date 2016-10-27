DROP Database IF EXISTS sistemas_pilet;
CREATE DATABASE sistemas_pilet;
use sistemas_pilet;

CREATE TABLE empresa(
codi_empr int NOT NULL AUTO_INCREMENT, 
nomb_empr varchar(100) NOT NULL,
dire_empr varchar(255) NOT NULL,
emai_empr varchar(70) NOT NULL,
enca_empr varchar(70) NOT NULL,
fech_empr date NOT NULL,
esta_empr int,
PRIMARY KEY (codi_empr),
UNIQUE (nomb_empr));


/* reca_tipo_dona es para decir si el campo esta destinado para ser utilizado como reacudación
 si no es para recaidación se insertara una F de y si es para recaudación se insertara una V 
*/
  
CREATE TABLE tipo_donacion(
codi_tipo_dona int NOT NULL AUTO_INCREMENT,
nomb_tipo_dona varchar(50) not null, 
desc_tipo_dona varchar(500),
reca_tipo_dona char(1), 
esta_dona int,
PRIMARY KEY (codi_tipo_dona)
);

CREATE TABLE donacion(
codi_dona int NOT NULL AUTO_INCREMENT, 
codi_empr int  NOT NULL,
plaz_dona int NOT NULL, 
cant_cuot numeric(15,2) NOT NULL, 
mont_tot numeric(15,2) NOT NULL,
mont_pend numeric(15,2),

fech_dona date NOT NULL,
esta_dona int NOT NULL,
codi_tipo_dona int,
PRIMARY KEY (codi_dona));

alter table donacion add foreign key (codi_empr) references empresa (codi_empr);
alter table donacion add foreign key (codi_tipo_dona) references tipo_donacion (codi_tipo_dona);


CREATE TABLE grado(
codi_grad int NOT NULL AUTO_INCREMENT, 
nomb_grad varchar(50) NOT NULL, 
mens_grad numeric(15,2) NOT NULL,
esta_grad int,
PRIMARY KEY(codi_grad)
);

CREATE TABLE  solicitud_beca (
codi_soli_beca int NOT NULL AUTO_INCREMENT, 
codi_empr int,
codi_grad int NOT NULL, 
carn_alum varchar(10) NOT NULL,
nomb_alum varchar(100) NOT NULL,
fech_soli_beca date NOT NULL, 
esta_soli_beca int NOT NULL,
PRIMARY KEY (codi_soli_beca));

alter table solicitud_beca add foreign key (codi_empr) references empresa (codi_empr);
alter table solicitud_beca add foreign key (codi_grad) references grado (codi_grad);

CREATE TABLE tipo_seguimiento(
codi_tipo_segui int NOT NULL AUTO_INCREMENT,
nomb_tipo_segui varchar(50) NOT NULL,
desc_tipo_segui varchar(100), 
esta_tipo_segui int,
PRIMARY KEY(codi_tipo_segui));


CREATE TABLE seguimiento(
codi_segu int NOT NULL AUTO_INCREMENT, 
codi_empr int ,
codi_soli_beca int , 
codi_tipo_segui int, 
fech_segu date NOT NULL,
fech_reco date,
nomb_segu varchar(50) not null,
desc_segu varchar(500) not null,
esta_segu int, 
padr_segu int,
PRIMARY KEY (codi_segu));

alter table seguimiento add foreign key (codi_empr) references empresa (codi_empr);
alter table seguimiento add foreign key (codi_soli_beca) references solicitud_beca (codi_soli_beca);
alter table seguimiento add foreign key (codi_tipo_segui) references tipo_seguimiento (codi_tipo_segui);
alter table seguimiento add foreign key (padr_segu) references seguimiento (codi_segu);



CREATE TABLE  tipo_documento (
codi_tipo_docu int NOT NULL AUTO_INCREMENT, 
nomb_tipo_docu varchar(50) NOT NULL,
desc_tipo_docu varchar(100),
esta_tipo_docu int,
PRIMARY KEY (codi_tipo_docu),
UNIQUE (nomb_tipo_docu));

CREATE TABLE documento (
codi_docu int NOT NULL AUTO_INCREMENT, 
codi_tipo_docu int NOT NULL,
codi_soli_beca int,
codi_empr int,
ruta_docu varchar(255) NOT NULL, 
fech_docu  date NOT NULL,
esta_docu int NOT NULL,
PRIMARY KEY (codi_docu));

alter table documento add foreign key (codi_tipo_docu) references tipo_documento (codi_tipo_docu);
alter table documento add foreign key (codi_soli_beca) references solicitud_beca (codi_soli_beca);
alter table documento add foreign key (codi_empr) references empresa (codi_empr);

CREATE TABLE tipo_estado (
codi_tipo_esta int NOT NULL AUTO_INCREMENT, 
nomb_tipo_esta varchar(50) NOT NULL,
desc_tipo_esta varchar(100),
esta_tipo_esta int,
PRIMARY KEY (codi_tipo_esta),
UNIQUE (nomb_tipo_esta));


CREATE TABLE  tipo_beca (
codi_tipo_beca int NOT NULL AUTO_INCREMENT, 
nomb_tipo_beca varchar(50) NOT NULL,
desc_tipo_beca  numeric(15,2),
esta_tipo_beca int,
tipo_tipo_beca int,
PRIMARY KEY (codi_tipo_beca),
UNIQUE (nomb_tipo_beca));


CREATE TABLE tipo_retiro (
codi_reti int NOT NULL AUTO_INCREMENT,
nomb_reti varchar(50), 
desc_reti varchar(500),
esta_reti int NOT NULL,
PRIMARY KEY(codi_reti), 
UNIQUE(nomb_reti)
);

CREATE TABLE  beca (
codi_beca int NOT NULL AUTO_INCREMENT,
codi_soli_beca int NOT NULL,
codi_tipo_esta int NOT NULL,
codi_reti int,
reti_beca varchar(500),
fech_inic date, 
fech_baja date, 
PRIMARY KEY (codi_beca));

alter table beca add foreign key (codi_soli_beca) references solicitud_beca (codi_soli_beca);
alter table beca add foreign key (codi_tipo_esta) references tipo_estado (codi_tipo_esta);
alter table beca add foreign key (codi_reti) references tipo_retiro (codi_reti);

CREATE TABLE detalle_beca (
codi_deta_beca int NOT NULL AUTO_INCREMENT,
codi_beca int NOT NULL,
codi_tipo_beca int NOT NULL,
cant_mese int NOT NULL,
PRIMARY KEY (codi_deta_beca));

alter table detalle_beca add foreign key (codi_beca) references beca (codi_beca);
alter table detalle_beca add foreign key (codi_tipo_beca) references tipo_beca (codi_tipo_beca);

CREATE TABLE transaccion (
codi_tran int NOT NULL AUTO_INCREMENT,
codi_dona int NOT NULL,
codi_deta_beca int,
mont_tran numeric(15,2), 
fech_tran date, 
mont_tota numeric(15,2),
tipo_tran int,
esta_tran int,
PRIMARY KEY (codi_tran));

alter table transaccion add foreign key (codi_dona) references donacion (codi_dona);
alter table transaccion add foreign key (codi_deta_beca) references detalle_beca (codi_deta_beca);

CREATE TABLE  detalle (
codi_deta int NOT NULL AUTO_INCREMENT, 
codi_tran int NOT NULL,
fech_deta date,
mont_alum numeric(15,2),
esta_deta int,
PRIMARY KEY (codi_deta));

alter table detalle add foreign key (codi_tran) references transaccion (codi_tran);

CREATE TABLE seccion (
codi_secc int NOT NULL AUTO_INCREMENT, 
nomb_secc varchar(40)  NOT NULL,
desc_secc varchar(255),
indi_secc varchar(255),
esta_secc int,
PRIMARY KEY (codi_secc),
UNIQUE (nomb_secc));

CREATE TABLE  pregunta (
codi_preg int NOT NULL AUTO_INCREMENT, 
codi_secc int  NOT NULL,
codi_preg_supe int,
desc_preg varchar(255),
esta_preg int,
PRIMARY KEY (codi_preg));

alter table pregunta add foreign key (codi_secc) references seccion (codi_secc);

CREATE TABLE   estructura (
codi_estr int NOT NULL AUTO_INCREMENT, 
tipo_estr varchar(25) NOT NULL,
esta_estr  int,
PRIMARY KEY (codi_estr));


CREATE TABLE opcion (
codi_opci int NOT NULL AUTO_INCREMENT, 
codi_preg int  NOT NULL,
codi_estr int NOT NULL,
titu_opci varchar(255) NOT NULL,
desc_opci varchar(50) NOT NULL,
esta_opci int,
PRIMARY KEY (codi_opci));

alter table opcion add foreign key (codi_preg) references pregunta (codi_preg);
alter table opcion add foreign key (codi_estr) references estructura (codi_estr);

CREATE TABLE  respuesta (
codi_resp int NOT NULL AUTO_INCREMENT, 
codi_opci int  NOT NULL,
codi_soli_beca int NOT NULL,
desc_opci varchar(255) NOT NULL,
esta_resp int, 
PRIMARY KEY (codi_resp));

alter table respuesta add foreign key (codi_opci) references opcion (codi_opci);
alter table respuesta add foreign key (codi_soli_beca) references solicitud_beca (codi_soli_beca);

CREATE TABLE `AppLog` (
  `DATED` varchar(50) NOT NULL,
  `LOGGER` varchar(50) NOT NULL,
  `LEVEL` varchar(10) NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;