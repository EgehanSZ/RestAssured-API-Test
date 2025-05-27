
# API Test Otomasyonu – RestAssured ile Java Test Projesi

Bu proje, Java programlama dili ve [RestAssured](https://rest-assured.io/) kütüphanesi kullanılarak HTTP tabanlı API'lerin otomatik testlerinin gerçekleştirilmesini amaçlar. Projede `JUnit` test çatısı kullanılmıştır.

## 🔍 Amaç

Bu projede örnek olarak iki adet test yapılmaktadır:

- `POST /login`: Giriş işlemi testi (başarılı giriş senaryosu).
- `GET /listings/:id`: Belirli bir ilanı getirme testi.

Her iki test de:
- Dönen yanıtın doğruluğunu kontrol eder.
- Yanıt süresinin 1 saniyenin altında olup olmadığını ölçer.
- Konsola ayrıntılı loglar ve yanıt çıktısı basar.

## 🛠 Kullanılan Teknolojiler

- Java 11+
- [RestAssured](https://rest-assured.io/)
- JUnit 4
- Maven/Gradle (bağımlılık yönetimi için)
- IntelliJ IDEA (opsiyonel geliştirme ortamı)

## 🚀 Nasıl Çalıştırılır?

### 1. Projeyi klonlayın:
```bash
git clone https://github.com/kullaniciadi/restassured-api-testing.git
cd restassured-api-testing
```

### 2. Bağımlılıkları yükleyin
Maven kullanıyorsanız:

```bash
mvn clean install
```

Gradle kullanıyorsanız:

```bash
./gradlew build
```

### 3. Testleri çalıştırın

```bash
mvn test
```

veya

```bash
./gradlew test
```

## 📁 Test Senaryoları

### ✅ POST `/login`

```json
{
  "kullaniciId": "xxxxxxxxxxx",
  "sifre": "xxx"
}
```

**Başarılı senaryo:**
- HTTP 200 dönmesi beklenir.
- `message` alanı "Giriş Başarılı" olmalıdır.
- `token` alanı null olmamalıdır.
- Yanıt süresi 1000 ms'den kısa olmalıdır.

### ✅ GET `/listings/:id`

- Geçerli bir ilan ID’si gönderilerek test edilir.
- Dönen veri içinde ilan bilgileri doğrulanır.
- HTTP 200 kontrolü yapılır.

## 📸 Örnek Çıktı

```
POST /login isteği süresi: 187 ms
Yanıt:
{
  "message": "Giriş Başarılı",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

## 📌 Notlar

- API sunucunuzun çalışır durumda olması gerekir (`http://localhost:3000` varsayılan olarak kullanılmıştır).
- `JWT_SECRET` gibi çevre değişkenlerinin backend tarafında tanımlı olması gerekir.
- Testlerde kullanılan `kullaniciId` ve `sifre` gerçek sistemde tanımlı olmalıdır.

## 🤝 Katkıda Bulunma

Katkıda bulunmak isterseniz lütfen `pull request` açın veya issue oluşturun.

## 🪪 Lisans

MIT License
