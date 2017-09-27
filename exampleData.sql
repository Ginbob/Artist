INSERT INTO `exhibition` (`description`, `exhibition_date`)
VALUES
('Galerie im Studentwerk Osnabrück', '1993'),
('Fachhochschule Osnabrück', '1993'),
('Galerie im Fenster, Universität Osnabrück', '1993/94'),
('Musikakademie Dümmer-See, Hüde', '1994'),
('4.Leeraner Kunstmeile 1994", Leer', '1994'),
('Galerie Khoury, Bremen', '1994');


INSERT INTO `painting` (`id`, `breite`, `datei`, `entstehungsjahr`, `hoehe`, `name`, `technik`, `hauptkategorie`, `unterkategorie`)
VALUES
	(3, 125, 'GrossesStillleben1998.png', 1998, 105, 'Großes Stillleben', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(4, 125, 'TischDesMalers1998.png', 1998, 105, 'Tisch des Malers', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(5, 32, 'DreiAepfel2000.png', 2000, 25, 'Drei Äpfel', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(6, 32, 'DreiNektarinenMitGlas2000.png', 2000, 25, 'Drei Nektarinen mit Glas', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(7, 75, 'DreiGrannysVorWeidenkorb2000.png', 2000, 65, 'Drei Grannys vor Weidenkorb', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(8, 75, 'FruechteNuesseGlas2004.png', 2004, 65, 'Früchte, Nüsse und Glas', 'Tempera/Öl auf Leinen', 'representational', NULL);
