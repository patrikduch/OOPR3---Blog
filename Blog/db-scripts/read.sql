SELECT * FROM Member M
SELECT * FROM Role
SELECT * FROM MemberRole



SELECT * FROM MEMBER
WHERE USERNAME = 'patrikhunt' AND ID IN (
  SELECT ID
  FROM ROLE
  WHERE ROLENAME = 'Admin'
);

SELECT * FROM CATEGORY

INSERT INTO CATEGORY values (1, 'Test', 'aa', true)