# TestRetrofit

Dự án Android mẫu để tìm hiểu và thử nghiệm thư viện **Retrofit** trong việc tương tác với RESTful APIs.

## Các công nghệ sử dụng (Dự kiến)
- [Retrofit](https://square.github.io/retrofit/): HTTP client cho Android.
- Kotlin & Coroutines: Xử lý bất đồng bộ.
- OkHttp: Ghi log (Logging Interceptor) và quản lý mạng.
- Gson / Moshi / Kotlinx Serialization: Phân tích JSON.

## Yêu cầu môi trường
- **Android Studio** (phiên bản mới nhất được khuyến nghị)
- **JDK 17+**
- **Gradle**

## Hướng dẫn chạy dự án
1. Clone dự án về máy tính của bạn:
   ```bash
   git clone https://github.com/Fiwpr06/TestRetrofit.git
   ```
2. Mở dự án bằng **Android Studio**.
3. Đợi cho Gradle đồng bộ hóa (sync) xong các thư viện.
4. Kết nối với thiết bị Android thật hoặc khởi động Android Emulator.
5. Nhấn nút **Run** (`Shift + F10`) để build và chạy ứng dụng.

## Cấu trúc thư mục
- `app/`: Chứa toàn bộ mã nguồn của ứng dụng (UI, Network calls, Models,...).
- `gradle/`: Chứa các cấu hình của Gradle wrapper.
- `build.gradle.kts` / `settings.gradle.kts`: Các file cấu hình dependencies và build script.
