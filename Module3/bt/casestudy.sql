create database Furama;
use Furama;
CREATE TABLE vi_tri (
    ma_vi_tri INT PRIMARY KEY,
    ten_vi_tri VARCHAR(45)
);
CREATE TABLE trinh_do (
    ma_trinh_do INT PRIMARY KEY,
    ten_trinh_do VARCHAR(45)
);
CREATE TABLE bo_phan (
    ma_bo_phan INT PRIMARY KEY,
    ten_bo_phan VARCHAR(45)
);
CREATE TABLE nhan_vien (
    ma_nhan_vien INT PRIMARY KEY,
    ho_ten VARCHAR(45),
    ngay_sinh DATE,
    so_cmnd VARCHAR(45),
    luong DOUBLE,
    so_dien_thoai VARCHAR(45),
    email VARCHAR(45),
    dia_chi VARCHAR(45),
    ma_vi_tri INT,
    ma_trinh_do INT,
    ma_bo_phan INT,
    FOREIGN KEY (ma_vi_tri) REFERENCES vi_tri(ma_vi_tri),
    FOREIGN KEY (ma_trinh_do) REFERENCES trinh_do(ma_trinh_do),
    FOREIGN KEY (ma_bo_phan) REFERENCES bo_phan(ma_bo_phan)
);
CREATE TABLE loai_khach (
    ma_loai_khach INT PRIMARY KEY,
    ten_loai_khach VARCHAR(45)
);
CREATE TABLE khach_hang (
    ma_khach_hang INT PRIMARY KEY,
    ma_loai_khach INT,
    ho_ten VARCHAR(45),
    ngay_sinh DATE,
    gioi_tinh BIT(1),
    so_cmnd VARCHAR(45),
    so_dien_thoai VARCHAR(45),
    email VARCHAR(45),
    dia_chi VARCHAR(45),
    FOREIGN KEY (ma_loai_khach) REFERENCES loai_khach(ma_loai_khach)
);
CREATE TABLE kieu_thue (
    ma_kieu_thue INT PRIMARY KEY,
    ten_kieu_thue VARCHAR(45)
);
CREATE TABLE loai_dich_vu (
    ma_loai_dich_vu INT PRIMARY KEY,
    ten_loai_dich_vu VARCHAR(45)
);
CREATE TABLE dich_vu (
    ma_dich_vu INT PRIMARY KEY,
    ten_dich_vu VARCHAR(45),
    dien_tich INT,
    chi_phi_thue DOUBLE,
    so_nguoi_toi_da INT,
    ma_kieu_thue INT,
    ma_loai_dich_vu INT,
    tieu_chuan_phong VARCHAR(45),
    mo_ta_tien_nghi_khac VARCHAR(45),
    dien_tich_ho_boi DOUBLE,
    so_tang INT,
    FOREIGN KEY (ma_kieu_thue) REFERENCES kieu_thue(ma_kieu_thue),
    FOREIGN KEY (ma_loai_dich_vu) REFERENCES loai_dich_vu(ma_loai_dich_vu)
);
CREATE TABLE hop_dong (
    ma_hop_dong INT PRIMARY KEY,
    ngay_lam_hop_dong DATE,
    ngay_ket_thuc DATE,
    tien_dat_coc DOUBLE,
    ma_nhan_vien INT,
    ma_khach_hang INT,
    ma_dich_vu INT,
    FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien(ma_nhan_vien),
    FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang(ma_khach_hang),
    FOREIGN KEY (ma_dich_vu) REFERENCES dich_vu(ma_dich_vu)
);
CREATE TABLE dich_vu_di_kem (
    ma_dich_vu_di_kem INT PRIMARY KEY,
    ten_dich_vu_di_kem VARCHAR(45),
    gia DOUBLE,
    don_vi VARCHAR(10),
    trang_thai VARCHAR(45)
);
CREATE TABLE hop_dong_chi_tiet (
    ma_hop_dong_chi_tiet INT PRIMARY KEY,
    ma_hop_dong INT,
    ma_dich_vu_di_kem INT,
    so_luong INT,
    FOREIGN KEY (ma_hop_dong) REFERENCES hop_dong(ma_hop_dong),
    FOREIGN KEY (ma_dich_vu_di_kem) REFERENCES dich_vu_di_kem(ma_dich_vu_di_kem)
);
INSERT INTO vi_tri VALUES
(1,'Quản Lý'),
(2,'Nhân Viên');
INSERT INTO trinh_do VALUES
(1,'Trung Cấp'),
(2,'Cao Đẳng'),
(3,'Đại Học'),
(4,'Sau Đại Học');
INSERT INTO bo_phan VALUES
(1,'Sale-Marketing'),
(2,'Hành chính'),
(3,'Phục vụ'),
(4,'Quản lý');
INSERT INTO loai_khach VALUES
(1,'Diamond'),
(2,'Platinium'),
(3,'Gold'),
(4,'Silver'),
(5,'Member');
INSERT INTO kieu_thue VALUES
(1,'year'),
(2,'month'),
(3,'day'),
(4,'hour');
INSERT INTO loai_dich_vu VALUES
(1,'Villa'),
(2,'House'),
(3,'Room');
INSERT INTO dich_vu VALUES
(1,'Villa Beach Front',25000,10000000,10,3,1,'vip','Có hồ bơi',500,4),
(2,'House Princess 01',14000,5000000,7,2,2,'vip','Có thêm bếp nướng',NULL,3),
(3,'Room Twin 01',5000,1000000,2,4,3,'normal','Có tivi',NULL,NULL),
(4,'Villa No Beach Front',22000,9000000,8,3,1,'normal','Có hồ bơi',300,3),
(5,'House Princess 02',10000,4000000,5,3,2,'normal','Có thêm bếp nướng',NULL,2),
(6,'Room Twin 02',3000,900000,2,4,3,'normal','Có tivi',NULL,NULL);
INSERT INTO nhan_vien VALUES
(1,'Nguyễn Văn An','1970-11-07','456231786',10000000,'0901234121','annguyen@gmail.com','295 Nguyễn Tất Thành, Đà Nẵng',1,3,1),
(2,'Lê Văn Bình','1997-04-09','654231234',7000000,'0934212314','binhlv@gmail.com','22 Yên Bái, Đà Nẵng',1,2,2),
(3,'Hồ Thị Yến','1995-12-12','999231723',14000000,'0412352315','thiyen@gmail.com','K234/11 Điện Biên Phủ, Gia Lai',1,3,2),
(4,'Võ Công Toàn','1980-04-04','123231365',17000000,'0374443232','toan0404@gmail.com','77 Hoàng Diệu, Quảng Trị',1,4,4),
(5,'Nguyễn Bình Phát','1999-12-09','454363232',6000000,'0902341231','phatphat@gmail.com','43 Yên Bái, Đà Nẵng',2,1,1),
(6,'Khúc Nguyễn An Nghi','2000-11-08','964542311',7000000,'0978653213','annghi20@gmail.com','294 Nguyễn Tất Thành, Đà Nẵng',2,2,3),
(7,'Nguyễn Hữu Hà','1993-01-01','534323231',8000000,'0941234553','hho101@gmail.com','4 Nguyễn Chí Thanh, Huế',2,3,2),
(8,'Nguyễn Hà Đông','1989-09-03','234414123',9000000,'0642123111','donghanguyen@gmail.com','111 Hùng Vương, Hà Nội',2,4,4),
(9,'Tống Hoàng','1982-09-03','256781231',6000000,'0245144444','hoangtq@gmail.com','213 Hàm Nghi, Đà Nẵng',2,4,4),
(10,'Nguyễn Công Đạo','1994-01-08','755434343',8000000,'0988767111','nguyencongdao12@gmail.com','6 Hòa Khánh, Đồng Nai',2,3,2);
INSERT INTO khach_hang VALUES
(1,5,'Nguyễn Thị Hào','1970-11-07',0,'643431213','0945423362','thihao07@gmail.com','23 Nguyễn Hoàng, Đà Nẵng'),
(2,3,'Phạm Xuân Diệu','1992-08-08',1,'865342123','0954333333','xuandieu92@gmail.com','K77/22 Thái Phiên, Quảng Trị'),
(3,1,'Trương Đình Nghệ','1990-02-27',1,'488645199','0373213122','nghenhan2702@gmail.com','K323/12 Ông Ích Khiêm, Vinh'),
(4,1,'Dương Văn Quan','1981-07-08',1,'543432111','0490039241','duongquan@gmail.com','K453/12 Lê Lợi, Đà Nẵng'),
(5,4,'Hoàng Trần Nhi Nhi','1995-12-09',0,'795453345','0312345678','nhinhi123@gmail.com','224 Lý Thái Tổ, Gia Lai'),
(6,4,'Tôn Nữ Mộc Châu','2005-12-06',0,'732434215','0988888844','tonnuchau@gmail.com','37 Yên Thế, Đà Nẵng'),
(7,1,'Nguyễn Mỹ Kim','1984-04-08',0,'856453123','0912345698','kimcuong84@gmail.com','K123/45 Lê Lợi, Hồ Chí Minh'),
(8,3,'Nguyễn Thị Hào','1999-04-08',0,'965656433','0763212345','haohao99@gmail.com','55 Nguyễn Văn Linh, Kon Tum'),
(9,1,'Trần Đại Danh','1994-07-01',1,'432341235','0643343433','danhhai99@gmail.com','24 Lý Thường Kiệt, Quảng Ngãi'),
(10,2,'Nguyễn Tâm Đắc','1989-07-01',1,'344343432','0987654321','dactam@gmail.com','22 Ngô Quyền, Đà Nẵng');

