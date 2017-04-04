INSERT INTO `sh_departments` (`id`, `name`) VALUES (1, 'For Boys');
INSERT INTO `sh_departments` (`id`, `name`) VALUES (2, 'For Girls');
INSERT INTO `sh_departments` (`id`, `name`) VALUES (3, 'Stuffed Animals');

INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (1, 'Bryon Mayer', 8069, 1);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (2, 'Dr. Donnell Spinka', 4162, 3);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (3, 'Mae Zulauf', 4811, 1);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (4, 'Linnie Strosin', 3480, 1);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (5, 'Meda Ryan', 4374, 2);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (6, 'Ms. Bobby Rogahn', 9659, 2);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (7, 'Filomena Corkery II', 4254, 3);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (8, 'Juliana Gusikowski', 7092, 3);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (9, 'Adah Wilderman', 2918, 2);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (10, 'Melba Zemlak MD', 8814, 2);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (11, 'Mrs. Kameron Murray', 4312, 2);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (12, 'Una Gorczany', 3176, 2);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (13, 'Rusty Kovacek MD', 5873, 1);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (14, 'Niko Mayert', 5478, 3);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (15, 'Ms. Vickie Hackett', 1731, 1);
INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) 
	VALUES (16, 'Susana Gutmann', 3416, 2);

INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (1, 'Moore, Hartmann and Welch');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (2, 'Schimmel-OReilly');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (3, 'Schoen-Moore');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (4, 'Huel Inc');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (5, 'Volkman, Rau and Ankunding');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (6, 'Konopelski LLC');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (7, 'King-Denesik');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (8, 'Baumbach-Cronin');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (9, 'Macejkovic, Rempel and Hayes');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (10, 'Kshlerin, Heller and Bernier');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (11, 'Hoeger-Deckow');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (12, 'Heidenreich-Runte');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (13, 'Lynch, McKenzie and Moore');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (14, 'McClure-Schimmel');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (15, 'Becker LLC');
INSERT INTO `sh_suppliers` (`id`, `name`) 
	VALUES (16, 'Stracke-Dickinson');

INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (1, 'Alakazam statue', 94, 
	0, 14, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (2, 'Dodrio toy', 37, 
	0, 5, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (3, 'Ditto picture', 94, 
	0, 10, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (4, 'Meowth figurine', 85, 
	56, 1, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (5, 'Bellsprout figurine', 94, 
	0, 2, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (6, 'Lickitung toy', 91, 
	0, 4, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (7, 'Gyarados plushie', 47, 
	156, 4, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (8, 'Magikarp picture', 89, 
	0, 2, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (9, 'Ivysaur picture', 87, 
	0, 2, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (10, 'Snorlax statue', 64, 
	186, 5, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (11, 'Ivysaur figurine', 100, 
	39, 4, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (12, 'Porygon statue', 30, 
	50, 13, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (13, 'Parasect picture', 50, 
	0, 3, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (14, 'Weepinbell statue', 68, 
	35, 7, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (15, 'Dugtrio picture', 86, 
	0, 15, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (16, 'Gyarados figurine', 93, 
	0, 5, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (17, 'Raichu plushie', 24, 
	0, 2, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (18, 'Zapdos picture', 15, 
	0, 5, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (19, 'Victreebel statue', 33, 
	115, 16, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (20, 'Hitmonchan figurine', 10, 
	0, 17, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (21, 'Poliwag toy', 91, 
	0, 9, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (22, 'Farfetchd picture', 83, 
	0, 20, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (23, 'Muk statue', 13, 
	0, 12, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (24, 'Muk toy', 10, 
	42, 7, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (25, 'Golduck statue', 63, 
	0, 14, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (26, 'Raichu plushie', 57, 
	0, 13, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (27, 'Cloyster statue', 65, 
	38, 10, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (28, 'Omanyte picture', 51, 
	0, 14, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (29, 'Poliwhirl figurine', 39, 
	56, 8, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (30, 'Victreebel picture', 65, 
	32, 2, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (31, 'Clefairy figurine', 93, 
	0, 13, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (32, 'Meowth toy', 94, 
	27, 17, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (33, 'Ninetales statue', 28, 
	0, 19, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (34, 'Dragonite figurine', 17, 
	0, 13, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (35, 'Gloom plushie', 47, 
	94, 8, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (36, 'Vulpix toy', 96, 
	0, 13, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (37, 'Ditto plushie', 48, 
	0, 19, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (38, 'Weepinbell plushie', 41, 
	0, 10, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (39, 'Rhydon picture', 19, 
	0, 9, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (40, 'Metapod toy', 18, 
	135, 15, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (41, 'Pikachu picture', 19, 
	0, 10, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (42, 'Parasect statue', 20, 
	0, 4, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (43, 'Venomoth statue', 92, 
	0, 4, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (44, 'Dodrio statue', 53, 
	0, 11, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (45, 'Aerodactyl picture', 45, 
	0, 20, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (46, 'Poliwag figurine', 99, 
	0, 1, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (47, 'Wigglytuff statue', 27, 
	0, 16, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (48, 'Lickitung statue', 95, 
	0, 9, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (49, 'Haunter picture', 34, 
	0, 15, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (50, 'Cloyster picture', 85, 
	0, 12, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (51, 'Goldeen picture', 41, 
	99, 18, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (52, 'Kakuna figurine', 39, 
	0, 5, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (53, 'Horsea picture', 16, 
	46, 20, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (54, 'Golbat statue', 31, 
	87, 12, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (55, 'Jigglypuff statue', 96, 
	96, 15, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (56, 'Cloyster picture', 81, 
	25, 15, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (57, 'Grimer figurine', 28, 
	0, 1, 2);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (58, 'Seadra picture', 54, 
	55, 5, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (59, 'Scyther plushie', 37, 
	30, 4, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (60, 'Nidoqueen figurine', 16, 
	0, 4, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (61, 'Parasect plushie', 48, 
	0, 13, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (62, 'Ninetales plushie', 58, 
	45, 1, 1);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (63, 'Machoke plushie', 100, 
	32, 4, 3);
INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) 
	VALUES (64, 'Magikarp figurine', 24, 
	61, 19, 1);

INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (1, 17);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (1, 32);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (1, 54);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (2, 43);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (2, 56);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (2, 7);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (2, 58);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (2, 35);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (2, 62);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (2, 63);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (3, 60);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (3, 37);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (3, 47);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 20);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 7);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 28);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 47);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 31);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 10);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 63);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (4, 40);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 37);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 24);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 17);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 41);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 15);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 30);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 55);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (5, 51);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (6, 44);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (6, 32);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (6, 36);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (6, 31);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (6, 22);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (6, 42);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (6, 8);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (7, 29);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (7, 41);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (7, 13);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (7, 55);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (8, 38);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (8, 52);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (8, 56);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (8, 27);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (8, 23);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (8, 14);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (8, 46);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (9, 40);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (9, 11);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (9, 19);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (10, 37);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (10, 5);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (10, 42);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (10, 54);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (10, 14);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (10, 13);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (11, 1);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (11, 59);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (11, 27);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (12, 64);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (12, 12);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (12, 55);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (12, 25);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (13, 4);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (13, 64);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (13, 22);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (14, 44);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (14, 54);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (14, 51);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (14, 24);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (14, 32);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 62);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 20);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 35);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 15);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 37);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 10);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 59);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 32);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (15, 47);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 49);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 18);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 21);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 6);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 12);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 19);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 53);
INSERT INTO `sh_suppliers_items` (`supplier_id`, `item_id`) 
			VALUES (16, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (1, '2016-11-30', 12, 
		64, 56, 8);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (2, '2016-05-02', 9, 
		40, 46, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (3, '2016-10-05', 4, 
		63, 40, 9);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (4, '2016-08-17', 16, 
		49, 58, 11);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (5, '2016-11-22', 14, 
		54, 48, 12);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (6, '2016-08-12', 4, 
		40, 41, 14);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (7, '2017-03-18', 6, 
		22, 34, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (8, '2017-01-23', 12, 
		55, 54, 6);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (9, '2016-08-08', 2, 
		43, 50, 7);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (10, '2017-03-24', 6, 
		8, 56, 3);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (11, '2017-03-13', 6, 
		36, 50, 5);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (12, '2016-10-22', 2, 
		7, 48, 1);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (13, '2016-10-12', 9, 
		19, 63, 15);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (14, '2016-04-22', 7, 
		55, 40, 12);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (15, '2017-01-31', 7, 
		41, 59, 16);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (16, '2016-05-09', 2, 
		63, 45, 1);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (17, '2016-08-19', 7, 
		29, 61, 16);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (18, '2016-05-08', 9, 
		11, 48, 10);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (19, '2016-09-23', 8, 
		14, 44, 6);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (20, '2016-09-01', 16, 
		19, 59, 13);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (21, '2016-06-04', 8, 
		23, 39, 3);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (22, '2016-06-09', 10, 
		42, 42, 8);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (23, '2017-02-10', 2, 
		58, 62, 14);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (24, '2016-09-08', 8, 
		56, 35, 15);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (25, '2016-06-15', 2, 
		62, 55, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (26, '2016-04-28', 5, 
		51, 41, 16);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (27, '2016-12-04', 14, 
		24, 54, 13);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (28, '2016-06-03', 13, 
		64, 38, 3);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (29, '2016-09-16', 14, 
		54, 39, 10);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (30, '2017-03-14', 16, 
		53, 52, 12);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (31, '2016-05-10', 9, 
		40, 52, 1);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (32, '2016-08-27', 15, 
		47, 33, 6);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (33, '2016-08-05', 12, 
		12, 63, 8);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (34, '2017-02-11', 5, 
		51, 61, 14);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (35, '2016-06-19', 12, 
		55, 62, 14);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (36, '2016-10-25', 12, 
		12, 57, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (37, '2017-02-03', 5, 
		55, 42, 7);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (38, '2016-11-11', 15, 
		10, 38, 6);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (39, '2016-10-27', 15, 
		10, 35, 16);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (40, '2016-09-11', 5, 
		41, 57, 15);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (41, '2016-11-02', 2, 
		35, 48, 11);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (42, '2016-10-03', 14, 
		54, 41, 6);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (43, '2016-05-10', 2, 
		7, 64, 12);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (44, '2016-09-16', 11, 
		27, 53, 8);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (45, '2016-07-28', 11, 
		59, 35, 6);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (46, '2016-08-27', 2, 
		7, 62, 7);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (47, '2016-07-05', 9, 
		40, 56, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (48, '2016-07-19', 13, 
		64, 55, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (49, '2016-09-11', 7, 
		41, 41, 3);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (50, '2016-06-08', 8, 
		56, 34, 15);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (51, '2016-09-12', 15, 
		10, 64, 15);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (52, '2016-07-08', 1, 
		32, 37, 12);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (53, '2016-09-16', 4, 
		47, 42, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (54, '2016-08-27', 11, 
		59, 33, 10);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (55, '2016-08-14', 15, 
		10, 56, 1);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (56, '2016-12-21', 16, 
		18, 47, 5);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (57, '2016-11-06', 10, 
		14, 50, 14);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (58, '2016-09-15', 13, 
		4, 62, 12);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (59, '2016-05-13', 12, 
		64, 35, 2);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (60, '2017-03-17', 16, 
		49, 35, 13);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (61, '2016-10-01', 5, 
		30, 40, 7);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (62, '2016-08-03', 7, 
		55, 64, 8);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (63, '2016-11-27', 15, 
		35, 53, 13);
INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `item_id`, `amount`, `employee_id`) 
		VALUES (64, '2016-10-14', 16, 
		21, 37, 9);

INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (10, '2016-10-04', 9);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (10, 40, 52, 93);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (12, '2016-10-25', 5);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (12, 51, 41, 95);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (22, '2017-03-31', 14);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (22, 54, 48, 84);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (26, '2017-02-16', 13);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (26, 64, 38, 99);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (56, '2016-11-07', 2);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (56, 35, 48, 12);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (71, '2016-05-24', 12);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (71, 12, 57, 74);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (72, '2016-06-08', 2);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (72, 7, 48, 31);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (85, '2016-12-31', 2);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (85, 62, 55, 59);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (95, '2016-11-02', 14);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (95, 24, 54, 61);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (107, '2016-09-07', 5);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (107, 55, 42, 100);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (113, '2017-01-01', 15);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (113, 10, 35, 29);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (115, '2016-07-05', 8);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (115, 56, 35, 39);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (123, '2016-08-31', 15);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (123, 10, 64, 11);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (124, '2017-04-02', 11);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (124, 59, 33, 50);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (125, '2016-08-02', 8);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (125, 14, 44, 21);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (129, '2016-05-24', 2);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (129, 7, 64, 64);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (139, '2016-12-22', 12);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (139, 55, 54, 21);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (148, '2017-03-30', 2);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (148, 58, 62, 15);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (155, '2016-06-14', 12);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (155, 64, 35, 20);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (164, '2016-04-23', 2);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (164, 63, 45, 93);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (167, '2016-12-26', 1);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (167, 32, 37, 50);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (169, '2016-05-13', 9);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (169, 19, 63, 48);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (170, '2017-02-26', 5);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (170, 51, 61, 46);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (177, '2017-02-04', 9);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (177, 40, 46, 57);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (192, '2016-05-29', 16);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (192, 19, 59, 63);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (193, '2016-06-26', 11);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (193, 27, 53, 72);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (198, '2016-05-04', 2);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (198, 7, 62, 64);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (200, '2017-02-17', 7);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (200, 29, 61, 30);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (203, '2016-05-13', 15);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (203, 10, 56, 91);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (204, '2016-07-25', 5);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (204, 30, 40, 100);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (211, '2016-09-25', 4);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (211, 40, 41, 99);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (233, '2016-10-10', 16);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (233, 53, 52, 20);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (247, '2016-07-24', 15);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (247, 10, 38, 73);
INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (250, '2017-03-20', 15);
INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (250, 35, 53, 38);

UPDATE `sh_orders` SET supply_id = 177 WHERE id = 2;
UPDATE `sh_orders` SET supply_id = 22 WHERE id = 5;
UPDATE `sh_orders` SET supply_id = 211 WHERE id = 6;
UPDATE `sh_orders` SET supply_id = 139 WHERE id = 8;
UPDATE `sh_orders` SET supply_id = 72 WHERE id = 12;
UPDATE `sh_orders` SET supply_id = 169 WHERE id = 13;
UPDATE `sh_orders` SET supply_id = 164 WHERE id = 16;
UPDATE `sh_orders` SET supply_id = 200 WHERE id = 17;
UPDATE `sh_orders` SET supply_id = 169 WHERE id = 18;
UPDATE `sh_orders` SET supply_id = 125 WHERE id = 19;
UPDATE `sh_orders` SET supply_id = 192 WHERE id = 20;
UPDATE `sh_orders` SET supply_id = 148 WHERE id = 23;
UPDATE `sh_orders` SET supply_id = 115 WHERE id = 24;
UPDATE `sh_orders` SET supply_id = 85 WHERE id = 25;
UPDATE `sh_orders` SET supply_id = 12 WHERE id = 26;
UPDATE `sh_orders` SET supply_id = 95 WHERE id = 27;
UPDATE `sh_orders` SET supply_id = 26 WHERE id = 28;
UPDATE `sh_orders` SET supply_id = 233 WHERE id = 30;
UPDATE `sh_orders` SET supply_id = 10 WHERE id = 31;
UPDATE `sh_orders` SET supply_id = 170 WHERE id = 34;
UPDATE `sh_orders` SET supply_id = 71 WHERE id = 36;
UPDATE `sh_orders` SET supply_id = 107 WHERE id = 37;
UPDATE `sh_orders` SET supply_id = 247 WHERE id = 38;
UPDATE `sh_orders` SET supply_id = 113 WHERE id = 39;
UPDATE `sh_orders` SET supply_id = 56 WHERE id = 41;
UPDATE `sh_orders` SET supply_id = 95 WHERE id = 42;
UPDATE `sh_orders` SET supply_id = 129 WHERE id = 43;
UPDATE `sh_orders` SET supply_id = 193 WHERE id = 44;
UPDATE `sh_orders` SET supply_id = 198 WHERE id = 46;
UPDATE `sh_orders` SET supply_id = 123 WHERE id = 51;
UPDATE `sh_orders` SET supply_id = 167 WHERE id = 52;
UPDATE `sh_orders` SET supply_id = 124 WHERE id = 54;
UPDATE `sh_orders` SET supply_id = 203 WHERE id = 55;
UPDATE `sh_orders` SET supply_id = 26 WHERE id = 58;
UPDATE `sh_orders` SET supply_id = 155 WHERE id = 59;
UPDATE `sh_orders` SET supply_id = 204 WHERE id = 61;
UPDATE `sh_orders` SET supply_id = 250 WHERE id = 63;

INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (2, '2016-05-01', 15);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (2, 14, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (3, '2016-08-28', 12);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (3, 4, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (4, '2016-09-23', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (4, 35, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (6, '2016-12-02', 1);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (6, 12, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (8, '2016-09-17', 5);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (8, 58, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (9, '2017-03-05', 14);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (9, 10, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (10, '2016-08-13', 6);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (10, 29, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (12, '2016-06-23', 16);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (12, 7, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (15, '2016-10-07', 9);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (15, 30, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (16, '2016-08-23', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (16, 10, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (18, '2016-07-31', 13);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (18, 56, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (19, '2016-10-19', 15);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (19, 7, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (20, '2017-03-04', 7);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (20, 58, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (21, '2016-05-05', 4);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (21, 7, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (22, '2016-05-25', 3);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (22, 63, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (23, '2016-08-23', 15);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (23, 27, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (24, '2017-03-18', 10);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (24, 27, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (27, '2016-05-01', 14);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (27, 56, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (30, '2017-01-10', 5);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (30, 64, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (31, '2016-10-15', 10);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (31, 63, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (32, '2016-09-16', 7);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (32, 32, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (34, '2016-10-31', 3);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (34, 11, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (35, '2016-11-06', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (35, 40, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (36, '2016-12-22', 16);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (36, 27, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (38, '2016-10-30', 1);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (38, 12, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (40, '2016-09-08', 11);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (40, 27, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (42, '2016-06-28', 2);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (42, 56, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (44, '2016-07-20', 9);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (44, 56, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (45, '2016-08-02', 11);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (45, 53, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (47, '2017-01-07', 7);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (47, 64, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (48, '2016-06-19', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (48, 35, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (49, '2016-11-21', 5);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (49, 7, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (50, '2016-11-27', 7);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (50, 14, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (51, '2016-11-10', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (51, 62, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (52, '2017-03-13', 9);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (52, 62, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (55, '2016-11-15', 15);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (55, 62, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (57, '2016-06-17', 9);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (57, 32, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (66, '2016-09-04', 4);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (66, 27, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (67, '2016-11-30', 1);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (67, 59, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (69, '2017-01-14', 13);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (69, 24, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (70, '2017-03-05', 14);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (70, 53, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (72, '2017-03-11', 7);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (72, 29, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (73, '2017-02-15', 1);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (73, 32, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (74, '2016-05-26', 10);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (74, 64, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (80, '2017-03-25', 14);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (80, 30, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (82, '2016-12-15', 7);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (82, 12, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (83, '2016-05-11', 12);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (83, 64, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (84, '2017-03-15', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (84, 64, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (85, '2016-11-27', 11);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (85, 59, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (86, '2016-10-30', 3);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (86, 19, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (88, '2016-10-10', 5);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (88, 63, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (92, '2017-02-21', 2);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (92, 4, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (93, '2016-06-25', 9);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (93, 35, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (94, '2017-03-13', 13);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (94, 40, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (95, '2016-09-08', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (95, 62, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (97, '2016-11-21', 6);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (97, 24, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (98, '2017-03-28', 5);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (98, 63, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (99, '2016-06-25', 10);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (99, 27, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (101, '2017-03-10', 1);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (101, 14, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (102, '2016-08-10', 4);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (102, 11, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (103, '2017-01-21', 13);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (103, 63, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (104, '2016-11-08', 13);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (104, 19, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (106, '2016-07-21', 15);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (106, 56, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (107, '2016-04-25', 16);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (107, 7, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (110, '2016-08-28', 16);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (110, 24, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (111, '2016-09-26', 11);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (111, 51, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (112, '2016-04-12', 8);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (112, 29, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (114, '2016-04-24', 11);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (114, 10, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (116, '2016-05-26', 9);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (116, 11, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (117, '2016-04-18', 7);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (117, 19, 1);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (118, '2017-03-22', 1);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (118, 24, 3);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (120, '2017-02-08', 3);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (120, 64, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (121, '2016-10-26', 4);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (121, 32, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (122, '2016-06-14', 4);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (122, 7, 2);
INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (124, '2017-03-15', 4);
INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (124, 27, 1);
