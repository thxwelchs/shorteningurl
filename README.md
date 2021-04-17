# URL Shortening Service

### 구현 리스트
- [x] URL 입력폼 제공 및 결과 출력
- [x] URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
- [x] 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
- [x] 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
- [x] Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.

# How to run or use
1. this project clone to your local
    ```shell
    git clone 
    cd shorteningurl
    ```

2. if you want to run the tests
    ```shell
    ./gradlew clean test
    ```

3. application build and run in your local (dev mode)
    ```shell
    ./gradlew bootRun
    ```

4. request to shorten url (application must be running)
   
    __default port__: `8082`\
    __http method__: `POST`\
    __http header Content-type__: `application/json`\
    __api endpoint url__: `http://localhost:{default port}/api/shortening-urls`\
    __http request body(raw text)__: `{to shorten url}`\
    Use curl
    ```shell
    curl -s -X POST -H "Content-type: application/json" http://localhost:8082/api/shortening-urls -d "https://www.google.com"
    ```
    ![img](https://user-images.githubusercontent.com/38197077/115124334-8f51a680-9ffc-11eb-88bb-a381119c7272.png)

5. request to unshorten url (application must be running and have shortenedURL)

    `Enter the shortenedURL in your browser`



