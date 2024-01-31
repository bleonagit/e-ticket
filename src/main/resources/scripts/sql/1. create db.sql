--use master database
USE master; 
GO 
-- create e-ticket database it not exist
IF NOT EXISTS ( SELECT name FROM sys.databases WHERE name = N'e-ticket' ) CREATE DATABASE [e-ticket]; 
Go