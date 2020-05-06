package net.skhu.midexam1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ListIterator;

//RecyclerView3Adapter 가 다른 class 를 참조하지 않도록 고쳐야 한다.
public class RecyclerView3Adapter extends RecyclerView.Adapter<RecyclerView3Adapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;

        //데이터가 항목마다 다르기 때문에,  ViewHolder 필요, 어떤 data에 있는 textView에 대해 findViewById 합니다.
        public ViewHolder(View view) {//ViewHolder 사용하는 이유는 findViewById등을 하기 위함입니다. 그리고 각 리스너도 등록합니다.
            super(view);

            //참고로 여기서 view 는 해당 항목의 view입니다. 그래서 다른 항목의 view하고는 전혀 다른 겁니다.
            textView1 = view.findViewById(R.id.textView1);
        }

        public void setData() {//데이터를 채우는 함수입니다.
            Memo memo = arrayList.get(getAdapterPosition());//arrayList로부터 해당 요소 데이터 가져옵니다.

            //각 view를 데이터에 맞게 채워줍니다.
            textView1.setText(memo.getBody());
        }
    }

    LayoutInflater layoutInflater;
    ArrayList<Memo> arrayList;

    public RecyclerView3Adapter(Context context) {//RecyclerView1Adapter 생성자(Constructor)
        this.layoutInflater = LayoutInflater.from(context);//layout inflate
        //this.arrayList = arrayList;//목록의 arrayList
        this.arrayList = new ArrayList<Memo>();//arrayList를 RecyclerView3Adapter 안에 있도록 했습니다.
        //이렇게 하는 이유는 다른 class 의 멤버에 자꾸 접근하는 것을 방지하기 위함입니다.
        //서로 독립적이게 움직이도록 해주어야 합니다.//참조하는 클래스를 최소화하기 위함입니다.
        //메모 목록 관리를 RecyclerView3Adapter 에 전부 몰아주었습니다.//목록 관리하는 코드를 한 클래스에 몰아서 구현하는 것이 좋다.
    }

    @Override
    public int getItemCount() {//크기 반환
        return arrayList.size();
    }

    @Override
    public RecyclerView3Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {//ViewHolder 만들 때의 호출되는 함수입니다.
        View view = layoutInflater.inflate(R.layout.memo, viewGroup, false);

        return new RecyclerView3Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView3Adapter.ViewHolder viewHolder, final int index) {//데이터를 할당하는 함수, 데이터 세팅하는 함수
        viewHolder.setData();//해당 viewHolder의 데이터를 채워주었습니다.
    }


    //목록 관리 함수
    public void add(Memo memo) {//추가할 때, RecyclerView3Adapter 멤버 변수에 추가합니다.
        arrayList.add(memo);
        notifyItemInserted(arrayList.size() - 1);
    }
}
