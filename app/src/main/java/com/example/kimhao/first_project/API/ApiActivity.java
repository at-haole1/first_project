package com.example.kimhao.first_project.API;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KimHao on 05/04/2017.
 */
@EActivity(R.layout.activity_api)
public class ApiActivity extends AppCompatActivity{
    @Extra
    String myMapsActivity;

    @ViewById(R.id.recyclerViewApi)
    RecyclerView mRecyclerView;

    @ViewById(R.id.toolbarAPi)
    Toolbar toolbar;

    private SOService mService;
    private AnswersAdapter mAdapter;

    @AfterViews
    void afterViews(){
        //setSupportActionBar(toolbar);
        Toast.makeText(this, myMapsActivity, Toast.LENGTH_SHORT).show();
        mService  = ApiUtils.getSOService();

        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0),new AnswersAdapter.PostItemListener(){
            @Override
            public void onPostClick(long id) {
                Toast.makeText(ApiActivity.this, "Post id is "+ id, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        loadAnswer();
    }

    public void loadAnswer() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                //showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_api,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        menuItem(searchView);
        return true;
    }

    private void menuItem(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
