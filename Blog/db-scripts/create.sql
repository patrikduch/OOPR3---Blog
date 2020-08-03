-- Tabulka pro vytvořené uživatele
CREATE TABLE Member (
  id int not null primary key,
  username VARCHAR(40) not null,
  password VARCHAR(40) not null,
  email VARCHAR(40) not null,
  registrationDate TIMESTAMP not null
);

CREATE TABLE Role (
  id int not null primary key,
  roleName varchar(20) not null unique
)

-- Asociační tabulka mezi rolemi a uživateli
CREATE TABLE MemberRole(
  rId int  not null, -- Asociace s rolemi
  mId int  not null, -- Asociace s uživateli
  FOREIGN KEY (mId) REFERENCES Member(id),
  FOREIGN KEY (rId) REFERENCES Role(id),
  PRIMARY KEY (rId, mId) -- Složený klič
);


CREATE TABLE Category(

  id int not null primary  key,
  categoryName varchar(25) not null,
  description varchar(25) not null,
  Visible   BOOLEAN not null
);

