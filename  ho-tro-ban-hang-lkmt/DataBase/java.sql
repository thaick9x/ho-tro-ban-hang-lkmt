SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Loai_LK`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Loai_LK` (
  `Loai_LK` VARCHAR(10) NOT NULL ,
  `XmlTTKT` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`Loai_LK`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_Tin_LK`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_Tin_LK` (
  `Ma_Ten_LK` INT NOT NULL AUTO_INCREMENT ,
  `Ten_LK` VARCHAR(255) NOT NULL ,
  `Gia_Niem_Yet` DECIMAL(9) NOT NULL ,
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
    REFERENCES `mydb`.`Loai_LK` (`Loai_LK` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Nhom`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Nhom` (
  `Nhom_NV` VARCHAR(10) NOT NULL ,
  `Tham_Chieu_Q` SMALLINT NOT NULL ,
  PRIMARY KEY (`Nhom_NV`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_Tin_Dang_Nhap`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_Tin_Dang_Nhap` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `user` VARCHAR(10) NOT NULL ,
  `pass` VARCHAR(25) NOT NULL ,
  `MaNhomQuyen` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`user_id`) ,
  INDEX `fk_ThongTinDangNhap_Nhom1_idx` (`MaNhomQuyen` ASC) ,
  CONSTRAINT `fk_ThongTinDangNhap_Nhom1`
    FOREIGN KEY (`MaNhomQuyen` )
    REFERENCES `mydb`.`Nhom` (`Nhom_NV` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_Tin_Nhan_Vien`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_Tin_Nhan_Vien` (
  `Ma_NV` INT NOT NULL AUTO_INCREMENT ,
  `Ten_NV` VARCHAR(45) NOT NULL ,
  `Dia_Chi_NV` VARCHAR(255) NOT NULL ,
  `CMND_NV` VARCHAR(15) NOT NULL ,
  `Ngay_Vao_Lam` DATE NOT NULL ,
  `Ngay_Sinh` DATE NOT NULL ,
  `Gioi_Tinh_NV` VARCHAR(3) NOT NULL ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`Ma_NV`) ,
  INDEX `fk_Thong_Tin_Nhan_Vien_Thong_Tin_Dang_Nhap1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Thong_Tin_Nhan_Vien_Thong_Tin_Dang_Nhap1`
    FOREIGN KEY (`user_id` )
    REFERENCES `mydb`.`Thong_Tin_Dang_Nhap` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_Tin_Nhap_Kho`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_Tin_Nhap_Kho` (
  `Ma_NK` INT NOT NULL AUTO_INCREMENT ,
  `Ma_NV` INT NOT NULL ,
  `Ngay_nhap` DATE NOT NULL ,
  `Chi_Phi_Nhap` DECIMAL(10) NOT NULL ,
  PRIMARY KEY (`Ma_NK`) ,
  INDEX `fk_Thong_Tin_Nhap_Kho_Thong_Tin_Nhan_Vien1_idx` (`Ma_NV` ASC) ,
  CONSTRAINT `fk_Thong_Tin_Nhap_Kho_Thong_Tin_Nhan_Vien1`
    FOREIGN KEY (`Ma_NV` )
    REFERENCES `mydb`.`Thong_Tin_Nhan_Vien` (`Ma_NV` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Chi_Tiet_Nhap_Kho`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Chi_Tiet_Nhap_Kho` (
  `Ma_CTNK` INT NOT NULL AUTO_INCREMENT ,
  `Ma_NK` INT NOT NULL ,
  `Ma_Ten_LK` INT NOT NULL ,
  `So_Luong` SMALLINT NOT NULL ,
  `Gia_Nhap` DECIMAL(9) NOT NULL ,
  PRIMARY KEY (`Ma_CTNK`) ,
  INDEX `fk_ChiTietNhapKho_ThongTinLK1_idx` (`Ma_Ten_LK` ASC) ,
  CONSTRAINT `fk_ChiTietNhapKho_ThongTinNhapKho1`
    FOREIGN KEY (`Ma_NK` )
    REFERENCES `mydb`.`Thong_Tin_Nhap_Kho` (`Ma_NK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ChiTietNhapKho_ThongTinLK1`
    FOREIGN KEY (`Ma_Ten_LK` )
    REFERENCES `mydb`.`Thong_Tin_LK` (`Ma_Ten_LK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Chi_Tiet_Linh_Kien`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Chi_Tiet_Linh_Kien` (
  `Ma_LK` INT NOT NULL AUTO_INCREMENT ,
  `Ma_Ten_LK` INT NOT NULL ,
  `Trang_Thai` VARCHAR(20) NOT NULL ,
  `IMEI` VARCHAR(255) NOT NULL ,
  `Ma_CTNK` INT NOT NULL ,
  PRIMARY KEY (`Ma_LK`) ,
  UNIQUE INDEX `IMEI_UNIQUE` (`IMEI` ASC) ,
  INDEX `fk_Chi_Tiet_Linh_Kien_Chi_Tiet_Nhap_Kho1_idx` (`Ma_CTNK` ASC) ,
  CONSTRAINT `fk_ChiPhiLinhKien_ThongTinLK`
    FOREIGN KEY (`Ma_Ten_LK` )
    REFERENCES `mydb`.`Thong_Tin_LK` (`Ma_Ten_LK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Chi_Tiet_Linh_Kien_Chi_Tiet_Nhap_Kho1`
    FOREIGN KEY (`Ma_CTNK` )
    REFERENCES `mydb`.`Chi_Tiet_Nhap_Kho` (`Ma_CTNK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_Tin_Khach_Hang`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_Tin_Khach_Hang` (
  `Ma_KH` INT NOT NULL AUTO_INCREMENT ,
  `Ten_KH` VARCHAR(45) NOT NULL ,
  `Dia_Chi_KH` VARCHAR(255) NOT NULL ,
  `SDT_KH` VARCHAR(20) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `user_id` INT NULL ,
  PRIMARY KEY (`Ma_KH`) ,
  UNIQUE INDEX `SDTKH_UNIQUE` (`SDT_KH` ASC) ,
  INDEX `fk_Thong_Tin_Khach_Hang_Thong_Tin_Dang_Nhap1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Thong_Tin_Khach_Hang_Thong_Tin_Dang_Nhap1`
    FOREIGN KEY (`user_id` )
    REFERENCES `mydb`.`Thong_Tin_Dang_Nhap` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_tin_gio_hang`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_tin_gio_hang` (
  `ma_gio` INT NOT NULL AUTO_INCREMENT ,
  `ma_KH` INT NOT NULL ,
  `trang_thai` VARCHAR(10) NOT NULL ,
  `quay_xl` INT NULL ,
  PRIMARY KEY (`ma_gio`) ,
  INDEX `fk_Thong_tin_gio_hang_Thong_Tin_Khach_Hang1_idx` (`ma_KH` ASC) ,
  CONSTRAINT `fk_Thong_tin_gio_hang_Thong_Tin_Khach_Hang1`
    FOREIGN KEY (`ma_KH` )
    REFERENCES `mydb`.`Thong_Tin_Khach_Hang` (`Ma_KH` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_Tin_HD`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_Tin_HD` (
  `ma_HD` INT NOT NULL AUTO_INCREMENT ,
  `Ngay_lap_HD` DATE NOT NULL ,
  `Don_Gia` DECIMAL(10) NOT NULL ,
  `Ma_NV` INT NOT NULL ,
  `Ma_KH` INT NOT NULL ,
  `Ma_GIo` INT NOT NULL ,
  INDEX `fk_Thong_Tin_HD_Thong_Tin_Nhan_Vien1_idx` (`Ma_NV` ASC) ,
  INDEX `fk_Thong_Tin_HD_Thong_Tin_Khach_Hang1_idx` (`Ma_KH` ASC) ,
  PRIMARY KEY (`ma_HD`) ,
  INDEX `fk_Thong_Tin_HD_Thong_tin_gio_hang1_idx` (`Ma_GIo` ASC) ,
  CONSTRAINT `fk_Thong_Tin_HD_Thong_Tin_Nhan_Vien1`
    FOREIGN KEY (`Ma_NV` )
    REFERENCES `mydb`.`Thong_Tin_Nhan_Vien` (`Ma_NV` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Thong_Tin_HD_Thong_Tin_Khach_Hang1`
    FOREIGN KEY (`Ma_KH` )
    REFERENCES `mydb`.`Thong_Tin_Khach_Hang` (`Ma_KH` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Thong_Tin_HD_Thong_tin_gio_hang1`
    FOREIGN KEY (`Ma_GIo` )
    REFERENCES `mydb`.`Thong_tin_gio_hang` (`ma_gio` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Chi_Tiet_HD`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Chi_Tiet_HD` (
  `MaCTHD` INT NOT NULL AUTO_INCREMENT ,
  `Ma_LK` INT NOT NULL ,
  `Ma_HD` INT NOT NULL ,
  `Gia_trong_HD` DECIMAL(9) NOT NULL ,
  PRIMARY KEY (`MaCTHD`) ,
  INDEX `fk_Chi_Tiet_HD_Thong_Tin_HD1_idx` (`Ma_HD` ASC) ,
  CONSTRAINT `fk_ChiTietHD_ChiTietLinhKien1`
    FOREIGN KEY (`Ma_LK` )
    REFERENCES `mydb`.`Chi_Tiet_Linh_Kien` (`Ma_LK` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Chi_Tiet_HD_Thong_Tin_HD1`
    FOREIGN KEY (`Ma_HD` )
    REFERENCES `mydb`.`Thong_Tin_HD` (`ma_HD` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Quyen`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Quyen` (
  `Ma_Quyen` INT NOT NULL ,
  `Ten_Quyen` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`Ma_Quyen`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`chi_tiet_gio_hang`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`chi_tiet_gio_hang` (
  `ma_ctgh` INT NOT NULL AUTO_INCREMENT ,
  `ma_gio` INT NOT NULL ,
  `ma_ten_lk` INT NOT NULL ,
  `so_luong_dat` INT NOT NULL ,
  PRIMARY KEY (`ma_ctgh`) ,
  INDEX `fk_chi_tiet_gio_hang_Thong_tin_gio_hang1_idx` (`ma_gio` ASC) ,
  INDEX `fk_chi_tiet_gio_hang_Thong_Tin_LK1_idx` (`ma_ten_lk` ASC) ,
  CONSTRAINT `fk_chi_tiet_gio_hang_Thong_tin_gio_hang1`
    FOREIGN KEY (`ma_gio` )
    REFERENCES `mydb`.`Thong_tin_gio_hang` (`ma_gio` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chi_tiet_gio_hang_Thong_Tin_LK1`
    FOREIGN KEY (`ma_ten_lk` )
    REFERENCES `mydb`.`Thong_Tin_LK` (`Ma_Ten_LK` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Thong_tin_xuat_kho`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Thong_tin_xuat_kho` (
  `ma_xl` INT NOT NULL AUTO_INCREMENT ,
  `ngay_xk` DATE NOT NULL ,
  `ma_gio` INT NOT NULL ,
  `ma_lk` INT NOT NULL ,
  PRIMARY KEY (`ma_xl`) ,
  INDEX `fk_Thong_tin_xuat_kho_Thong_tin_gio_hang1_idx` (`ma_gio` ASC) ,
  INDEX `fk_Thong_tin_xuat_kho_Chi_Tiet_Linh_Kien1_idx` (`ma_lk` ASC) ,
  CONSTRAINT `fk_Thong_tin_xuat_kho_Thong_tin_gio_hang1`
    FOREIGN KEY (`ma_gio` )
    REFERENCES `mydb`.`Thong_tin_gio_hang` (`ma_gio` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Thong_tin_xuat_kho_Chi_Tiet_Linh_Kien1`
    FOREIGN KEY (`ma_lk` )
    REFERENCES `mydb`.`Chi_Tiet_Linh_Kien` (`Ma_LK` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