INSERT INTO dich_vu_di_kem VALUES
(1,'Karaoke',10000,'giờ','tiện nghi, hiện tại'),
(2,'Thuê xe máy',10000,'chiếc','hỏng 1 xe'),
(3,'Thuê xe đạp',20000,'chiếc','tốt'),
(4,'Buffet buổi sáng',15000,'suất','đầy đủ đồ ăn, tráng miệng'),
(5,'Buffet buổi trưa',90000,'suất','đầy đủ đồ ăn, tráng miệng'),
(6,'Buffet buổi tối',16000,'suất','đầy đủ đồ ăn, tráng miệng');

INSERT INTO hop_dong VALUES
(1,'2020-12-08','2020-12-08',0,3,1,3),
(2,'2020-07-14','2020-07-21',200000,7,3,1),
(3,'2021-03-15','2021-03-17',50000,3,4,2),
(4,'2021-01-14','2021-01-18',100000,7,5,5),
(5,'2021-07-14','2021-07-15',0,7,2,6),
(6,'2021-06-01','2021-06-03',0,7,7,6),
(7,'2021-09-02','2021-09-05',100000,7,4,4),
(8,'2021-06-17','2021-06-18',150000,3,4,1),
(9,'2020-11-19','2020-11-19',0,3,4,3),
(10,'2021-04-12','2021-04-14',0,10,3,5),
(11,'2021-04-25','2021-04-25',0,2,2,1),
(12,'2021-05-25','2021-05-27',0,7,10,1);

