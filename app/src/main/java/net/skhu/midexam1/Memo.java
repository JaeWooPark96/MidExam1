package net.skhu.midexam1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//Serializable는 구현할 수 없습니다. 안에 함수가 없는 것이 맞는 표현입니다.
public class Memo implements Serializable {//Serializable interface 는 marking interface 입니다. 이거를 붙이거나, 이것
    String body;//내용

    public Memo(String body) {
        this.body = body;//내용 저장
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
