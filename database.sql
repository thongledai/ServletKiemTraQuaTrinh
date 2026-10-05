USE KiemTraLTWeb;
GO


-- Danh mục
INSERT INTO Category (Categoryname, Categorycode, Images, Status) VALUES
    (N'Nhạc', N'MUSIC', N'music.jpg', 1),
    (N'Giải trí', N'ENTERTAINMENT', N'entertainment.jpg', 1),
    (N'Giáo dục', N'EDUCATION', N'education.jpg', 1),
    (N'Công nghệ', N'TECHNOLOGY', N'technology.jpg', 1),
    (N'Thể thao', N'SPORT', N'sport.jpg', 1);
GO

-- Người dùng
INSERT INTO Users (Username, Password, Phone, Fullname, Email, Admin, Active, Images) VALUES
    (N'admin', N'123456', N'0901234567', N'Quản trị viên', N'admin@gmail.com', 1, 1, N'admin.jpg'),
    (N'user01', N'123456', N'0901111111', N'Nguyễn Văn An', N'an@gmail.com', 0, 1, N'user01.jpg'),
    (N'user02', N'123456', N'0902222222', N'Trần Văn Bình', N'binh@gmail.com', 0, 1, N'user02.jpg'),
    (N'user03', N'123456', N'0903333333', N'Lê Văn Cường', N'cuong@gmail.com', 0, 1, N'user03.jpg');
GO

-- Videos
INSERT INTO Videos (VideoId, Title, Poster, Views, Description, Active, CategoryId, Price) VALUES
    (N'VID001', N'Nhạc trẻ hay nhất 2026', N'vid001.jpg', 1200, N'Tổng hợp những bài nhạc trẻ được yêu thích.', 1, 1, 60000),
    (N'VID002', N'Nhạc chill thư giãn', N'vid002.jpg', 850, N'Những bài nhạc chill giúp thư giãn.', 1, 1, 60000),
    (N'VID003', N'Nhạc Việt Nam mới nhất', N'vid003.jpg', 2300, N'Tuyển tập nhạc Việt Nam mới nhất.', 1, 1, 60000),
    (N'VID004', N'Nhạc buồn tâm trạng', N'vid004.jpg', 950, N'Những ca khúc dành cho những ngày tâm trạng.', 1, 1, 60000),
    (N'VID005', N'Video hài tổng hợp', N'vid005.jpg', 3200, N'Những video hài được yêu thích.', 1, 2, 70000),
    (N'VID006', N'Khoảnh khắc hài hước', N'vid006.jpg', 1800, N'Các khoảnh khắc hài hước trong cuộc sống.', 1, 2, 70000),
    (N'VID007', N'Chương trình giải trí', N'vid007.jpg', 2700, N'Chương trình giải trí nổi bật.', 1, 2, 70000),
    (N'VID008', N'Gameshow vui nhộn', N'vid008.jpg', 1400, N'Gameshow giải trí dành cho mọi người.', 1, 2, 70000),
    (N'VID009', N'Học Java cơ bản', N'vid009.jpg', 4500, N'Hướng dẫn học Java cho người mới bắt đầu.', 1, 3, 80000),
    (N'VID010', N'Học SQL Server', N'vid010.jpg', 3900, N'Hướng dẫn SQL Server cơ bản.', 1, 3, 80000),
    (N'VID011', N'Học Spring Boot', N'vid011.jpg', 5100, N'Hướng dẫn xây dựng ứng dụng với Spring Boot.', 1, 3, 80000),
    (N'VID012', N'Học lập trình Web', N'vid012.jpg', 2800, N'Kiến thức cơ bản về lập trình Web.', 1, 3, 80000),
    (N'VID013', N'Giới thiệu trí tuệ nhân tạo', N'vid013.jpg', 6200, N'Giới thiệu về AI và các ứng dụng thực tế.', 1, 4, 90000),
    (N'VID014', N'AI trong cuộc sống', N'vid014.jpg', 4800, N'Các ứng dụng AI trong cuộc sống.', 1, 4, 90000),
    (N'VID015', N'Blockchain là gì', N'vid015.jpg', 2100, N'Tìm hiểu những kiến thức cơ bản về Blockchain.', 1, 4, 90000),
    (N'VID016', N'Internet of Things', N'vid016.jpg', 3500, N'Tìm hiểu về Internet of Things.', 1, 4, 90000),
    (N'VID017', N'Bóng đá Việt Nam', N'vid017.jpg', 7200, N'Tin tức bóng đá Việt Nam.', 1, 5, 100000),
    (N'VID018', N'Kỹ thuật đá bóng', N'vid018.jpg', 3100, N'Hướng dẫn một số kỹ thuật bóng đá.', 1, 5, 100000),
    (N'VID019', N'Các bàn thắng đẹp', N'vid019.jpg', 5600, N'Tổng hợp những bàn thắng đẹp.', 1, 5, 100000),
    (N'VID020', N'Thể thao hôm nay', N'vid020.jpg', 4300, N'Tổng hợp tin tức thể thao mới nhất.', 1, 5, 100000);
