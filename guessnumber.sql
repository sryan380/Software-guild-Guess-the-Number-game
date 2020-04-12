Drop database if EXISTS guessnumber;

create database guessnumber;

use guessnumber;



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

Select * from rounds r
inner join games g on r.gameID = g.gameID
where r.gameID = 1;