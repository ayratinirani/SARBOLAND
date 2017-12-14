package ir.ounegh.vardast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.ListViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aseme on 13/12/2017.
 */

public class CatsFragment extends Fragment {
    ListViewCompat lv;
    int[]selectedcats;
    AppCompatAutoCompleteTextView ato;
    ArrayList<Category> ctas;
    ArrayAdapter<Category>adapter;
    ArrayAdapter<Mlocation>ladapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catslayout,
                container, false);
        lv=(ListViewCompat) view.findViewById(R.id.catslistview);
        ato=(AppCompatAutoCompleteTextView) view.findViewById(R.id.autotext);
        getCats();



        return view;


    }
private  void remake()
{
    ctas=VrdstApp.CATS;

    if(ctas.size()>1) {
        ArrayAdapter<Category> madapter = new ArrayAdapter
                (getActivity(), android.R.layout.select_dialog_item,ctas);
        ato.setThreshold(1);
        ato.setAdapter(madapter);
        ato.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //  Toast.makeText(getActivity(),ctas.toString(),Toast.LENGTH_LONG).show();
                        VrdstApp.SECTEDCAT=ctas.get(i).getName();
                        findLocs(VrdstApp.SECTEDCAT);
                    }
                }
        );
    }
}
    private void findLocs(String s) {
        Map<String,String> map=new HashMap<>();
        map.put("category",s);
        map.put("latitude",38.258536+"");
        map.put("longiyude",38.258536+"");
        VrdstApp.SELECTEDLOCATIONS=new ArrayList<>();

        Call<List<Mlocation>> listCallcall=
                VrdClient.getClient().create(VrdApi.class).getList(map);
      listCallcall.enqueue(new Callback<List<Mlocation>>() {
            @Override
            public void onResponse(Call<List<Mlocation>> call, Response<List<Mlocation>> response) {
                Toast.makeText(getActivity(),response.body().toString(),Toast.LENGTH_LONG).show();

                for(int i=0;i<response.body().size();i++){
                    VrdstApp.SELECTEDLOCATIONS.add(response.body().get(i));

                    refreshList();
                }
            }

            @Override
            public void onFailure(Call<List<Mlocation>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
              t.printStackTrace();
            }
        });
//        Call<String> stringCall = VrdClient.getClient().create(VrdApi.class).getStringResponse(map);
//        stringCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    String responseString = response.body();
//                    // todo: do something with the response string
//                    Toast.makeText(getActivity(),call.toString(),Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(getActivity(),"error: "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
//            }
//        });







    }
    private void refreshList(){
        ladapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,VrdstApp.SELECTEDLOCATIONS);

           lv.setAdapter(ladapter);
           lv.setOnItemClickListener(

                   new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                           String s= VrdstApp.SELECTEDLOCATIONS.get(i).getName();
                           Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
                       }
                   }
           );

    }
    public void getCats(){
        VrdstApp.SECTEDCAT="";
        Call<List<Category>> ccall=
                VrdClient.getClient().create(VrdApi.class).getCats();
        ccall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Toast.makeText(getActivity(),response.body().toString(),Toast.LENGTH_LONG).show();
                //   mTextMessage.setText(response.body().toString());
                VrdstApp.CATS=new ArrayList<>();
                for(int i=0;i<response.body().size();i++){
                    VrdstApp.CATS.add(response.body().get(i));
                }
                remake();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(),"error"+call.toString(),Toast.LENGTH_LONG).show();
            }
        });



    }





}