INSERT INTO hop_dong_chi_tiet VALUES
(1,2,4,5),
(2,2,5,8),
(3,2,6,15),
(4,3,1,1),
(5,3,2,11),
(6,1,3,1),
(7,1,2,2),
(8,12,2,2);


# Câu 1 Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
SELECT *
FROM nhan_vien
WHERE (ho_ten LIKE 'H%' 
    OR ho_ten LIKE 'T%' 
    OR ho_ten LIKE 'K%')
AND LENGTH(ho_ten) <= 15;

# câu 2 Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
SELECT *
FROM khach_hang
WHERE 
    TIMESTAMPDIFF(YEAR, ngay_sinh, CURDATE()) BETWEEN 18 AND 50
AND (
    dia_chi LIKE '%Đà Nẵng%' 
    OR dia_chi LIKE '%Quảng Trị%'
);

# câu 3 Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng. Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
SELECT 
    kh.ma_khach_hang,
    kh.ho_ten,
    lk.ten_loai_khach,
    COUNT(hd.ma_hop_dong) AS so_lan_dat_phong
FROM khach_hang kh
JOIN loai_khach lk 
    ON kh.ma_loai_khach = lk.ma_loai_khach
LEFT JOIN hop_dong hd 
    ON kh.ma_khach_hang = hd.ma_khach_hang
