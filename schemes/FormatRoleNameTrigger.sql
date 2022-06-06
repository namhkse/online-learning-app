USE Online_Learning;

PRINT '';
PRINT '*** Format [Name] In Table Role. Trim and UpperCase.';
GO
UPDATE dbo.Role SET [Name] = UPPER(TRIM([Name]));

PRINT '';
PRINT '*** Create FormatRoleNameTrigger.';
GO

CREATE TRIGGER FormatRoleNameTrigger
ON dbo.Role
AFTER INSERT, UPDATE
AS
BEGIN
	UPDATE dbo.Role
	SET	[Name] = (SELECT UPPER(TRIM([Name]))
					FROM Inserted
					WHERE RoleID = dbo.Role.RoleID)
	FROM dbo.Role
	JOIN Inserted ON Inserted.RoleID = Role.RoleID;
END
