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
PRIMARY KEY (codi_empr),
UNIQUE (nomb_empr));

CREATE TABLE seguimiento(
codi_segu int NOT NULL AUTO_INCREMENT, 
codi_empr int  NOT NULL,
fech_segu date NOT NULL,
fech_reco date,
desc_segu varchar(150) NOT NULL,
esta_segu int, 
padr_segu int,
PRIMARY KEY (codi_segu));

alter table seguimiento add foreign key (codi_empr) references empresa (codi_empr);
alter table seguimiento add foreign key (padr_segu) references seguimiento (codi_segu);

CREATE TABLE donacion(
codi_dona int NOT NULL AUTO_INCREMENT, 
codi_empr int  NOT NULL,
plaz_dona int NOT NULL, 
cant_cuot int NOT NULL, 
mont_tot numeric(15,2) NOT NULL,
mont_pend numeric(15,2) NOT NULL,
esta_dona int NOT NULL,
fech_dona date NOT NULL,
PRIMARY KEY (codi_dona));

alter table donacion add foreign key (codi_empr) references empresa (codi_empr);

CREATE TABLE  solicitud_beca (
codi_soli_beca int NOT NULL AUTO_INCREMENT, 
codi_empr int,
carn_alum varchar(10) NOT NULL,
fech_soli_beca date NOT NULL, 
esta_soli_beca int NOT NULL,
PRIMARY KEY (codi_soli_beca));

alter table solicitud_beca add foreign key (codi_empr) references empresa (codi_empr);

CREATE TABLE  tipo_documento (
codi_tipo_docu int NOT NULL AUTO_INCREMENT, 
nomb_tipo_docu varchar(50) NOT NULL,
desc_tipo_docu varchar(100),
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
PRIMARY KEY (codi_tipo_esta),
UNIQUE (nomb_tipo_esta));


CREATE TABLE  tipo_beca (
codi_tipo_beca int NOT NULL AUTO_INCREMENT, 
nomb_tipo_beca varchar(50) NOT NULL,
desc_tipo_beca  numeric(15,2),
PRIMARY KEY (codi_tipo_beca),
UNIQUE (nomb_tipo_beca));

CREATE TABLE  beca (
codi_beca int NOT NULL AUTO_INCREMENT,
codi_soli_beca int NOT NULL,
codi_tipo_beca int NOT NULL,
codi_tipo_esta int NOT NULL,
mens_alum numeric(15,2) NOT NULL,
cant_mese int, 
fech_inic date, 
fech_baja date, 
PRIMARY KEY (codi_beca));

alter table beca add foreign key (codi_tipo_beca) references tipo_beca (codi_tipo_beca);
alter table beca add foreign key (codi_soli_beca) references solicitud_beca (codi_soli_beca);
alter table beca add foreign key (codi_tipo_esta) references tipo_estado (codi_tipo_esta);

CREATE TABLE transaccion (
codi_tran int NOT NULL AUTO_INCREMENT,
codi_dona int NOT NULL,
codi_beca int,
mont_tran numeric(15,2), 
fech_entr_tran date, 
fech_conf_tran date, 
fech_sali_tran date, 
tipo_tran int,
PRIMARY KEY (codi_tran));

alter table transaccion add foreign key (codi_dona) references donacion (codi_dona);
alter table transaccion add foreign key (codi_beca) references beca (codi_beca);

CREATE TABLE  detalle (
codi_deta int NOT NULL AUTO_INCREMENT, 
codi_tran int NOT NULL,
fech_deta date,
mont_alum numeric(15,2),
PRIMARY KEY (codi_deta));

alter table detalle add foreign key (codi_tran) references transaccion (codi_tran);

CREATE TABLE seccion (
codi_secc int NOT NULL AUTO_INCREMENT, 
nomb_secc varchar(40)  NOT NULL,
desc_secc varchar(255),
indi_secc varchar(255),
PRIMARY KEY (codi_secc),
UNIQUE (nomb_secc));

CREATE TABLE  pregunta (
codi_preg int NOT NULL AUTO_INCREMENT, 
codi_secc int  NOT NULL,
codi_preg_supe int,
desc_preg varchar(255),
PRIMARY KEY (codi_preg));

alter table pregunta add foreign key (codi_secc) references seccion (codi_secc);

CREATE TABLE   estructura (
codi_estr int NOT NULL AUTO_INCREMENT, 
tipo_estr varchar(25) NOT NULL,
PRIMARY KEY (codi_estr));


CREATE TABLE opcion (
codi_opci int NOT NULL AUTO_INCREMENT, 
codi_preg int  NOT NULL,
codi_estr int NOT NULL,
titu_opci varchar(255) NOT NULL,
desc_opci varchar(50) NOT NULL,
PRIMARY KEY (codi_opci));

alter table opcion add foreign key (codi_preg) references pregunta (codi_preg);
alter table opcion add foreign key (codi_estr) references estructura (codi_estr);

CREATE TABLE  respuesta (
codi_resp int NOT NULL AUTO_INCREMENT, 
codi_opci int  NOT NULL,
codi_soli_beca int NOT NULL,
desc_opci varchar(255) NOT NULL,
PRIMARY KEY (codi_resp));

alter table respuesta add foreign key (codi_opci) references opcion (codi_opci);
alter table respuesta add foreign key (codi_soli_beca) references solicitud_beca (codi_soli_beca);

CREATE TABLE `AppLog` (
  `DATED` varchar(50) NOT NULL,
  `LOGGER` varchar(50) NOT NULL,
  `LEVEL` varchar(10) NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;