WHERE lk.ten_loai_khach = 'Diamond'
GROUP BY kh.ma_khach_hang, kh.ho_ten, lk.ten_loai_khach
ORDER BY so_lan_dat_phong ASC;

# câu 4 Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien
SELECT
    kh.ma_khach_hang,
    kh.ho_ten,
    lk.ten_loai_khach,
    hd.ma_hop_dong,
    dv.ten_dich_vu,
    hd.ngay_lam_hop_dong,
    hd.ngay_ket_thuc,
    (dv.chi_phi_thue + IFNULL(SUM(hdct.so_luong * dvdk.gia),0)) AS tong_tien
FROM khach_hang kh
JOIN loai_khach lk 
    ON kh.ma_loai_khach = lk.ma_loai_khach
JOIN hop_dong hd 
    ON kh.ma_khach_hang = hd.ma_khach_hang
JOIN dich_vu dv 
    ON hd.ma_dich_vu = dv.ma_dich_vu
LEFT JOIN hop_dong_chi_tiet hdct 
    ON hd.ma_hop_dong = hdct.ma_hop_dong
LEFT JOIN dich_vu_di_kem dvdk 
    ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
GROUP BY 
    kh.ma_khach_hang,
    kh.ho_ten,
    lk.ten_loai_khach,
    hd.ma_hop_dong,
    dv.ten_dich_vu,
    hd.ngay_lam_hop_dong,
    hd.ngay_ket_thuc,
    dv.chi_phi_thue;
    
# Câu 5 Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3).
SELECT 
    dv.ma_dich_vu,
    dv.ten_dich_vu,
    dv.dien_tich,
    dv.chi_phi_thue,
    ldv.ten_loai_dich_vu
FROM dich_vu dv
JOIN loai_dich_vu ldv 
    ON dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
LEFT JOIN hop_dong hd 
    ON dv.ma_dich_vu = hd.ma_dich_vu
    AND hd.ngay_lam_hop_dong BETWEEN '2021-01-01' AND '2021-03-31'
WHERE hd.ma_hop_dong IS NULL;

#  câu 6 Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021.

SELECT 
    dv.ma_dich_vu,
    dv.ten_dich_vu,
    dv.dien_tich,
    dv.so_nguoi_toi_da,
    dv.chi_phi_thue,
    ldv.ten_loai_dich_vu
FROM dich_vu dv
JOIN loai_dich_vu ldv
    ON dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
WHERE dv.ma_dich_vu IN (
    SELECT ma_dich_vu
    FROM hop_dong
    WHERE YEAR(ngay_lam_hop_dong) = 2020
)
AND dv.ma_dich_vu NOT IN (
    SELECT ma_dich_vu
    FROM hop_dong
    WHERE YEAR(ngay_lam_hop_dong) = 2021
);

# câu 7 
SELECT ho_ten
FROM khach_hang
GROUP BY ho_ten;

# câu 8 
SELECT 
    MONTH(ngay_lam_hop_dong) AS thang,
    COUNT(DISTINCT ma_khach_hang) AS so_khach_hang_dat_phong
FROM hop_dong
WHERE YEAR(ngay_lam_hop_dong) = 2021
GROUP BY MONTH(ngay_lam_hop_dong)
ORDER BY thang;

# câu 9
SELECT 
    hd.ma_hop_dong,
    hd.ngay_lam_hop_dong,
    hd.ngay_ket_thuc,
    hd.tien_dat_coc,
    IFNULL(SUM(hdct.so_luong), 0) AS so_luong_dich_vu_di_kem
FROM hop_dong hd
LEFT JOIN hop_dong_chi_tiet hdct 
    ON hd.ma_hop_dong = hdct.ma_hop_dong
GROUP BY 
    hd.ma_hop_dong,
    hd.ngay_lam_hop_dong,
    hd.ngay_ket_thuc,
    hd.tien_dat_coc
ORDER BY hd.ma_hop_dong;

