-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for qllinhkien
CREATE DATABASE IF NOT EXISTS `qllinhkien` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `qllinhkien`;

-- Dumping structure for table qllinhkien.chitiethoadon
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `soLuong` int(11) NOT NULL,
  `hoaDon` varchar(255) DEFAULT NULL,
  `maCTHD` varchar(255) NOT NULL,
  `maSP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maCTHD`),
  KEY `FKdejyx1d3hg6ibs3vet0vd6f4i` (`hoaDon`),
  KEY `FK6e48ooakrayatm2uq598i9a4x` (`maSP`),
  CONSTRAINT `FK6e48ooakrayatm2uq598i9a4x` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`),
  CONSTRAINT `FKdejyx1d3hg6ibs3vet0vd6f4i` FOREIGN KEY (`hoaDon`) REFERENCES `hoadon` (`maHD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.chitiethoadon: ~4 rows (approximately)
INSERT INTO `chitiethoadon` (`soLuong`, `hoaDon`, `maCTHD`, `maSP`) VALUES
	(10, 'HD01', 'CTHD01', 'SP01'),
	(5, 'HD01', 'CTHD02', 'SP02'),
	(8, 'HD02', 'CTHD03', 'SP03'),
	(12, 'HD02', 'CTHD04', 'SP04');

