# spring-boot-haochih-demo
## 測試環境  
- Linux ubuntu 18(Oracle Cloud)
- arm64
- Open JDK:11.0.16

## Docker Demo頁面
[Swagger UI(open api 3.0)](http://150.230.57.141:19100/swagger-ui/index.html)
![image](./document/image/swagger.PNG)

## Quick Deploy 
1. 拉取專案並切換至專案根目錄
```
git checkout https://github.com/sdes5317/spring-boot-haochih-demo.git
cd spring-boot-haochih-demo
```
2. 打包jar
```
mvn package
```
3. 發布 docker image
```
sudo bash docker-image-build.sh
```
4. Run docker compose
```
sudo docker-compose up -d
```

## H2 Create Table SQL
```
CREATE TABLE CURRENCY(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    CODE VARCHAR(255) NOT NULL,
    CHINESE_NAME VARCHAR(255) NOT NULL
);
```

## Todo list
- [ ] 全域錯誤處理
- [ ] log紀錄
- [ ] 簡化佈署腳本
