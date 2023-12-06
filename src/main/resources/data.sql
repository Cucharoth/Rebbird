USE project_rebbird;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

INSERT IGNORE INTO `categoria` VALUES
(1,'Videojuegos'),
(2,'Series'),
(3,'Películas'),
(4,'Música'),
(5,'Ciencia'),
(6,'Deportes'),
(7,'Mundo'),
(8,'Política'),
(9,'Programación');

INSERT IGNORE INTO `img_perfil` VALUES
(1,'https://imgur.com/lk6Pc5q.png'),
(2,'https://imgur.com/UZSNN9v.png'),
(3,'https://imgur.com/r35wDjq.png'),
(4,'https://imgur.com/IRCwlgO.png'),
(5,'https://imgur.com/z4oIA5W.png'),
(6,'https://imgur.com/SrZxbit.png');

INSERT IGNORE INTO `usuario` (usuario_id, nombre_usuario, password_usuario, perfil_usuario_id, role, img_perfil_id, descripcion, categorias_favoritas) VALUES
(1,'Cucharoth','$2a$10$.YPrvacFlzJ3Y8n.RTsGmO4Nl26M5XfPMpTgZYMThChVw9oqR.ZDS',NULL,'USER',3,'',1),
(2,'Perl','perl',NULL,NULL,1,'',1),
(3,'Zitga','zitga',NULL,NULL,2,'',1),
(4,'Quazzix','quazzix',NULL,NULL,4,'',1),
(5,'Floweret','floweret',NULL,NULL,1,'',1),
(6,'Bøunty','bøunty',NULL,NULL,2,'',1),
(7,'Noedesha','noedesha',NULL,NULL,4,'',1),
(8,'Revculter','revculter',NULL,NULL,3,'',1),
(13,'notCucha','$2a$10$yue48d8PVsROG05WdDd4E.hDJaZDN2/twINRKUZB/aqOZb3V/R/nK',NULL,'USER',6,'',1),
(15,'defnotcucha','$2a$10$.YPrvacFlzJ3Y8n.RTsGmO4Nl26M5XfPMpTgZYMThChVw9oqR.ZDS',NULL,'ADMIN',1,'',1),
(17,'anotheruser','$2a$10$neKkPnxNA3G/NumehguLieHf1VR6FLjINLeKSIHtfwsWHQWBosETK',NULL,NULL,1,'',1),
(18,'andanotherone','$2a$10$/wg994fNvz4C/V/xSDZE/eBc9IcKZ9cUboQ6jBymmXX62BPdiBJgm',NULL,NULL,1,'',1),
(19,'galleta','$2a$10$D81OdXuVDxNY.5kVEr6kbeeZLsyNUUcxOPHRZO.5ITova064cHzke',NULL,'USER',5,'',1),
(20,'elneme','$2a$10$iqKL37CTxn7Bhp3x7An6g.GYxHMFH3ISk./27I/Rbyax/mcl6COga',NULL,'USER',1,'',1),
(21,'Marrian','Marrian',NULL,'USER',2,'',1),
(22,'Cheatdeath','Cheatdeath',NULL,'USER',3,'',1),
(23,'Vladamire','Vladamire',NULL,'USER',4,'',1),
(24,'Dripik','Dripik',NULL,'USER',2,'',1),
(25,'Breadisfunny','Breadisfunny',NULL,'USER',1,'',1),
(26,'Sarama','Sarama',NULL,'USER',3,'',1),
(31,'Caramelo','$2a$10$dyTI7P69CrAV0hBS7zta6OKie2DHXi.qDTMnFoOARFwyp0.fZpHSO',NULL,'USER',1,NULL,1),
(33,'notCaramelo','$2a$10$/6xirUtTv5cJ4I..PvdoE.YiHFYoZlRR9F1PHa2BWeF4iojrS3VXy',NULL,'USER',1,NULL,1),
(34,'Admin','$2a$10$LhSiDzaO148o5/nCrzu6oOW1jEGpQ3moYCLox9h01DyWBZjpmxkXe',NULL,'ADMIN',1,NULL,1);

