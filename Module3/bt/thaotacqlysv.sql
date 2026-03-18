use QuanLySinhVien;
 #câu 1 Hiển thị tất cả sinh viên có tên bắt đầu bằng ‘h’
SELECT * 
FROM Student
WHERE StudentName LIKE 'h%';
# câu 2 Hiển thị thông tin lớp có thời gian bắt đầu vào tháng 12
SELECT *
FROM Class
WHERE MONTH(StartDate) = 12;
 # câu 3 Hiển thị môn học có credit từ 3 đến 5
 SELECT *
FROM Subject
WHERE Credit BETWEEN 3 AND 5;
# câu 4 Cập nhật ClassID = 2 cho sinh viên tên ‘Hung’
UPDATE Student
SET ClassID = 2
WHERE StudentName = 'Hung';
# câu 5 Hiển thị StudentName, SubName, Mark (sắp xếp)
SELECT s.StudentName, sub.SubName, m.Mark
FROM Mark m
JOIN Student s ON m.StudentID = s.StudentID
JOIN Subject sub ON m.SubID = sub.SubID
ORDER BY m.Mark DESC, s.StudentName ASC;
