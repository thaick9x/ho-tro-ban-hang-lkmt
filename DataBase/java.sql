SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `java` ;

-- -----------------------------------------------------
-- Table `java`.`Loai_LK`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Loai_LK` (
  `Loai_LK` VARCHAR(10) NOT NULL ,
  `XmlTTKT` TEXT NOT NULL ,
  PRIMARY KEY (`Loai_LK`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Thong_Tin_LK`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Thong_Tin_LK` (
  `Ma_Ten_LK` INT NOT NULL ,
  `Ten_LK` VARCHAR(100) NOT NULL ,
  `Gia_Niem_Yet` DECIMAL(8,2) NOT NULL ,
  `Bao_Hanh` SMALLINT NOT NULL ,
  `Nha_San_Xuat` VARCHAR(45) NOT NULL ,
  `Loai_LK` VARCHAR(10) NOT NULL ,
  `Luot_Truy_Cap` SMALLINT NOT NULL ,
  `Tinh_Trang` VARCHAR(45) NOT NULL ,
  `So_Luong` INT NOT NULL ,
  PRIMARY KEY (`Ma_Ten_LK`) ,
  UNIQUE INDEX `TenLK_UNIQUE` (`Ten_LK` ASC) ,
  INDEX `fk_ThongTinLK_LoaiLK1_idx` (`Loai_LK` ASC) ,
  CONSTRAINT `fk_ThongTinLK_LoaiLK1`
    FOREIGN KEY (`Loai_LK` )
    REFERENCES `java`.`Loai_LK` (`Loai_LK` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Thong_Tin_Nhan_Vien`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Thong_Tin_Nhan_Vien` (
  `Ma_NV` INT NOT NULL ,
  `Ten_NV` VARCHAR(45) NULL ,
  `Dia_Chi_NV` VARCHAR(45) NULL ,
  `CMND_NV` VARCHAR(15) NULL ,
  `Ngay_Vao_Lam` DATE NULL ,
  `Ngay_Sinh` DATE NULL ,
  `Gioi_Tinh_NV` VARCHAR(3) NULL ,
  PRIMARY KEY (`Ma_NV`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Thong_Tin_Nhap_Kho`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Thong_Tin_Nhap_Kho` (
  `Ma_NK` INT NOT NULL ,
  `Ma_NV` INT NOT NULL ,
  `Ngay_nhap` VARCHAR(45) NOT NULL ,
  `Chi_Phi_Nhap` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Ma_NK`) ,
  INDEX `fk_Thong_Tin_Nhap_Kho_Thong_Tin_Nhan_Vien1_idx` (`Ma_NV` ASC) ,
  CONSTRAINT `fk_Thong_Tin_Nhap_Kho_Thong_Tin_Nhan_Vien1`
    FOREIGN KEY (`Ma_NV` )
    REFERENCES `java`.`Thong_Tin_Nhan_Vien` (`Ma_NV` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Chi_Tiet_Nhap_Kho`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Chi_Tiet_Nhap_Kho` (
  `Ma_CTNK` INT NOT NULL ,
  `Ma_NK` INT NOT NULL ,
  `Ma_Ten_LK` INT NOT NULL ,
  `So_Luong` SMALLINT NOT NULL ,
  `Gia_Nhap` VARCHAR(45) NULL ,
  PRIMARY KEY (`Ma_CTNK`) ,
  INDEX `fk_ChiTietNhapKho_ThongTinLK1_idx` (`Ma_Ten_LK` ASC) ,
  CONSTRAINT `fk_ChiTietNhapKho_ThongTinNhapKho1`
    FOREIGN KEY (`Ma_NK` )
    REFERENCES `java`.`Thong_Tin_Nhap_Kho` (`Ma_NK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ChiTietNhapKho_ThongTinLK1`
    FOREIGN KEY (`Ma_Ten_LK` )
    REFERENCES `java`.`Thong_Tin_LK` (`Ma_Ten_LK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Chi_Tiet_Linh_Kien`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Chi_Tiet_Linh_Kien` (
  `Ma_LK` INT NOT NULL ,
  `Ma_Ten_LK` INT NOT NULL ,
  `Trang_Thai` VARCHAR(20) NOT NULL ,
  `IMEI` TEXT NOT NULL ,
  `Ma_CTNK` INT NOT NULL ,
  PRIMARY KEY (`Ma_LK`) ,
  UNIQUE INDEX `MaTenLK_UNIQUE` (`Ma_Ten_LK` ASC) ,
  UNIQUE INDEX `IMEI_UNIQUE` (`IMEI` ASC) ,
  INDEX `fk_Chi_Tiet_Linh_Kien_Chi_Tiet_Nhap_Kho1_idx` (`Ma_CTNK` ASC) ,
  CONSTRAINT `fk_ChiPhiLinhKien_ThongTinLK`
    FOREIGN KEY (`Ma_Ten_LK` )
    REFERENCES `java`.`Thong_Tin_LK` (`Ma_Ten_LK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Chi_Tiet_Linh_Kien_Chi_Tiet_Nhap_Kho1`
    FOREIGN KEY (`Ma_CTNK` )
    REFERENCES `java`.`Chi_Tiet_Nhap_Kho` (`Ma_CTNK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Chi_Tiet_HD`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Chi_Tiet_HD` (
  `MaCTGD` INT NOT NULL ,
  `Ma_LK` INT NOT NULL ,
  `Ma_HD` INT NOT NULL ,
  `Gia_GD` DECIMAL(8,2) NOT NULL ,
  PRIMARY KEY (`MaCTGD`) ,
  CONSTRAINT `fk_ChiTietHD_ChiTietLinhKien1`
    FOREIGN KEY (`Ma_LK` )
    REFERENCES `java`.`Chi_Tiet_Linh_Kien` (`Ma_LK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Thong_Tin_GD`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Thong_Tin_GD` (
  `Ma_HD` INT NOT NULL ,
  `Ngay_GD` DATE NOT NULL ,
  `Don_Gia` DECIMAL(8,2) NOT NULL ,
  `Ma_NV` VARCHAR(10) NOT NULL ,
  `Ma_KH` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`Ma_HD`) ,
  CONSTRAINT `fk_ThongTinGD_ChiTietGD1`
    FOREIGN KEY (`Ma_HD` )
    REFERENCES `java`.`Chi_Tiet_HD` (`Ma_LK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Thong_Tin_Khach_Hang`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Thong_Tin_Khach_Hang` (
  `Ma_KH` INT NOT NULL ,
  `Ten_KH` VARCHAR(45) NOT NULL ,
  `Dia_Chi_KH` VARCHAR(45) NOT NULL ,
  `SDT_KH` VARCHAR(20) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Ma_KH`) ,
  UNIQUE INDEX `SDTKH_UNIQUE` (`SDT_KH` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Nhom`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Nhom` (
  `Nhom_NV` VARCHAR(10) NOT NULL ,
  `Tham_Chieu_Q` SMALLINT NOT NULL ,
  PRIMARY KEY (`Nhom_NV`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Thong_Tin_Dang_Nhap`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Thong_Tin_Dang_Nhap` (
  `user_id` INT NOT NULL ,
  `user` VARCHAR(10) NOT NULL ,
  `pass` VARCHAR(25) NOT NULL ,
  `TT` VARCHAR(10) NOT NULL ,
  `MaNhomQuyen` VARCHAR(10) NULL ,
  INDEX `fk_ThongTinDangNhap_ThongTinKhachHang1_idx` (`TT` ASC) ,
  PRIMARY KEY (`user_id`) ,
  INDEX `fk_ThongTinDangNhap_Nhom1_idx` (`MaNhomQuyen` ASC) ,
  CONSTRAINT `fk_ThongTinDangNhap_ThongTinKhachHang1`
    FOREIGN KEY (`TT` )
    REFERENCES `java`.`Thong_Tin_Khach_Hang` (`Ma_KH` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ThongTinDangNhap_ThongTinNhanVien1`
    FOREIGN KEY (`TT` )
    REFERENCES `java`.`Thong_Tin_Nhan_Vien` (`Ma_NV` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ThongTinDangNhap_Nhom1`
    FOREIGN KEY (`MaNhomQuyen` )
    REFERENCES `java`.`Nhom` (`Nhom_NV` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java`.`Quyen`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `java`.`Quyen` (
  `Ma_Quyen` INT NOT NULL ,
  `Ten_Quyen` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`Ma_Quyen`) )
ENGINE = InnoDB;

USE `java` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
