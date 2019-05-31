-- Create Research Database Script
-- ISTE 330 Group Project
-- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng

-- CREATE DATABASE RESEARCH
DROP DATABASE IF EXISTS Research;
CREATE DATABASE Research;
USE Research;

-- CREATE Project table
CREATE TABLE IF NOT EXISTS Project (
    projID INT AUTO_INCREMENT PRIMARY KEY,
    projectName VARCHAR(30) NOT NULL,
    abstractText TEXT NOT NULL,
    ownerID INT NOT NULL
);

-- CREATE User table
CREATE TABLE IF NOT EXISTS User (
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    userType CHAR(1) NOT NULL,
    name VARCHAR(20) NOT NULL
);

-- CREATE Keywords table
CREATE TABLE IF NOT EXISTS Keywords (
    keyID INT AUTO_INCREMENT PRIMARY KEY,
    keyword VARCHAR(80) NOT NULL
);

-- CREATE Contributors table (associative table)
CREATE TABLE IF NOT EXISTS Contributors (
    projID INT,
    username VARCHAR(20),
    PRIMARY KEY (projID, username),
    FOREIGN KEY (username) REFERENCES User(username),
    FOREIGN KEY (projID) REFERENCES Project(projID)
);

-- CREATE ProjectKeys table (associative table)
CREATE TABLE IF NOT EXISTS ProjectKeys (
    projID INT,
    keyID INT,
    PRIMARY KEY (projID, keyID),
    FOREIGN KEY (keyID) REFERENCES Keywords(keyID),
    FOREIGN KEY (projID) REFERENCES Project(projID)
);

-- CREATE UserKeys table (associative table)
CREATE TABLE IF NOT EXISTS UserKeys (
    keyID INT,
    username VARCHAR(20),
    PRIMARY KEY (keyID, username),
    FOREIGN KEY (username) REFERENCES User(username),
    FOREIGN KEY (keyID) REFERENCES Keywords(keyID)
);

