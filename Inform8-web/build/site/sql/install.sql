--  ====================================== 
--        IP Blacklist for blocking IP's
--  ======================================
CREATE TABLE `IpBlacklist` (
  `IpBlacklistId` smallint unsigned NOT NULL auto_increment,
  `Ip` varchar(255) NOT NULL,
  `Expires` Date NOT NULL,
  PRIMARY KEY  (`IpBlacklistId`)
) ENGINE=InnoDB CHARSET=utf8;

--  ====================================== 
--        Templates & General Variables
--  ======================================
CREATE TABLE `EmailTemplate` (
  `TemplateId` smallint unsigned NOT NULL auto_increment,
  `Enabled` tinyint(1) default NULL,
  `Name` varchar(255) NOT NULL,
  `SubjectTemplate` varchar(512) NOT NULL,
  `HtmlTemplate` text,
  `TextTemplate` text,
  PRIMARY KEY  (`TemplateId`)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE `TemplateFile` (
  `TemplateFileId` smallint unsigned NOT NULL auto_increment,
  `Enabled` tinyint(1) default NULL,
  `Name` varchar(255) NOT NULL,
  `Filename` varchar(255),
  `TemplateId` smallint unsigned NOT NULL,
  PRIMARY KEY  (`TemplateFileId`)
) ENGINE=InnoDB CHARSET=utf8;
ALTER TABLE `TemplateFile` ADD CONSTRAINT FOREIGN KEY (`TemplateId`) REFERENCES `EmailTemplate` (`TemplateId`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `TemplateFileAttachment` (
  `TemplateFileAttachmentId` smallint unsigned NOT NULL auto_increment,
  `Enabled` tinyint(1) default NULL,
  `Name` varchar(255) NOT NULL,
  `Filename` varchar(255),
  `TemplateId` smallint unsigned NOT NULL,
  PRIMARY KEY  (`TemplateFileAttachmentId`)
) ENGINE=InnoDB CHARSET=utf8;
ALTER TABLE `TemplateFileAttachment` ADD CONSTRAINT FOREIGN KEY (`TemplateId`) REFERENCES `EmailTemplate` (`TemplateId`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `Variable` (
  `VariableId` smallint unsigned NOT NULL auto_increment,
  `Enabled` tinyint(1) default NULL,
  `Name` varchar(32) NOT NULL,
  `Value` varchar(256) NOT NULL,
  `TemplateId` smallint unsigned,
  PRIMARY KEY  (`VariableId`)
) ENGINE=InnoDB CHARSET=utf8;
ALTER TABLE `Variable` ADD CONSTRAINT FOREIGN KEY (`TemplateId`) REFERENCES `EmailTemplate` (`TemplateId`) ON DELETE CASCADE ON UPDATE CASCADE;


--  ====================================== 
--  Generic user table.
--  ======================================
CREATE TABLE  `User` (
  `UserId` mediumint unsigned NOT NULL auto_increment,
  `Enabled` tinyint(1) NOT NULL,
  `Firstname` varchar(255),
  `Lastname` varchar(255),
  `UserLevel` enum('User','Admin') default 'User',
  `Email` varchar(255),	
  `Username` varchar(32) UNIQUE,
  `Password` varchar(255) NOT NULL,
  `CreationTime` TIMESTAMP,
  `IP` varchar(50),
  `Browser` varchar(255),
  PRIMARY KEY  (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 


CREATE TABLE  `UserLoginHistory` (
  `UserLoginHistoryId` bigint unsigned NOT NULL auto_increment,
  `UserId` mediumint unsigned NOT NULL,
  `CreationTime` TIMESTAMP,
  `IP` varchar(50),
  `Latitude` FLOAT( 10, 6 ) NOT NULL,
  `Longitude` FLOAT( 10, 6 ) NOT NULL,
  PRIMARY KEY  (`UserLoginHistoryId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `UserLoginHistory` ADD CONSTRAINT FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE; 