-- Dumping structure for table qllinhkien.hoadon
CREATE TABLE IF NOT EXISTS `hoadon` (
  `ngayTao` date DEFAULT NULL,
  `soLuongGiam` double NOT NULL,
  `thue` double NOT NULL,
  `maHD` varchar(255) NOT NULL,
  `maKH` varchar(255) DEFAULT NULL,
  `maNV` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maHD`),
  KEY `FKg1ebuq3tydt58wb4gutehmh9w` (`maKH`),
  KEY `FKoyaa1162ps1nyifmfv2pv2qr` (`maNV`),
  CONSTRAINT `FKg1ebuq3tydt58wb4gutehmh9w` FOREIGN KEY (`maKH`) REFERENCES `khachhang` (`maKH`),
  CONSTRAINT `FKoyaa1162ps1nyifmfv2pv2qr` FOREIGN KEY (`maNV`) REFERENCES `nhanvien` (`maNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.hoadon: ~2 rows (approximately)
INSERT INTO `hoadon` (`ngayTao`, `soLuongGiam`, `thue`, `maHD`, `maKH`, `maNV`) VALUES
	('2023-11-10', 8000, 0.02, 'HD01', 'KH01', 'NV01'),
	('2023-11-10', 10000, 0.02, 'HD02', 'KH02', 'NV02');

-- Dumping structure for table qllinhkien.khachhang
CREATE TABLE IF NOT EXISTS `khachhang` (
  `diemTichLuy` int(11) NOT NULL,
  `gioiTinh` bit(1) NOT NULL,
  `sdt` int(11) NOT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `maKH` varchar(255) NOT NULL,
  `tenKH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.khachhang: ~5 rows (approximately)
INSERT INTO `khachhang` (`diemTichLuy`, `gioiTinh`, `sdt`, `diaChi`, `maKH`, `tenKH`) VALUES
	(100, b'1', 9785568, 'Quận 2', 'KH01', 'Nguyễn Văn X'),
	(300, b'0', 978556, 'Quận 1', 'KH02', 'Trần Trung B'),
	(600, b'1', 978556, 'Gò Vấp', 'KH03', 'Trần Tấn C'),
	(400, b'0', 978556, 'Tân Bình', 'KH04', 'Trần Thị D'),
	(900, b'1', 978556, 'Quận 7', 'KH05', 'Văn Bé E');

-- Dumping structure for table qllinhkien.loainhanvien
CREATE TABLE IF NOT EXISTS `loainhanvien` (
  `loai` varchar(255) NOT NULL,
  PRIMARY KEY (`loai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.loainhanvien: ~2 rows (approximately)
INSERT INTO `loainhanvien` (`loai`) VALUES
	('nvbh'),
	('nvql');

-- Dumping structure for table qllinhkien.nhacungcap
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `diaChi` varchar(255) DEFAULT NULL,
  `maNCC` varchar(255) NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `tenNCC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.nhacungcap: ~5 rows (approximately)
INSERT INTO `nhacungcap` (`diaChi`, `maNCC`, `sdt`, `tenNCC`) VALUES
	('Số 117 Nguyễn Du, Phường Bến Thành, Quận 1, Thành Phố Hồ Chí Minh', 'NCC01', '02873026865', 'MSI'),
	('Số 1 Thái Hà, Phường Trung Liệt, Quận Đống Đa, Hà Nội', 'NCC02', '0901057057', 'Intel'),
	('677/2A Điện Biên Phủ, Phường 25, Quận Bình Thạnh, TP. Hồ Chí Minh', 'NCC03', '0868323832', 'ASUS'),
	('905 Kha Vạn Cân, Phường Linh Tây, TP. Thủ Đức', 'NCC04', '02839621368', 'AMD'),
	('162 - 164 Thái Hà, Phường Trung Liệt, Đống Đa, Hà Nội', 'NCC05', '02871068880', 'Gigabyte');

-- Dumping structure for table qllinhkien.nhanvien
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `gioiTinh` bit(1) NOT NULL,
  `sdt` int(11) NOT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `loai` varchar(255) DEFAULT NULL,
  `maNV` varchar(255) NOT NULL,
  `tenNV` varchar(255) DEFAULT NULL,
  `tenTK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maNV`),
  UNIQUE KEY `UK_b93wdpg68gvv4o3r6p1w1ashv` (`tenTK`),
  KEY `FKd1mbn4mwgudqkpmw169tld8aj` (`loai`),
  CONSTRAINT `FKd1mbn4mwgudqkpmw169tld8aj` FOREIGN KEY (`loai`) REFERENCES `loainhanvien` (`loai`),
  CONSTRAINT `FKothkxlr050n3f7kk8hxa6fs5w` FOREIGN KEY (`tenTK`) REFERENCES `taikhoan` (`tenTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.nhanvien: ~2 rows (approximately)
INSERT INTO `nhanvien` (`gioiTinh`, `sdt`, `diaChi`, `loai`, `maNV`, `tenNV`, `tenTK`) VALUES
	(b'1', 8123456, 'Gò Vấp', 'nvbh', 'NV01', 'Nguyễn Văn A', 'NV01'),
	(b'0', 7123456, 'Quận 1', 'nvql', 'NV02', 'Trần Thị B', 'NV02');

-- Dumping structure for table qllinhkien.sanpham
CREATE TABLE IF NOT EXISTS `sanpham` (
  `giaBan` double NOT NULL,
  `giaMua` double NOT NULL,
  `soLuongTon` int(11) NOT NULL,
  `maNCC` varchar(255) DEFAULT NULL,
  `maSP` varchar(255) NOT NULL,
  `tenSP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maSP`),
  KEY `FK88w6u2of9fyt7eqoqwso1creb` (`maNCC`),
  CONSTRAINT `FK88w6u2of9fyt7eqoqwso1creb` FOREIGN KEY (`maNCC`) REFERENCES `nhacungcap` (`maNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.sanpham: ~10 rows (approximately)
INSERT INTO `sanpham` (`giaBan`, `giaMua`, `soLuongTon`, `maNCC`, `maSP`, `tenSP`) VALUES
	(3190000, 3490000, 26, 'NCC01', 'SP01', 'Tản nhiệt nước MSI MAG CORELIQUID E360 WHITE'),
	(2990000, 3290000, 49, 'NCC01', 'SP02', 'Tản nhiệt nước MSI MAG CORELIQUID E360'),
	(2590000, 2890000, 38, 'NCC01', 'SP03', 'Tản nhiệt nước MSI MAG CORELIQUID E240 WHITE'),
	(2390000, 2690000, 11, 'NCC01', 'SP04', 'Tản nhiệt nước MSI MAG CORELIQUID E240'),
	(19190000, 20490000, 32, 'NCC01', 'SP05', 'Card Màn Hình VGA MSI RTX 4070 GAMING X SLIM 12G'),
	(15590000, 16490000, 56, 'NCC01', 'SP06', 'Card Màn Hình VGA MSI RTX 4060 Ti GAMING X SLIM WHITE 16G'),
	(15390000, 16490000, 79, 'NCC01', 'SP07', 'Card Màn Hình VGA MSI RTX 4060 Ti GAMING X SLIM 16G'),
	(14490000, 15590000, 8, 'NCC01', 'SP08', 'Card Màn Hình VGA MSI RTX 4060 Ti VENTUS 3X 16G OC'),
	(13990000, 15190000, 37, 'NCC01', 'SP09', 'Card Màn Hình VGA MSI RTX 4060 Ti VENTUS 2X BLACK 16G OC'),
	(9190000, 9890000, 62, 'NCC01', 'SP10', 'Card Màn Hình VGA MSI RTX 4060 VENTUS 2X WHITE 8G OC');

-- Dumping structure for table qllinhkien.taikhoan
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `matKhau` varchar(255) DEFAULT NULL,
  `tenTK` varchar(255) NOT NULL,
  PRIMARY KEY (`tenTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table qllinhkien.taikhoan: ~2 rows (approximately)
INSERT INTO `taikhoan` (`matKhau`, `tenTK`) VALUES
	('1111', 'NV01'),
	('1111', 'nv02');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
