INSERT INTO `exhibition` (`description`, `exhibition_date`)
VALUES
	('Galerie im Studentwerk Osnabrück', '1993'),
	('Fachhochschule Osnabrück', '1993'),
	('Galerie im Fenster, Universität Osnabrück', '1993/94'),
	('Musikakademie Dümmer-See, Hüde', '1994'),
	('4.Leeraner Kunstmeile 1994, Leer', '1994'),
	('Galerie Khoury, Bremen', '1994'),
	('Kulturbahnhof Binolen, Märkischer Kreis', '1998'),
	('Kreissparkasse Grafschaft Bentheim, Uelsen', '2000'),
	('Remarque, die Stadt und der Frieden/ Ausstellung zum Remarque-Jahr 2008, Kunst-Quartier, Osnabrück (G)\nKontraste - (mit Michael Olsen), Kunst-Quartier, Osnabrück (G)', '2008'),
	('Kompositionen – Malerei, Collage, planen + bauen, Osnabrück (E) Art & Dialog, im Rahmen von „Zeitgleich – Zeitzeichen 2010“ des Bundesverbandes Bildender Künstlerinnen und Künstler, Kunst-Quartier, Osnabrück (G)', '2010'),
	('Sigma, planen + bauen, Osnabrück (G)', '2011'),
	('Cutouts und Farbkompositionen - Kunst-Quartier, Osnabrück (E)', '2013');


INSERT INTO `painting` (`id`, `breite`, `datei`, `entstehungsjahr`, `hoehe`, `name`, `technik`, `hauptkategorie`, `unterkategorie`)
VALUES
	(3, 125, 'GrossesStillleben1998.png', 1998, 105, 'Großes Stillleben', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(4, 125, 'TischDesMalers1998.png', 1998, 105, 'Tisch des Malers', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(5, 32, 'DreiAepfel2000.png', 2000, 25, 'Drei Äpfel', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(6, 32, 'DreiNektarinenMitGlas2000.png', 2000, 25, 'Drei Nektarinen mit Glas', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(7, 75, 'DreiGrannysVorWeidenkorb2000.png', 2000, 65, 'Drei Grannys vor Weidenkorb', 'Tempera/Öl auf Leinen', 'representational', NULL),
	(8, 75, 'FruechteNuesseGlas2004.png', 2004, 65, 'Früchte, Nüsse und Glas', 'Tempera/Öl auf Leinen', 'representational', NULL);


INSERT INTO `blogpost` (`id`, `post_date`, `post_text`, `headline`)
VALUES
	(1, '2017-10-15 00:00:00', 'Die Collage wird hier als Auseinandersetzung mit Form, Linie und Fläche anhand farbiger Papiere unterschiedlicher Grammatur verstanden. Intendiert ist eine spannende Komposition, die es mit wenigen Mitteln umzusetzen gilt. Diese Technik von interagierenden Farbformen entwickelte bereits Henry Matisse und nutzte ihr Ausdruckspotential in seinen Papiers Collés. Burandt zeigt die Variante einer auf eine Sequenz hin angelegten Ausdrucksform, die unterschiedliche kompositionelle Fragestellungen auf einem einheitlichen quadratischen Format durchspielt. In der Zusammenschau treten die Arbeiten in immer wieder neue Bezüge.', 'Kompositionen auf Papier – Collagen und Scherenschnitte'),
	(2, '2017-10-15 00:00:00', 'Erinnert man Tizians Großes Stillleben […] im Louvre, benachbart zu Veroneses Hochzeit zu Kana, lässt sich der Reichtum malerischer Differenzierung von Weiß ermessen. Denn mit den Farbtönen des Umfeldes werden Wahrnehmung und Ausdruck eines Farbtons entscheidend beeinflusst, wie Josef Albers in seiner Schrift Interaction of Color darlegt. Durch das Studium der Alten Meister wird die Sensibilität u.a. für die Wirkung von Weiß geweckt und in den gegenständlichen Gemälden Burandts wird diese Auseinandersetzung sichtbar. Impulsgeber für die gegenständlichen Bilder war der Besuch der Retrospektive des französischen Malers Jean Simeon Baptiste Chardin in Düsseldorf 2000.', 'Weiß ist nicht gleich Weiß – gegenständliche Arbeiten'),
	(3, '2017-10-15 00:00:00', 'Mit der Zeichentechnik des suchenden Strichs legen sich Sichtspuren auf das Blatt, die sukzessive den Gegenstand plastisch hervortreten lassen. Der weitgehend gleichbleibende Strich des Filzschreibers notiert skizzenartig und zugleich distanziert die formalen Eigenschaften des Gegenstandes und etabliert ihn im Raum. Form und Stofflichkeit des Objekts kristallisieren sich heraus, das Wesen wird zeichnerisch erkundet, gestalterisch herausgehoben und spiegelt schließlich die Sichtweise des Zeichners und Betrachters wider. Beispielhaft für diese Zeichentechnik steht Alberto Giacometti mit der Zeichnung Annette von 1960. Die vorliegenden Zeichnungen sind dem Skizzenbuch 2014 entnommen.', 'Zeichnerisch die Welt sehen');
