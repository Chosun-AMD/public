# AMD Project - Public

## 기능  
 > - CRUD
 > - 파일 업로드

## 진행사항

## Version
> openjdk-11-jdk

## 테스트
> - http://localhost:8888/user/signup

## 참고 사항
> - port : 8888
> - h2-console : http://localhost:8888/h2-console
> - h2-console ID : sa
> - h2-console PW : None

## 진행사항
`localhost:8888/user/signup`으로 접속하게 되면 다음과 같은 화면이 나옵니다. 여기서 회원가입을 진행하게 되면 로그인 페이지로 이동하게 됩니다.
![image](https://user-images.githubusercontent.com/54488922/232457132-aef00ddc-3d08-4c6c-bec5-f60dc9252028.png)

`localhost:8888/user/login`으로 접속하게 되면 로그인 페이지가 나오는데 화면은 다음과 같습니다.
![image](https://user-images.githubusercontent.com/54488922/232457398-ea909cea-79d3-4af9-b293-4463eb7f0633.png)

로그인이 완료됬다면 `localhost:8888/dashboard`로 이동하게 되는데, `/dashboard`페이지는 권한이 없으면 접근되지 않습니다.
다음 사진은 dashboard페이지이며 추후 dashboard를 추가할 예정입니다.
![image](https://user-images.githubusercontent.com/54488922/232457679-a93208bb-10dd-4bff-a3ad-20fbb6c5554b.png)
