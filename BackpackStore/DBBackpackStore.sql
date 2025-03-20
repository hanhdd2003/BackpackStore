CREATE DATABASE BackpackStore

USE BackpackStore

DROP TABLE Backpack
DROP TABLE Employee

SELECT * FROM Backpack
SELECT * FROM Employee

CREATE TABLE Backpack(
	id			INT				PRIMARY KEY		IDENTITY(1,1),
	[type]		NVARCHAR(255)	NOT NULL,
	quantity	INT				NOT NULL,
	price		DECIMAL(18,2)	NOT NULL,
	selled		INT				DEFAULT 0
)

CREATE TABLE Employee(
	id			INT				PRIMARY KEY		IDENTITY(1,1),
	firstname	NVARCHAR(255)	NOT NULL,
	lastname	NVARCHAR(255)	NOT NULL,
	email		NVARCHAR(255)	NOT NULL,
	[address]	NVARCHAR(255),
	phone		NVARCHAR(255)	NOT NULL
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

INSERT INTO Employee (firstname, lastname, email, [address], phone)
VALUES
(N'Nguyễn', N'Minh Tuấn', 'nguyen.minhtuan@example.com', N'Số 12, Đường Lê Lai, TP. Hồ Chí Minh', '0901234567'),
(N'Trần', N'Thị Lan', 'tran.thilan@example.com', N'Số 34, Đường Nguyễn Trãi, Hà Nội', '0987654321'),
(N'Phan', N'Thiện Tâm', 'phan.thientam@example.com', N'Số 56, Đường Phan Đình Phùng, Đà Nẵng', '0912345678'),
(N'Lê', N'Văn An', 'le.vanan@example.com', N'Số 78, Đường Trần Phú, Cần Thơ', '0934567890'),
(N'Hoàng', N'Hữu Hùng', 'hoang.huuhung@example.com', N'Số 90, Đường Nguyễn Huệ, Bình Dương', '0945678901');
