DROP TABLE IF EXISTS camp CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS document CASCADE;
DROP TABLE IF EXISTS documentType CASCADE;
DROP TABLE IF EXISTS employeeRights CASCADE;
DROP TABLE IF EXISTS worksIn CASCADE;
DROP TABLE IF EXISTS ownsRight CASCADE;
DROP TABLE IF EXISTS news CASCADE;
DROP SEQUENCE IF EXISTS seqEmployee CASCADE;
DROP SEQUENCE IF EXISTS seqAddress CASCADE;
DROP SEQUENCE IF EXISTS seqCamp CASCADE;
DROP SEQUENCE IF EXISTS seqDocumentType CASCADE;
DROP SEQUENCE IF EXISTS seqDocument CASCADE;
DROP SEQUENCE IF EXISTS seqEmployeeRights CASCADE;
DROP SEQUENCE IF EXISTS seqNews CASCADE;

CREATE SEQUENCE seqAddress START 1;
CREATE SEQUENCE seqEmployee START 1;
CREATE SEQUENCE seqCamp START 1;
CREATE SEQUENCE seqDocumentType START 1;
CREATE SEQUENCE seqDocument START 1;
CREATE SEQUENCE seqEmployeeRights START 1;
CREATE SEQUENCE seqNews START 1;

CREATE TABLE address
(
    id_Address   INTEGER DEFAULT NEXTVAL('seqAddress'),
    addressLine1 VARCHAR(30),
    addressLine2 VARCHAR(30),
    postCode     INTEGER,
    city         VARCHAR(30),
    country      VARCHAR(30),
    CONSTRAINT pk_Address PRIMARY KEY (id_Address),
    CONSTRAINT uq_Address UNIQUE (addressLine1, addressLine2, postCode, city, country)
);

CREATE TABLE employee
(
    id_Employee       INTEGER DEFAULT NEXTVAL('seqEmployee'),
    forename          VARCHAR(30),
    surname           VARCHAR(30),
    dateOfBirth       TIMESTAMP,
    id_Address        INTEGER,
    svn           BIGINT,
    uid               INTEGER,
    bankAccountNumber VARCHAR(30),
    email             VARCHAR(30),
    phoneNumber       VARCHAR(30),
    CONSTRAINT pk_Employee PRIMARY KEY (id_Employee),
    CONSTRAINT fk_Employee_Address FOREIGN KEY (id_Address) REFERENCES address (id_Address),
    CONSTRAINT uq_Employee UNIQUE (forename, surname, dateOfBirth, svn, uid, bankAccountNumber, email, phoneNumber),
    CONSTRAINT uq_Employee_BankAccountNumber UNIQUE (bankAccountNumber),
    CONSTRAINT uq_Employee_Svn UNIQUE (svn),
    CONSTRAINT uq_Employee_Uid UNIQUE (uid)
);

CREATE TABLE camp
(
    id_Camp    INTEGER DEFAULT NEXTVAL('seqCamp'),
    id_Address INTEGER,
    name       VARCHAR(60),
    id_Leader  INTEGER,
    CONSTRAINT pk_Camp PRIMARY KEY (id_Camp),
    CONSTRAINT fk_Camp_Address FOREIGN KEY (id_Address) REFERENCES address (id_Address),
    CONSTRAINT fk_Camp_Leader FOREIGN KEY (id_Leader) REFERENCES employee (id_Employee),
    CONSTRAINT uq_Camp UNIQUE (id_Address, name, id_Leader)
);

CREATE TABLE documentType
(
    id_Documenttype INTEGER DEFAULT NEXTVAL('seqDocumentType'),
    type            VARCHAR(30),
    CONSTRAINT pk_Documenttype PRIMARY KEY (id_Documenttype),
    CONSTRAINT uq_Documenttype UNIQUE (type)
);

CREATE TABLE document
(
    id_Document     INTEGER DEFAULT NEXTVAL('seqDocument'),
    id_User         INTEGER,
    id_Documenttype INTEGER,
    file            BYTEA,
    CONSTRAINT pk_Document PRIMARY KEY (id_Document),
    CONSTRAINT fk_Document_Employee FOREIGN KEY (id_User) REFERENCES employee (id_Employee),
    CONSTRAINT fk_Document_Documenttype FOREIGN KEY (id_Documenttype) REFERENCES documenttype (id_Documenttype),
    CONSTRAINT uq_Document UNIQUE (id_User, id_Documenttype)
);

CREATE TABLE employeeRights
(
    id_EmployeeRight INTEGER DEFAULT NEXTVAL('seqEmployeeRights'),
    decription       VARCHAR(60),
    CONSTRAINT pk_EmployeeRights PRIMARY KEY (id_EmployeeRight),
    CONSTRAINT uq_EmployeeRights UNIQUE (decription)
);

CREATE TABLE worksIn
(
    id_Employee INTEGER,
    id_Camp     INTEGER,
    CONSTRAINT pk_WorksIn PRIMARY KEY (id_Employee, id_Camp),
    CONSTRAINT fk_WorksIn_Employee FOREIGN KEY (id_Employee) REFERENCES employee (id_Employee),
    CONSTRAINT fk_WorksIn_Camp FOREIGN KEY (id_Camp) REFERENCES camp (id_Camp)
);

CREATE TABLE ownsRight
(
    id_Employee  INTEGER,
    id_UserRight INTEGER,
    CONSTRAINT pk_OwnsRight PRIMARY KEY (id_Employee, id_UserRight),
    CONSTRAINT fk_OwnsRight_Employee FOREIGN KEY (id_Employee) REFERENCES employee (id_Employee),
    CONSTRAINT fk_OwnsRight_UserRight FOREIGN KEY (id_UserRight) REFERENCES employeeRights (id_EmployeeRight)
);

CREATE TABLE news
(
    id_News   INTEGER DEFAULT NEXTVAL('seqNews'),
    id_Employee INTEGER,
    dateTime TIMESTAMP,
    infoHeader VARCHAR(100),
    info VARCHAR(400),
    CONSTRAINT pk_News PRIMARY KEY (id_News),
    CONSTRAINT fk_News_Employee FOREIGN KEY (id_Employee) REFERENCES  employee (id_Employee),
    CONSTRAINT uq_News UNIQUE (id_News, id_Employee, dateTime, infoHeader, info)
);

COMMIT;