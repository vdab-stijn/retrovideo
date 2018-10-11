set names utf8mb4;
set charset utf8mb4;

drop database if exists retrovideo;
CREATE DATABASE retrovideo charset utf8mb4;
use retrovideo;

CREATE TABLE genres (
  id int NOT NULL AUTO_INCREMENT primary key,
  naam varchar(20) NOT NULL unique
);

INSERT INTO genres(naam) VALUES
 ('Aktiefilm'),
 ('Avontuur'),
 ('Cowboyfilm'),
 ('Erotiek'),
 ('Griezel'),
 ('Humor'),
 ('Kinderfilm'),
 ('Oorlog'),
 ('Piratenfilm'),
 ('Science fiction'),
 ('Sentimenteel'),
 ('Speelfilm'),
 ('Thriller');

CREATE TABLE films (
  id int NOT NULL AUTO_INCREMENT primary key,
  genreid int NOT NULL,
  titel varchar(30) NOT NULL,
  voorraad int unsigned NOT NULL,
  gereserveerd int unsigned NOT NULL,
  prijs decimal(9,2) NOT NULL,
  KEY Titel (titel),
  CONSTRAINT filmsGenres FOREIGN KEY (genreid) REFERENCES genres(id)
);

INSERT INTO films(genreid,titel,voorraad,gereserveerd,prijs) VALUES
 (2,'Raiders of the lost ark',3,0,3.5),
 (7,'Harry Potter',3,0,3),
 (11,'Love story',1,0,3),
 (4,'Two moon junction',8,0,3.5),
 (6,'Police academy',3,0,3.5),
 (3,'Once upon a time in the west',2,0,3),
 (2,'In de ban van de ring',3,0,3.5),
 (7,'Babe',2,0,3),
 (2,'Zorro',2,0,3.5),
 (6,'Hector',2,0,3.5),
 (3,'High noon',4,0,3),
 (9,'Captain blood',2,0,3),
 (2,'The last emperor',3,0,3.5),
 (12,'The deer hunter',9,0,3.5),
 (6,'The gods must be crazy',6,0,3.5),
 (13,'Silent night, deadly night',4,0,3),
 (13,'The gangs of new york',4,0,3),
 (13,'Kickboxer',4,0,3),
 (2,'Batman',12,0,3.5),
 (11,'Cramer vs Cramer',1,0,3),
 (11,'Titanic',5,0,3),
 (3,'El gringo',5,0,3),
 (11,'The graduate',3,0,3),
 (13,'The omen',5,0,3),
 (4,'Sex,lies and videotapes',0,0,3.5),
 (1,'Chicago',7,0,3),
 (7,'De smurfen',6,0,3),
 (13,'First blood',3,0,3),
 (4,'Her alibi',5,0,3.5),
 (8,'De langste dag',3,0,3.5),
 (8,'The guns of navarone',2,0,3.5),
 (2,'The revenge of jaws',6,0,3.5),
 (13,'Lock up',3,0,3),
 (5,'Hellraiser',5,0,3),
 (5,'The exorcist',2,0,3),
 (13,'Road house',5,0,3),
 (11,'Matador',5,0,3),
 (8,'Missing in action',4,0,3.5),
 (2,'Licence to kill',6,0,3.5);


CREATE TABLE klanten(
  id int NOT NULL AUTO_INCREMENT primary key,
  familienaam varchar(30) NOT NULL,
  voornaam varchar(20) NOT NULL,
  straatNummer varchar(30) NOT NULL,
  postcode varchar(10) NOT NULL,
  gemeente varchar(30) NOT NULL,
  KEY Familienaam (familienaam)
);


INSERT INTO klanten(familienaam,voornaam,straatNummer,postcode,gemeente) VALUES
 ('Heiremans','Inge','Koekelbergstraat 32','9330','Dendermonde'),
 ('Goessens','Joris','Diepeweg 1','9000','Gent'),
 ('Van delsen','Lode','Kouterstraat 10','9263','Bavegem'),
 ('Van den berghe','Piet','Melkerijstraat 34','8900','Ieper'),
 ('Van den bosche','Christel','Heirbaan 34','9311','Impe'),
 ('Verbiest','Karen','Dorpsstraat 35','9000','Gent'),
 ('Boelens','Luc','Gravenstraat 23','9402','Meerbeke'),
 ('Verplancken','Mia','Kempeland 3','9200','Wetteren'),
 ('Meert','Sabine','Oosthoek 23','9230','Melle'),
 ('Boelens','Kristel','Koekoekstraat 2','9000','Gent'),
 ('De clerq','Hilde','Molenstraat 23','9140','Zele'),
 ('De coninck','Philippe','Stationstraat 23','9402','Meerbeke'),
 ('Cousaert','Nathalie','Stationstraat 234','9300','Aalst'),
 ('De coninck','Kathleen','Vogelzang 34','9000','Gent'),
 ('Lorez','Veronique','Beverhoekstraat 23','9200','Wetteren'),
 ('Heyman','Lieve','Dendermondse stwg 112','9010','Gentbrugge'),
 ('Huysman','Ann','Noordlaan 12','9300','Aalst'),
 ('Gevaert','Jan','Wortegemstraat 3','1890','Opwijk'),
 ('Nijs','Pascal','Lindestraat 23','9200','Wetteren'),
 ('Coppens','Roland','Dorp 6','9411','Erondegem'),
 ('Gysels','Rita','Kasteeldreef 45','9000','Gent'),
 ('Janssens','Etienne','Blikstraat 21','9370','Lebbeke'),
 ('Goeman','Christine','Eikelstraat 345','9160','Hamme'),
 ('Van de sompel','Luc','Voermanstraat 45','9170','Waasmunster'),
 ('Van de poele','Trees','Stationstraat 11','9000','Gent'),
 ('Matthijs','Paul','Sticheldreef 37','9140','Zele'),
 ('Lefever','Hendrik','Lijsterstraat 2','9290','Berlare'),
 ('Lenaerds','Marc','Dragonderwegel 23','9281','Overmere'),
 ('Lampens','Lieven','Drapstraat 45','9282','Uitbergen'),
 ('Verpoest','Steven','Dammenlaan','9200','Dendermonde');

CREATE TABLE reservaties(
  klantid int NOT NULL,
  filmid int NOT NULL,
  reservatie datetime NOT NULL,
  PRIMARY KEY (klantid,filmid,reservatie) USING BTREE,
  CONSTRAINT reservatiesFilms FOREIGN KEY (filmid) REFERENCES films(id),
  CONSTRAINT reservatiesKlanten FOREIGN KEY (klantid) REFERENCES klanten(id)
);

create user if not exists cursist identified by 'cursist';
grant insert,select,update on films to cursist;
grant insert,select on genres to cursist;
grant insert,select on klanten to cursist;
grant insert,select on reservaties to cursist;