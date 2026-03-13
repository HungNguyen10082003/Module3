CREATE DATABASE QuanLyBanHang;
USE QuanLyBanHang;
CREATE TABLE Customer(
    cID INT AUTO_INCREMENT PRIMARY KEY,
    cName VARCHAR(100),
    cAge INT
);
CREATE TABLE Product(
    pID INT AUTO_INCREMENT PRIMARY KEY,
    pName VARCHAR(100),
    pPrice DOUBLE
);
CREATE TABLE Orders(
    oID INT AUTO_INCREMENT PRIMARY KEY,
    cID INT,
    oDate DATE,
    oTotalPrice DOUBLE,
    FOREIGN KEY (cID) REFERENCES Customer(cID)
);
CREATE TABLE OrderDetail(
    oID INT,
    pID INT,
    oQTY INT,
    PRIMARY KEY(oID, pID),
    FOREIGN KEY (oID) REFERENCES Orders(oID),
    FOREIGN KEY (pID) REFERENCES Product(pID)
);
INSERT INTO Customer(cName,cAge)
VALUES
('An',25),
('Binh',30);

INSERT INTO Product(pName,pPrice)
VALUES
('Laptop',1500),
('Mouse',20);

INSERT INTO Orders(cID,oDate,oTotalPrice)
VALUES
(1,'2026-03-13',1520);

INSERT INTO OrderDetail(oID,pID,oQTY)
VALUES
(1,1,1),
(1,2,1);
SELECT * FROM Customer;
SELECT * FROM Product;
SELECT * FROM Orders;
SELECT * FROM OrderDetail;