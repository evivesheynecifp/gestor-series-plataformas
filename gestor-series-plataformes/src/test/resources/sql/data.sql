-- PLATAFORMES

INSERT INTO plataforma (id, nom) VALUES (1, 'Netflix');
INSERT INTO plataforma (id, nom) VALUES (2, 'HBO Max');
INSERT INTO plataforma (id, nom) VALUES (3, 'Amazon Prime');
INSERT INTO plataforma (id, nom) VALUES (4, 'Disney Plus');
INSERT INTO plataforma (id, nom) VALUES (5, 'Apple TV');
INSERT INTO plataforma (id, nom) VALUES (6, 'Movistar Plus');
INSERT INTO plataforma (id, nom) VALUES (7, 'Rakuten TV');
INSERT INTO plataforma (id, nom) VALUES (8, 'SkyShowtime');
INSERT INTO plataforma (id, nom) VALUES (9, 'Filmin');
INSERT INTO plataforma (id, nom) VALUES (10, 'Crunchyroll');

-- SERIES

INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (1, 'Dark', 'SciFi', 1);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (2, 'Stranger Things', 'SciFi', 1);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (3, 'Breaking Bad', 'Drama', 1);

INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (4, 'The Last of Us', 'Drama', 2);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (5, 'Euphoria', 'Drama', 2);

INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (6, 'The Boys', 'Action', 3);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (7, 'Reacher', 'Action', 3);

INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (8, 'Loki', 'Fantasy', 4);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (9, 'The Mandalorian', 'SciFi', 4);

INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (10, 'Foundation', 'SciFi', 5);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (11, 'Ted Lasso', 'Comedy', 5);

INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (12, 'La Unidad', 'Thriller', 6);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (13, 'Gomorrah', 'Crime', 7);

INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (14, 'Yellowstone', 'Drama', 8);
INSERT INTO serie (id, titol, genere, plataforma_id) VALUES (15, 'Attack Titan', 'Anime', 10);
