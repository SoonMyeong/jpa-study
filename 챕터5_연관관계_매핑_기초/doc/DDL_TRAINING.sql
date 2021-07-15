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
                       USER_NAME VARCHAR(255),
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



/* TRAINING TABLES */

/* CREATE TABLES */
CREATE TABLE USER(
                USER_ID BIGINT NOT NULL,
                NAME    VARCHAR(255),
                CITY    VARCHAR(255),
                STREET  VARCHAR(255),
                ZIPCODE VARCHAR(255),
                PRIMARY KEY (USER_ID)
);


CREATE TABLE ORDERS(
                ORDER_ID        BIGINT NOT NULL,
                USER_ID         BIGINT NOT NULL,
                ORDER_DATE      DATE,
                STATUS          VARCHAR(255),
                FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
                PRIMARY KEY (ORDER_ID)
);

CREATE TABLE ORDER_ITEM(
                ORDER_ITEM_ID    BIGINT NOT NULL,
                ORDER_ID         BIGINT NOT NULL,
                ITEM_ID          BIGINT NOT NULL,
                ORDER_PRICE      INT,
                COUNT            INT,
                FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID),
                FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ITEM_ID),
                PRIMARY KEY (ORDER_ITEM_ID)
);

CREATE TABLE ITEM(
                ITEM_ID         BIGINT NOT NULL,
                NAME            VARCHAR(255),
                PRICE           INT,
                STOCK_QUANTITY  INT,
                PRIMARY KEY (ITEM_ID)
);