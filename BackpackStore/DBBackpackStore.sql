CREATE DATABASE BackpackStore

USE BackpackStore

CREATE TABLE Backpack(
	id			INT				PRIMARY KEY		IDENTITY(1,1),
	[type]		NVARCHAR(255)	NOT NULL,
	quantity	INT				NOT NULL,
	price		DECIMAL(18,2)	NOT NULL,
	selled		INT				DEFAULT 0
)

INSERT INTO Backpack ([type], quantity, price)
VALUES 
(N'Balo Thể Thao', 60, 550.00),
(N'Balo Thể Thao', 45, 600.00),
(N'Balo Thời Trang', 100, 450.00),
(N'Balo Thời Trang', 80, 499.99),
(N'Balo Laptop', 70, 750.00),
(N'Balo Laptop', 55, 800.00),
(N'Balo Du Lịch', 25, 650.00),
(N'Balo Du Lịch', 50, 620.00),
(N'Balo Dã Ngoại', 30, 550.00),
(N'Balo Dã Ngoại', 40, 590.00),
(N'Balo Học Sinh', 90, 180.00),
(N'Balo Học Sinh', 110, 160.00),
(N'Balo Thể Thao', 35, 530.00),
(N'Balo Thể Thao', 25, 570.00),
(N'Balo Thời Trang', 60, 480.00),
(N'Balo Thời Trang', 50, 510.00);