# câu 10 
SELECT DISTINCT
    dvdk.ma_dich_vu_di_kem,
    dvdk.ten_dich_vu_di_kem,
    dvdk.gia,
    dvdk.don_vi,
    dvdk.trang_thai
FROM khach_hang kh
JOIN loai_khach lk 
    ON kh.ma_loai_khach = lk.ma_loai_khach
JOIN hop_dong hd 
    ON kh.ma_khach_hang = hd.ma_khach_hang
JOIN hop_dong_chi_tiet hdct 
    ON hd.ma_hop_dong = hdct.ma_hop_dong
JOIN dich_vu_di_kem dvdk 
    ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
WHERE lk.ten_loai_khach = 'Diamond'
AND (
    kh.dia_chi LIKE '%Vinh%' 
    OR kh.dia_chi LIKE '%Quảng Ngãi%'
);

# câu 11
SELECT 
    hd.ma_hop_dong,
    nv.ho_ten AS ho_ten_nhan_vien,
    kh.ho_ten AS ho_ten_khach_hang,
    kh.so_dien_thoai,
    dv.ten_dich_vu,
    IFNULL(SUM(hdct.so_luong), 0) AS so_luong_dich_vu_di_kem,
    hd.tien_dat_coc
FROM hop_dong hd
JOIN nhan_vien nv 
    ON hd.ma_nhan_vien = nv.ma_nhan_vien
JOIN khach_hang kh 
    ON hd.ma_khach_hang = kh.ma_khach_hang
JOIN dich_vu dv 
    ON hd.ma_dich_vu = dv.ma_dich_vu
LEFT JOIN hop_dong_chi_tiet hdct 
    ON hd.ma_hop_dong = hdct.ma_hop_dong
WHERE YEAR(hd.ngay_lam_hop_dong) = 2020
AND MONTH(hd.ngay_lam_hop_dong) IN (10,11,12)
AND hd.ma_dich_vu NOT IN (
    SELECT ma_dich_vu
    FROM hop_dong
    WHERE YEAR(ngay_lam_hop_dong) = 2021
      AND MONTH(ngay_lam_hop_dong) BETWEEN 1 AND 6
)
GROUP BY 
    hd.ma_hop_dong,
    nv.ho_ten,
    kh.ho_ten,
    kh.so_dien_thoai,
    dv.ten_dich_vu,
    hd.tien_dat_coc
ORDER BY hd.ma_hop_dong;

# câu 12
SELECT 
    dvdk.ma_dich_vu_di_kem,
    dvdk.ten_dich_vu_di_kem,
    dvdk.gia,
    dvdk.don_vi,
    dvdk.trang_thai,
    SUM(hdct.so_luong) AS tong_so_luong_su_dung
FROM dich_vu_di_kem dvdk
JOIN hop_dong_chi_tiet hdct
    ON dvdk.ma_dich_vu_di_kem = hdct.ma_dich_vu_di_kem
GROUP BY 
    dvdk.ma_dich_vu_di_kem,
    dvdk.ten_dich_vu_di_kem,
    dvdk.gia,
    dvdk.don_vi,
    dvdk.trang_thai
HAVING SUM(hdct.so_luong) = (
    SELECT MAX(tong_so_luong)
    FROM (
        SELECT SUM(so_luong) AS tong_so_luong
        FROM hop_dong_chi_tiet
        GROUP BY ma_dich_vu_di_kem
    ) AS temp
);

# câu 13
SELECT 
    hd.ma_hop_dong,
    ldv.ten_loai_dich_vu,
    dvdk.ten_dich_vu_di_kem,
    1 AS so_lan_su_dung
FROM hop_dong_chi_tiet hdct
JOIN hop_dong hd
    ON hdct.ma_hop_dong = hd.ma_hop_dong
JOIN dich_vu dv
    ON hd.ma_dich_vu = dv.ma_dich_vu
JOIN loai_dich_vu ldv
    ON dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
JOIN dich_vu_di_kem dvdk
    ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
WHERE hdct.ma_dich_vu_di_kem IN (
    SELECT ma_dich_vu_di_kem
    FROM hop_dong_chi_tiet
    GROUP BY ma_dich_vu_di_kem
    HAVING COUNT(*) = 1
);
