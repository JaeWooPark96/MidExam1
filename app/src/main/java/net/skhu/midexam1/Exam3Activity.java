package net.skhu.midexam1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Exam3Activity extends AppCompatActivity {

    public static final int REQUEST_CREATE = 0;
    public static final int REQUEST_EDIT = 1;

    int memoIndex;
    RecyclerView3Adapter recyclerView3Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3);

        //RecyclerView3Adapter 생성, 우리가 만들어준 Adapter를 생성한 것입니다.
        recyclerView3Adapter = new RecyclerView3Adapter(this);//Lambda Expressions 함수 주의 사항은,  여기선  우리가 정의한 Interface 와 매개변수등 맞추어서 코딩해야 한다는 점입니다.
        //Lambda Expressions 에서, ->이후는 함수의 몸체입니다.//매개변수 인자전달은 RecyclerView3Adapter 에서 합니다.
        //Lambda Expressions 함수는 익명 함수와 비슷하다고 보면 됩니다.//Lambda Expressions 함수는 이미 정의된 해당 Interface 로만 정의되어야 한다.

        //RecyclerView 초기 설정
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);//RecyclerView 객체를 찾아주었습니다.
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerView3Adapter);//recyclerView에 recyclerView3Adapter를 등록해주었습니다.

        recyclerView3Adapter.add(new Memo("one"));
        recyclerView3Adapter.add(new Memo("two"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//메뉴를 생성할 때, 호출됩니다.
        getMenuInflater().inflate(R.menu.menu_exam3, menu);//R.menu.menu_recycler_view3 대로 메뉴를 그려줍니다.

        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {//메뉴가 클릭될 때의 리스너입니다.
        int id = item.getItemId();
        if (id == R.id.action_create) {//생성 버튼 눌릴 때
            startMemoActivityForResult(REQUEST_CREATE, null);
            return true;
        }
        return super.onOptionsItemSelected(item);//모르는 메뉴 눌렸을 때 호출
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {//자식 Activity 끝나면 여기로 옵니다.
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {//정상적으로 반환 되었을 때
            Memo memo = (Memo)intent.getSerializableExtra("MEMO");//메모 객체를 반환 받았습니다.
            //보통 위에 처럼 Memo 객체 리턴 받습니다.
            if (requestCode == REQUEST_CREATE)//생성 목적 intent 에서 반환 받을 때
                recyclerView3Adapter.add(memo);//추가

            recyclerView3Adapter.notifyDataSetChanged();//RecyclerView를 다시그려주었습니다.
        }
    }

    //MemoActivity 를 호출하는 함수를, RecyclerView3Activity 한 곳에 모으기 위해서 이렇게 했습니다.//다른 Activity 를 호출하는 코드는 액티비티 내부에 구현//RecyclerView3Adapter 는 Activity 를 참조하지 않는 게 좋다.
    private void startMemoActivityForResult(int requestCode, Memo memo) {//requestCode와 Memo 객체로 intent하는 함수입니다.
        Intent intent = new Intent(this, Exam3EditActivity.class);
        intent.putExtra("MEMO", memo);
        startActivityForResult(intent, requestCode);
    }
}
