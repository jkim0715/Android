# 메모장 



주요기능

- 로그인
- 제목, 날짜 선택
- 메모 저장 (SQLite)
- 저장 리스트 리스트 뷰로 보기
- 리스트 뷰 선택시 저장된 메모 불러오기

SQLite DB 구성

```java
String sql = "create table if not exists emp("        + " num integer PRIMARY KEY autoincrement, "        + " id text, "        + " title text, "        + " date text, "        + " content text)";
```

TroubleShooting

I. Intent에 ArrayList<obj>를 담아 보낼 때 

- obj 는 Implements Serializable 해야함..

  ```java
  public class Item implements Serializable
  ```

- 받을때도  getSerializableExtra

  ```java
   Intent intent = getIntent();
          list = (ArrayList<Item>) intent.getSerializableExtra("list");
  ```

  



