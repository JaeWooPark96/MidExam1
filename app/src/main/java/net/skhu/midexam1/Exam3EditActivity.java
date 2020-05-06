package net.skhu.midexam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Exam3EditActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_edit);

        editText = findViewById(R.id.editText);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String body = editText.getText().toString();//editText_title의 내용을 저장합니다.
                if (isEmptyOrWhiteSpace(body)) {//해당 String이 비어있는지 검사해주었습니다.
                    editText.setError("내용을 입력하세요");
                    return;
                }

                Memo memo = new Memo(body);

                Intent intent = new Intent();//Activity 호출, 반환할 때 intent 사용합니다.
                intent.putExtra("MEMO", memo);//memo 객체를 반환합니다. 여기서 중요한 것은 해당 memo객체의 class 에서 Serializable 가 implements 되었는지 입니다.
                setResult(RESULT_OK, intent);//RESULT_OK로 리턴합니다. 보통 리턴 성공일 때, RESULT_OK를 사용합니다.
                finish();//현제 화면 종료, 이전화면으로 되돌아 옵니다.
            }
        });
    }

    static boolean isEmptyOrWhiteSpace(String s) {//해당 String이 비어있는지 검사해주는 함수입니다.
        if (s == null) return true;
        return s.toString().trim().length() == 0;
    }
}
