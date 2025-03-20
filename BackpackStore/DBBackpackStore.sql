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
	selled		INT				DEFAULT 0,
	[description] NVARCHAR(MAX)
)

CREATE TABLE Employee(
	id			INT				PRIMARY KEY		IDENTITY(1,1),
	firstname	NVARCHAR(255)	NOT NULL,
	lastname	NVARCHAR(255)	NOT NULL,
	email		NVARCHAR(255)	NOT NULL,
	[address]	NVARCHAR(255),
	phone		NVARCHAR(255)	NOT NULL,
	gender		BIT,
	[status]	BIT default 1
)

INSERT INTO Backpack ([type], quantity, price,selled, [description])
VALUES 
(N'Balo Thể Thao', 60, 550.00,10, N'Balo thể thao phù hợp cho các hoạt động ngoài trời và thể thao'),
(N'Balo Thể Thao', 45, 600.00,13, N'Balo thể thao thiết kế gọn nhẹ, tiện lợi cho các chuyến đi ngắn'),
(N'Balo Thời Trang', 100, 450.00,3, N'Balo thời trang phù hợp cho giới trẻ, thiết kế hiện đại'),
(N'Balo Thời Trang', 80, 499.99,89, N'Balo thời trang với kiểu dáng trẻ trung, dễ phối đồ'),
(N'Balo Laptop', 70, 750.00,0, N'Balo laptop bảo vệ an toàn cho thiết bị điện tử và có nhiều ngăn tiện dụng'),
(N'Balo Laptop', 55, 800.00,2, N'Balo laptop cao cấp, thiết kế chắc chắn, phù hợp cho công việc'),
(N'Balo Du Lịch', 25, 650.00,4, N'Balo du lịch rộng rãi, có nhiều ngăn để chứa đồ đạc'),
(N'Balo Du Lịch', 50, 620.00,40, N'Balo du lịch tiện lợi, thoải mái cho những chuyến đi dài ngày'),
(N'Balo Dã Ngoại', 30, 550.00,0, N'Balo dã ngoại, thích hợp cho các chuyến trekking hoặc cắm trại'),
(N'Balo Dã Ngoại', 40, 590.00,23, N'Balo dã ngoại với thiết kế bền bỉ, chống nước, giúp bảo vệ đồ đạc'),
(N'Balo Học Sinh', 90, 180.00,34, N'Balo học sinh đơn giản, dễ sử dụng và phù hợp với học sinh cấp 1'),
(N'Balo Học Sinh', 110, 160.00,23, N'Balo học sinh nhẹ nhàng, thoải mái và dễ dàng mang theo mọi lúc mọi nơi'),
(N'Balo Thể Thao', 35, 530.00,29, N'Balo thể thao với chất liệu bền bỉ, chống thấm nước'),
(N'Balo Thể Thao', 25, 570.00,1, N'Balo thể thao tiện dụng với thiết kế năng động và thoáng khí'),
(N'Balo Thời Trang', 60, 480.00,6, N'Balo thời trang phong cách trẻ trung, dễ dàng kết hợp với trang phục hàng ngày'),
(N'Balo Thời Trang', 50, 510.00,3, N'Balo thời trang cao cấp, mang đến vẻ ngoài thanh lịch và hiện đại');


INSERT INTO Employee (firstname, lastname, email, [address], phone, gender)
VALUES
(N'Nguyễn', N'Minh Tuấn', 'nguyen.minhtuan@example.com', N'Số 12, Đường Lê Lai, TP. Hồ Chí Minh', '0901234567',0),
(N'Trần', N'Thị Lan', 'tran.thilan@example.com', N'Số 34, Đường Nguyễn Trãi, Hà Nội', '0987654321',1),
(N'Phan', N'Thiện Tâm', 'phan.thientam@example.com', N'Số 56, Đường Phan Đình Phùng, Đà Nẵng', '0912345678',1),
(N'Lê', N'Văn An', 'le.vanan@example.com', N'Số 78, Đường Trần Phú, Cần Thơ', '0934567890',0),
(N'Hoàng', N'Hữu Hùng', 'hoang.huuhung@example.com', N'Số 90, Đường Nguyễn Huệ, Bình Dương', '0945678901',0),
(N'Nguyễn', N'Lan Anh', 'nguyen.lananh@example.com', N'Số 22, Đường Lý Thường Kiệt, TP. Hồ Chí Minh', '0908765432', 1),
(N'Trần', N'Văn Tùng', 'tran.vantung@example.com', N'Số 88, Đường Hoàng Diệu, Hà Nội', '0919876543', 0),
(N'Phan', N'Hoàng Minh', 'phan.hoangminh@example.com', N'Số 12, Đường Lê Duẩn, Đà Nẵng', '0923456789', 0),
(N'Lê', N'Khánh Hòa', 'le.khanhhoa@example.com', N'Số 45, Đường Phan Chu Trinh, Cần Thơ', '0932345678', 1),
(N'Hoàng', N'Quốc Duy', 'hoang.quocduy@example.com', N'Số 30, Đường Nguyễn Văn Cừ, Bình Dương', '0941234567', 0);
