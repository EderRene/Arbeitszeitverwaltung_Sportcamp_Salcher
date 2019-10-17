DROP TABLE camp CASCADE CONSTRAINTS;
DROP TABLE userCamp CASCADE CONSTRAINTS;
DROP TABLE worksIn CASCADE CONSTRAINTS;
DROP TABLE address CASCADE CONSTRAINTS;
DROP TABLE dokument CASCADE CONSTRAINTS;
DROP TABLE dokumenttype CASCADE CONSTRAINTS;

CREATE TABLE address(
    id INTEGER,
    addressLine1 VARCHAR2(30),
    addressLine2 VARCHAR2(30),
    postCode INTEGER,
    city VARCHAR2(30),
    country VARCHAR2(30),
    CONSTRAINT pk_Address PRIMARY KEY(id)
);

CREATE TABLE camp(
    id INTEGER,
    id_Address INTEGER,
    name INTEGER,
    leader INTEGER,
    CONSTRAINT pk_Camp PRIMARY KEY(id),
    CONSTRAINT fk_Camp_Address FOREIGN KEY (id_Address) REFERENCES address(id)
);

CREATE TABLE userCamp(
    id INTEGER,
    forename VARCHAR2(30),
    surname VARCHAR2(30),
    dateOfBirth DATE,
    id_Address INTEGER,
    svn INTEGER,
    uidUser INTEGER,
    bankAccountNumber VARCHAR2(30),
    email VARCHAR2(30),
    phoneNumber INTEGER,
    CONSTRAINT pk_User PRIMARY KEY(id),
    CONSTRAINT fk_User_Address FOREIGN KEY(id_Address) REFERENCES address(id)
);

CREATE TABLE worksIn(
    id_User INTEGER,
    id_Camp INTEGER,
    CONSTRAINT pk_WorksIn PRIMARY KEY(id_User, id_Camp),
    CONSTRAINT fk_WorksIn_User FOREIGN KEY (id_User) REFERENCES userCamp(id),
    CONSTRAINT fk_WorksIn_Camp FOREIGN KEY (id_Camp) REFERENCES camp(id)
);

CREATE TABLE dokument(
    id INTEGER,
    id_User INTEGER,
    type INTEGER,
    dokument BLOB,
    CONSTRAINT pk_Dokument PRIMARY KEY(id),
    CONSTRAINT fk_Dokument_User FOREIGN KEY (id_User) REFERENCES userCamp(id)
);

CREATE TABLE dokumenttype(
    id INTEGER,
    type VARCHAR2(30),
    CONSTRAINT pk_Dokumenttype PRIMARY KEY(id, type)
);

INSERT INTO dokumenttype VALUES(1, 'Lohnzettel');
INSERT INTO dokumenttype VALUES(2, 'Arbeitsvertrag');