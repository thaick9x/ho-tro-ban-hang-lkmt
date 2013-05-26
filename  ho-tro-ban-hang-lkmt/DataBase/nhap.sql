-- -----------------------------------------------------
-- Thêm vào thông tin đăng nhập
-- -----------------------------------------------------
insert into nhom values ('nvgd', 0),  ('nvkho', 0)
-- -----------------------------------------------------
-- Thêm vào thông tin đăng nhập
-- -----------------------------------------------------
insert into thong_tin_dang_nhap (user, pass, manhomquyen) values 
('trung', 'trung', 'nvgd'),
('anh', 'anh', 'nvkho')
-- -----------------------------------------------------
-- Thêm vào bảng thông tin nhân viên
-- -----------------------------------------------------
thong_tin_nhan_vieninsert into thong_tin_nhan_vien (ten_nv, dia_chi_nv, CMND_nv, ngay_vao_lam, ngay_sinh, gioi_tinh_nv, user_id) values 
( 'Đoàn Trịnh Trọng Trung', '441/86 Điện Biên Phủ, p 25, q Bình Thạnh, Tp HCM', '024543006', '2013-05-23',  '1992-02-13', 'nam', 1), 
('Lê Công Ánh', 'địa chỉ', 'CMND', '2013-05-23', '1992-05-18', 'nam', 2);


-- -----------------------------------------------------
-- Thêm vào loại lk
-- -----------------------------------------------------
insert into loai_lk values
('cpu', '');

insert into loai_lk values
('mainboard', '');

insert into loai_lk values
('hdd', '');

insert into loai_lk values
('ram', '');

insert into loai_lk values
('vga', '');

insert into loai_lk values
('psu', '');

insert into loai_lk values
('monitor', '');

insert into loai_lk values
('cooling liquid', '');

insert into loai_lk values
('mouse', '');

insert into loai_lk values
('mouse pad', '');



insert into loai_lk values
('keyboard', '');

insert into loai_lk values
('network card', '');

insert into loai_lk values
('audio card', '');

insert into loai_lk values
('heat sink', '');

insert into loai_lk values
('case', '');

insert into loai_lk values
('speaker', '');
-- -----------------------------------------------------
-- Thêm vào thông tin linh kiện
-- -----------------------------------------------------
insert into thong_tin_lk (ten_lk, gia_niem_yet, bao_hanh, nha_san_xuat, loai_lk, luot_truy_cap, tinh_trang, so_luong) values
('Intel Core i3-3240T', 3000000, 24, 'Intel', 'cpu', 0, 'new', 5),
('Intel Core i3-3240', 2800000, 24, 'Intel', 'cpu', 0, 'new', 5),
('Intel Core i5-3439Y Processor', 5200000, 24, 'intel', 'cpu', 0, 'new', 5),
('Intel® Core i5-3210M Processor rPGA', 5700000, 24, 'Intel', 'cpu', 0, 'new', 5),
('Intel® Core™ i7-3770K Processor', 7200000, 24, 'Intel', 'cpu', 0, 'new', 5)
-- -----------------------------------------------------
-- Thêm vào thông tin nhập kho
-- -----------------------------------------------------
insert into thong_tin_nhap_kho (ma_nv, ngay_nhap, chi_phi_nhap) values 
(2, '2013-05-23', 20000000)
-- -----------------------------------------------------
-- Thêm vào chi tiết nhap kho
-- -----------------------------------------------------
insert into chi_tiet_nhap_kho (ma_nk, ma_ten_lk, so_luong, gia_nhap) values
(1, 1, 5, 2500000)