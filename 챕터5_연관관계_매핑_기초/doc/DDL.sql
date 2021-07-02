/* CREATE DATABASES */
CREATE DATABASE jpas CHARACTER SET utf8mb4 collate utf8mb4_unicode_ci;
/* CREATE USER */
CREATE USER 'jpas'@'%' identified BY 'tmxmfla111';
/* GIVEN GRANT TO USER */
GRANT ALL PRIVILEGES ON jpas.* to 'jpas'@'%';


DROP TABLE IF exists MEMBER;

DROP TABLE IF exists TEAM;

/* CREATE TABLES */
CREATE TABLE MEMBER(
                       MEMBER_ID BIGINT NOT NULL,
                       TEAM_ID BIGINT,
                       USER_NAME VARCHAR(2555),
                       PRIMARY KEY (MEMBER_ID)
);

CREATE TABLE TEAM(
                     TEAM_ID BIGINT NOT NULL,
                     NAME VARCHAR(255),
                     PRIMARY KEY (TEAM_ID)
);

/* CREATE CONSTRAINT FOREIGN KEY */
ALTER TABLE MEMBER ADD CONSTRAINT FK_MEMBER_TEAM
    FOREIGN KEY (TEAM_ID)
        REFERENCES TEAM(TEAM_ID);