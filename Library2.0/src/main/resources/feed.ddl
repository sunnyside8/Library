INSERT INTO roles(role)
VALUES ('ADMIN'),
       ('MODERATOR'),
       ('USER');


INSERT INTO clients (email, client_name, password, username)
VALUES ('stanislava@gmail.com', 'Stanislava Oncheva',
        '5c81772c076638d0427b78498a47ec160fd411806ac9ea6927233e534a74b68a3d0008a2a4012b0f', 'sunny'),
       ('svetlin@gmail.com', 'Svetlin Kolev',
        '5c81772c076638d0427b78498a47ec160fd411806ac9ea6927233e534a74b68a3d0008a2a4012b0f', 'svetlio'),
       ('kristina@gmail.com', 'Kristina Oncheva',
        '5c81772c076638d0427b78498a47ec160fd411806ac9ea6927233e534a74b68a3d0008a2a4012b0f', 'kristinka');

INSERT INTO clients_roles(client_id, role_id)
VALUES (1, 1),
       (1, 3),
       (2, 3),
       (3, 3);



INSERT INTO authors (author_name)
VALUES ('J. C. N. Jencarris'),
       ('Jonathan Ballers'),
       ('A. Jennings Ballington'),
       ('J. C. Noziz'),
       ('Carla Ruby School'),
       ('Randolph V. Hawthorn'),
       ('Annie Anson'),
       ('Noris Jenblood'),
       ('J. C. Northcott'),
       ('Victorine Northup'),
       ('R. V. Raymonds');



INSERT INTO books (book_title, author_id, date_of_publishing)
VALUES ('Pilot Of Dusk', 3, '01/09/1997'),
       ('Sage Of The Mountain', 3, '27/06/2001'),
       ('Heirs Of Utopia', 9, '17/08/2018'),
       ('Knights Without Courage', 3, '25/07/2015'),
       ('Pirates And Companions', 9, '22/06/2021'),
       ('Pig Of My Country', 9, '18/02/1997'),
       ('Queen Of Fantasy', 10, '13/12/2001'),
       ('Tigers Of Excelsior', 6, '28/03/2004'),
       ('Dinos In My House', 6, '27/02/2007'),
       ('Chickens And Sheep', 10, '29/04/2013'),
       ('Follower Of The Sea', 6, '30/09/2003'),
       ('Ancestor Of Dread', 6, '22/05/2013'),
       ('Companions Of The Gods', 2, '03/04/2002'),
       ('Supporters Of The Forest', 2, '18/01/2012'),
       ('Secretaries And Ancestors', 2, '19/10/1993'),
       ('Werewolf Of The Stars', 1, '07/05/2001'),
       ('Necromancer Of The Future', 1, '06/07/2014'),
       ('Hobgoblins Of The North', 1, '19/09/2008'),
       ('Robots Without Flaws', 7, '12/04/2010'),
       ('Dwarves And Summoners', 8, '25/10/1995'),
       ('Angel In The Forest', 4, '08/09/2011'),
       ('Freak Without A Voicen', 7, '09/03/2022'),
       ('Horses At The Crypts', 4, '22/12/1998'),
       ('Snakes Without Eyes', 9, '15/07/2003'),
       ('Students And Animals', 7, '03/08/2012'),
       ('Officer Program', 11, '22/06/1996'),
       ('Parrot Of Humor', 7, '13/02/2010'),
       ('Man Mocks Me', 11, '14/07/2017'),
       ('Fish Has Been Naughty', 11, '13/07/2000'),
       ('Hunter And Butcher', 3, '13/03/2020'),
       ('Tortoise Of The Void', 5, '20/12/2000'),
       ('Soldier Of A Stranger', 3, '16/03/2016'),
       ('Men Of The Moon', 5, '01/09/2009'),
       ('Slaves In My Country', 3, '01/10/1994'),
       ('Butchers And Rebels', 8, '15/01/2017'),
       ('Men Of The Jungle', 8, '19/06/1999'),
       ('Leaders With Tradition', 8, '19/08/2010'),
       ('Insects Of Medicine', 8, '01/07/2002'),
       ('Emperors Of The Lakes', 5, '03/11/2003'),
       ('Fish And Rodents', 2, '27/01/2015'),
       ('Girlfriend Of The Ocean', 7, '23/12/1999'),
       ('Angel With A Spaceship', 10, '11/08/2001'),
       ('Leader Of The Future', 11, '04/09/2017'),
       ('Veterans Of Death', 11, '24/09/2016'),
       ('Commanders Of Mars', 6, '13/05/1999'),
       ('Children And Martians', 10, '30/12/2007');
