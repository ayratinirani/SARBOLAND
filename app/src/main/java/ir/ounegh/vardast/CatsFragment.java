package ir.ounegh.vardast;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
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
    RecyclerView mrc;
    AppCompatAutoCompleteTextView ato;
    ArrayList<Category> ctas;
    ArrayAdapter<Category>adapter;
    Category selectedcat;
    ArrayAdapter<Mlocation>ladapter;

 @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catslayout,
                container, false);
        ato=(AppCompatAutoCompleteTextView) view.findViewById(R.id.autotext);
        mrc=(RecyclerView)view.findViewById(R.id.mrecyc);
        mrc.setLayoutManager(new LinearLayoutManager(getActivity()));
        getCats();

        return view;


    }
private  void remake()
{
    ctas=MainActivity.CATS;

    if(ctas.size()>1) {
        ArrayAdapter<Category> madapter = new ArrayAdapter
                (getActivity(), android.R.layout.select_dialog_item,ctas);
        ato.setThreshold(0);
        ato.setAdapter(madapter);
        ato.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //  Toast.makeText(getActivity(),ctas.toString(),Toast.LENGTH_LONG).show();
                        selectedcat=(Category) adapterView.getItemAtPosition(i);
                        String s=selectedcat.getName();
                        findLocs(s);

                    }
                }
        );
    }
}
    private void findLocs(String s) {
        getActivity().setTitle(s+" های اطراف اینجا");

        Map<String,String> map=new HashMap<>();
        map.put("category",s);
        map.put("latitude",(MainActivity.getLocation().getLatitude())+"");
        map.put("longitde",(MainActivity.getLocation().getLongitude())+"");

       MainActivity.MLOCATIONS=new ArrayList<>();
        Call<List<Mlocation>> listCallcall=
                VrdClient.getClient().create(VrdApi.class).getList(map);
      listCallcall.enqueue(new Callback<List<Mlocation>>() {
            @Override
            public void onResponse(Call<List<Mlocation>> call, Response<List<Mlocation>> response) {
               // Toast.makeText(getActivity(),response.body().toString(),Toast.LENGTH_LONG).show();

                for(int i=0;i<response.body().size();i++){
                    MainActivity.MLOCATIONS.add(response.body().get(i));

                    refreshList();
                }
            }

            @Override
            public void onFailure(Call<List<Mlocation>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
              t.printStackTrace();
            }
        });

    }
    private void refreshList() {

        final MlocationAdapter mAdapter = new MlocationAdapter(MainActivity.MLOCATIONS, getActivity());
        mrc.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mrc.addItemDecoration(itemDecoration);
        mrc.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String phone = MainActivity.MLOCATIONS.get(position).getPhone();
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(phone.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                })
        );
    }
    public void getCats(){
       selectedcat=null;
        Call<List<Category>> ccall=
                VrdClient.getClient().create(VrdApi.class).getCats();
        ccall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

               MainActivity.CATS.clear();
                for(int i=0;i<response.body().size();i++){
                   MainActivity.CATS.add(response.body().get(i));
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
