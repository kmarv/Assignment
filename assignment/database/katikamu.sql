create database Katikamu;
use Katikamu;
create table students( FIRSTNAME varchar(30), GENDER varchar(10) ,
LASTNAME varchar(30) ,regno varchar(20) ,COURSE_SUBJECT varchar(30),
AGE int(3), class varchar(20) );
create table teachers( FIRSTNAME varchar(30), GENDER varchar(10) ,
LASTNAME varchar(30) ,PASSWRD varchar(20),
CONFIRMPASSWRD varchar(20) ,COURSE_SUBJECT varchar(30),
EMAIL varchar(30) );
create table markstable(Regno varchar(20), subject varchar(20), score int(5));