GO

-- Shares
INSERT INTO Shares (Emails, SharedDate, Username, VideoId) VALUES
    (N'friend1@gmail.com', '2026-09-01', N'user01', N'VID001'),
    (N'friend2@gmail.com', '2026-09-02', N'user01', N'VID005'),
    (N'friend3@gmail.com', '2026-09-03', N'user02', N'VID009'),
    (N'friend4@gmail.com', '2026-09-04', N'user02', N'VID013'),
    (N'friend5@gmail.com', '2026-09-05', N'user03', N'VID017'),
    (N'friend6@gmail.com', '2026-09-06', N'user03', N'VID003');
GO

-- Favorites
INSERT INTO Favorites (LikedDate, VideoId, Username) VALUES
    ('2026-09-01', N'VID001', N'user01'),
    ('2026-09-02', N'VID002', N'user01'),
    ('2026-09-03', N'VID009', N'user01'),
    ('2026-09-04', N'VID013', N'user01'),
    ('2026-09-05', N'VID001', N'user02'),
    ('2026-09-06', N'VID005', N'user02'),
    ('2026-09-07', N'VID010', N'user02'),
    ('2026-09-08', N'VID017', N'user02'),
    ('2026-09-09', N'VID003', N'user03'),
    ('2026-09-10', N'VID007', N'user03'),
    ('2026-09-11', N'VID011', N'user03'),
    ('2026-09-12', N'VID019', N'user03');
GO

-- Carts
INSERT INTO Carts (Username) VALUES
    (N'user01'),
    (N'user02'),
    (N'user03');
GO

-- CartItems
INSERT INTO CartItems (CartId, VideoId, Quantity)
SELECT c.CartId, v.VideoId, 1
FROM Carts c
JOIN Videos v ON v.VideoId IN (N'VID001', N'VID009')
WHERE c.Username = N'user01';

INSERT INTO CartItems (CartId, VideoId, Quantity)
SELECT c.CartId, v.VideoId, 1
FROM Carts c
JOIN Videos v ON v.VideoId IN (N'VID005', N'VID017')
WHERE c.Username = N'user02';

INSERT INTO CartItems (CartId, VideoId, Quantity)
SELECT c.CartId, v.VideoId, 1
FROM Carts c
JOIN Videos v ON v.VideoId IN (N'VID013', N'VID020')
WHERE c.Username = N'user03';
GO

-- Kiểm tra dữ liệu
SELECT * FROM Users;
SELECT * FROM Category;
SELECT * FROM Videos;
SELECT * FROM Shares;
SELECT * FROM Favorites;
SELECT * FROM Carts;
SELECT * FROM CartItems;
SELECT * FROM Orders;
SELECT * FROM OrderDetails;
GO