INSERT IGNORE INTO `publicacion` (publicacion_id, author_id, content_publicacion, fecha_publicacion, titulo_publicacion, categoria_id, cant_comentarios, cant_reaccion) VALUES
(1,1,'Chris Metzen is back?','2022-02-23','Chris Metzen is back?',1,0,7),
(3,2,'I want to start off by making it clear, that this is regards to how the IN-GAME ignore function works. Please do not mistake this for a thread about the forums. This is specifically regarding the in-game ignore feature.\n\nCurrent ways that this system lets us down:\n\nTo ignore another player, we are required to log into each of our characters on that server and add the player to our ignore list. Similar to number 1 above, if we know this ignored player on other servers, we have to again log into all our characters on each server to ignore them. If we add a player to our ignore list, they can still see when we log in and where we are in-game via the friends list. (This one actually blows my mind) What needs to be implemented (please feel free to add any additional positive ideas in this thread):\n\nWhen we ignore another player, it should ignore their whole account on all of OUR characters. I shouldn’t need to log into every character I have or create and add the same other players to my ignore. Basically put, it should be battle.net-wide. It should make it so that the player on my ignore list can no longer see when I’m online, nor can they contact me on any realm or any character (new or old). An account-wide ignore for in-game.\n\nThe ignored player should not be able to see anything I say publically in-game. It should be a true ignore and should go both ways. I don’t want to be able to see anything they chat about either…ever.\n\nIt should also prevent any grouping via the systems in-place to create groups. This one is likely a big ask, but it already works in LFD/LFR for payers you have on ignore, so let’s go a step further and make it ALL groups of any kind. We often have posts in the CS forums where people are trying to avoid, block or ignore another player (for many valid reasons) and it is difficult when ignore still lets the other player see when you’re logged in and where you are in-game, with zero ways to prevent them from seeing it (short of paid services to wipe you from their friend list).','2023-03-23','In-game ignore…let’s make it better!',1,0,1),
(11,3,'Taunka Allied Race Megathread','2020-01-23','Taunka Allied Race Megathread',1,0,2),
(12,4,'We need m+ automatic queue','2020-05-22','We need m+ automatic queue',1,0,2),
(13,5,'PSA for players who pug m+','2020-02-22','PSA for players who pug m+',1,0,0),
(14,6,'Classic and Classic HC are proof that a change is needed','2020-01-20','Classic and Classic HC are proof that a change is needed',1,0,-2),
(15,1,'Gear Update and Trial Accounts','2020-02-19','Gear Update and Trial Accounts',1,0,0),
(16,8,'Garrosh Skip','2020-01-18','Garrosh Skip',1,0,0),
(18,13,'Pathfinder is getting ridiculous','2022-01-23','Pathfinder is getting ridiculous',1,0,0),
(19,19,'Older fan base is migrating to classic cause difficulty','2022-02-23','Older fan base is migrating to classic cause difficulty',1,0,0),
(20,21,'M+ keystone Depletion','2020-01-23','M+ keystone Depletion',1,0,0),
(21,1,'They will bring back Sargeras','2023-01-23','They will bring back Sargeras',1,0,1),
(22,23,'[Trading Post] Possessed Watcher Keg disappointment','2022-05-23','[Trading Post] Possessed Watcher Keg disappointment',1,0,-1),
(23,24,'Two twitch drops for wow revealed','2020-01-15','Two twitch drops for wow revealed',1,0,0),
(24,25,'How to make alts/logs private','2022-03-13','How to make alts/logs private',1,0,0),
(25,26,'Queue now for another chance at Arfas!','2021-01-23','Queue now for another chance at Arfas!',1,0,0),
(26,1,'Pathfinder for Dragonflight','2021-07-23','Pathfinder for Dragonflight',1,0,1),
(27,4,'Blizzcon Inclusion Nexus','2021-09-11','Blizzcon Inclusion Nexus',1,0,0),
(30,1,'<p>Let\'s gooo!</p>','2023-11-05','It\'s happening, classic + it\'s here!!',1,0,1);

INSERT IGNORE INTO `comentario` (comentario_id, content_comentario, fecha_comentario, publicacion_id, author_id) VALUES
(5,'Agree. I never understood why this wasnt implemented from the beginning.','2022-10-20 03:00:00',3,1),
(6,'It is infuriating ignoring/reporting the multi-paragraph guild addon spam + invite on every single character I log on, and having to dedicate an ignore spot to their generic brandless rerolled spam-bot alts. Oh, it’s been 5 minutes and they just deleted it + rerolled another toon and now are spamming all 10+ of my toons again every time I relog? Yay.','2022-11-19 03:00:00',3,3),
(7,'first: i would like to thank you for taking your time to write this lovely post regarding the in-game ignore function.','2023-01-10 03:00:00',3,4),
(8,'Very much in agreement on this.','2023-05-20 04:00:00',3,5),
(9,'At face value this isn’t a bad idea, but think of how the receiving player would view the strike. I can see escalation being used if this were the case. An ignore is silent, for the most part, unless the player keeps trying to communicate with you.','2022-10-20 03:00:00',3,6),
(10,'No way it can get worse with him around so yes?','2022-10-20 03:00:00',1,5),
(11,'You would be suprised','2022-10-22 03:00:00',1,3),
(12,'Creative Director.','2022-10-23 03:00:00',1,4),
(13,'<p>testoo</p>','2023-10-20 09:38:00',1,1),
(14,'<p><b><u style=\"background-color: rgb(255, 0, 0);\">micomentarioo</u></b></p>','2023-10-20 09:41:38',1,1),
(15,'<ol><li><font color=\"#ff9c00\"><b><u><br></u></b></font></li><li><font color=\"#ff9c00\"><b><u><br></u></b></font></li><li><font color=\"#ff9c00\"><b><u>test</u></b></font></li></ol><p><font color=\"#ff9c00\"><b><u><br></u></b></font></p><p style=\"text-align: center; \"><font color=\"#ff9c00\"><b><u>test</u></b></font></p>','2023-10-20 09:50:06',1,1),
(16,'<p>I agree.</p>','2023-10-20 09:50:54',3,1),
(17,'<p>I do not</p>','2023-10-26 13:57:24',3,20);

INSERT IGNORE INTO `usuario_reacciona_publicacion` VALUES
('LIKE',1,1),
('DISLIKE',3,1),
('LIKE',3,20),
('LIKE',11,1),
('LIKE',11,20),
('LIKE',12,1),
('LIKE',12,20),
('LIKE',13,1),
('DISLIKE',13,20),
('DISLIKE',14,1),
('DISLIKE',14,20),
('LIKE',15,1),
('DISLIKE',15,20),
('LIKE',16,1),
('DISLIKE',16,20),
('LIKE',21,1),
('DISLIKE',22,1),
('LIKE',26,1),
('LIKE',30,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;