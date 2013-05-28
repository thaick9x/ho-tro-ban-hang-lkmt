-- -----------------------------------------------------
-- Thêm vào thông tin đăng nhập
-- -----------------------------------------------------
insert into nhom values ('nvgd', 0),  
('nvkho', 0);
-- -----------------------------------------------------
-- Thêm vào thông tin đăng nhập
-- -----------------------------------------------------
insert into thong_tin_dang_nhap (user, pass, manhomquyen) values 
('trung', 'trung', 'nvgd'),
('anh', 'anh', 'nvkho');
-- -----------------------------------------------------
-- Thêm vào bảng thông tin nhân viên
-- -----------------------------------------------------
insert into thong_tin_nhan_vien (ten_nv, dia_chi_nv, CMND_nv, ngay_vao_lam, ngay_sinh, gioi_tinh_nv, user_id) values 
( 'Đoàn Trịnh Trọng Trung', '441/86 Điện Biên Phủ, p 25, q Bình Thạnh, Tp HCM', '024543006', '2013-05-23',  '1992-02-13', 'nam', 1), 
('Lê Công Ánh', 'địa chỉ', 'CMND', '2013-05-23', '1992-05-18', 'nam', 2);


-- -----------------------------------------------------
-- Thêm vào loại lk
-- -----------------------------------------------------
insert into loai_lk values
('cpu', 'd:\cpu.xml');

insert into loai_lk values
('mainboard', '');

insert into loai_lk values
('hdd', '');

insert into loai_lk values
('ram', 'd:\ram.xml');

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
('Intel® Core™ i7-3770K Processor', 7200000, 24, 'Intel', 'cpu', 0, 'new', 5),
('CORSAIR 1GB 200-Pin DDR2 SO-DIMM DDR2 667 (PC2 5300) Laptop Memory Model VS1GSDS667D2', 400000 , 12, 'corsair', 'ram', 0, 'new', 5),
('Mushkin Enhanced Essentials 8GB 204-Pin DDR3 SO-DIMM DDR3 1333 (PC3 10600) Laptop Memory Model 992020', 1500000 , 12, 'mushkin', 'ram', 0, 'new', 5),
('Kingston 4GB 240-Pin DDR3 SDRAM DDR3 1333 (PC3 10600) Desktop Memory Model KVR1333D3N9/4G', 800000 , 12, 'kingston', 'ram', 0, 'new', 5),
('G.SKILL 4GB 204-Pin DDR3 SO-DIMM DDR3 1600 (PC3 12800) Laptop Memory Model F3-12800CL11S-4GBSQ', 750000 , 12, 'gskill', 'ram', 0, 'new', 5),
('ADATA 8GB 204-Pin DDR3 SO-DIMM DDR3 1333 (PC3 10600) Laptop Memory Model AD3S1333W8G9-S', 2500000 , 12, 'adata', 'ram', 0, 'new', 5);
-- -----------------------------------------------------
-- Thêm vào thông tin nhập kho
-- -----------------------------------------------------
insert into thong_tin_nhap_kho (ma_nv, ngay_nhap, chi_phi_nhap) values 
(2, '2013-05-23', 20000000),
(2, '2013-05-27', 15000000);
-- -----------------------------------------------------
-- Thêm vào chi tiết nhap kho
-- -----------------------------------------------------
insert into chi_tiet_nhap_kho (ma_nk, ma_ten_lk, so_luong, gia_nhap) values
(1, 1, 5, 2500000),
(1, 2, 5, 2000000),
(1, 3, 5, 4000000),
(1, 4, 5, 4300000),
(1, 5, 5, 6100000), 
(2, 6, 5, 300000),
(2, 7, 5, 1000000),
(2, 8, 5, 600000),
(2, 9, 5, 500000),
(2, 10, 5, 2000000);  
 


