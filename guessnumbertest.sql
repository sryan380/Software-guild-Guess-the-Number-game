Drop database if EXISTS guessnumbertest;

create database guessnumbertest;

use guessnumbertest;



create table games(
	gameID int primary key auto_increment,
    targetNumber int not null,
    isCompleted bool default 0
);

create table rounds (
	roundID int primary key auto_increment,
    gameID int,
    foreign key (gameID) references games(gameId),
	guess int,
    timeGuess DateTime,
    exactmatches int,
    partialmathces int
);

select * from games;


Select * from rounds r
inner join games g on r.gameID = g.gameID
where r.gameID = 1;