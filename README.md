﻿# Backend Clothes Application

Ứng dụng backend Spring Boot cho cửa hàng quần áo trực tuyến.

## Mô tả

Ứng dụng này cung cấp các API để quản lý các chức năng chính của một cửa hàng quần áo trực tuyến, bao gồm:

- **Quản lý người dùng:** Đăng ký, đăng nhập, thông tin người dùng.
- **Quản lý sản phẩm:** Thêm, sửa, xóa, xem danh sách sản phẩm.
- **Quản lý danh mục:** Thêm, sửa, xóa, xem danh sách danh mục.
- **Giỏ hàng:** Thêm, xóa, cập nhật sản phẩm trong giỏ hàng.
- **Đơn hàng:** Tạo, xem, cập nhật trạng thái đơn hàng.
- **Thanh toán:** Xử lý thanh toán.
- **Đánh giá và bình luận:** Cho phép người dùng đánh giá và bình luận về sản phẩm.
- **Danh sách yêu thích:** Quản lý danh sách sản phẩm yêu thích của người dùng.
- **Xác thực:** Sử dụng Spring Security OAuth2 Resource Server để bảo vệ API.

## Cấu trúc dự án
![Cấu trúc dự án](/src/main/resources/static/Assets/Images/img.png).

## link FontEnd:https://github.com/khathach699/shop_app_clothes 

## Công nghệ sử dụng

- **Spring Boot:** Framework chính để xây dựng ứng dụng.
- **Spring Data JPA:** Tương tác với cơ sở dữ liệu MySQL.
- **MySQL:** Hệ quản trị cơ sở dữ liệu quan hệ.
- **Lombok:** Giảm thiểu boilerplate code.
- **MapStruct:** Mapping giữa các đối tượng DTO và Entity.
- **Spring Security OAuth2 Resource Server:** Bảo vệ API bằng OAuth2.
- **Redis:** Lưu trữ dữ liệu phiên và cache.
- **Jackson:** Hỗ trợ Java 8 date/time.
- **Nimbus JOSE + JWT:** Xử lý JWT.
- **Spring Security Crypto:** Mã hóa mật khẩu.
- **Spring Boot Validation:** Xác thực dữ liệu.

## Cài đặt

1. **Cài đặt Java 21:** Đảm bảo bạn đã cài đặt Java 21 trên máy tính.
2. **Cài đặt MySQL:** Cài đặt và cấu hình MySQL.
3. **Cài đặt Redis:** Cài đặt và cấu hình Redis.
4. **Clone dự án:**
5. **git clone https://github.com/khathach699/backend_clothes**
   
