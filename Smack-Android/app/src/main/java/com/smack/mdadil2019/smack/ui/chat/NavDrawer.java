package com.smack.mdadil2019.smack.ui.chat;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smack.mdadil2019.smack.R;
import com.smack.mdadil2019.smack.adapter.ChatAdapter;
import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;
import com.smack.mdadil2019.smack.data.network.model.MessageResponse;
import com.smack.mdadil2019.smack.data.prefs.AppPreferencesHelper;
import com.smack.mdadil2019.smack.di.root.MyApp;
import com.smack.mdadil2019.smack.ui.login.LoginActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavDrawer extends AppCompatActivity
        implements NavDrawerMVP.View, NavigationView.OnNavigationItemSelectedListener {

    TextView addChannelTv;

    TextInputEditText channelNameEt;

    TextInputEditText channelDescEt;

    @BindView(R.id.navRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.textViewSelectChannelLabel)
            TextView selectChannelLabel;

    @BindView(R.id.editTextMessage)
    EditText messageEt;

    @BindView(R.id.cardView)
    CardView cardView;

    @BindView(R.id.progressBarNav)
            ProgressBar navProgressBar;



    NavigationView navigationView;

    @Inject
    NavDrawerMVP.Presenter presenter;

    @Inject
    AppPreferencesHelper prefs;

    private String currentChannelName;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        ButterKnife.bind(this);

        ((MyApp)getApplication()).getApplicationComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setTitle("Smack");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.openDrawer(Gravity.LEFT);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView usernameTv = view.findViewById(R.id.textViewHeadUser);
        usernameTv.setText(prefs.getUserName());

        TextView addTv = view.findViewById(R.id.textViewAddChannel);
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(NavDrawer.this);
                dialog.setContentView(R.layout.create_channel_layout);
                dialog.show();

                channelDescEt = dialog.findViewById(R.id.textInputChannelDesc);
                channelNameEt = dialog.findViewById(R.id.textInputChannelName);

                Button createBtn = dialog.findViewById(R.id.createChannelBtn);
                createBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.createChannel();
                        dialog.dismiss();
                    }
                });
            }
        });

    }
    @OnClick(R.id.sendBtn)void sendMessage(){
        String message = messageEt.getText().toString();
        presenter.sendMessage(currentChannelName,message);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            presenter.logOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*
        1. Send chat messages reqeusts for particular channel using RxJava
        2. store the response in chat model (retrofit)
        3. onNext -> pass the chat into adapter and notify the adapter about itemAddition

         */
        currentChannelName = item.toString();


        //presenter will contain two methods 1. for network call 2. for local db call
        if(isNetworkAvalable())
            presenter.openChatRoom(currentChannelName);
        else
            presenter.getAllMessagesOffline(currentChannelName);

        cardView.setVisibility(View.VISIBLE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void createChannelTapped() {

    }


    @Override
    public void updateRecyclerView(final ArrayList<MessageResponse> responses) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                selectChannelLabel.setVisibility(View.INVISIBLE);
                chatAdapter = new ChatAdapter(responses,NavDrawer.this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(NavDrawer.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(chatAdapter);
                recyclerView.smoothScrollToPosition(responses.size());
            }
        });
    }

    @Override
    public void showUIMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getCreateChannelName() {

        return channelNameEt.getText().toString();
    }

    @Override
    public String getCreateChannelDescription() {
        return channelDescEt.getText().toString();
    }

    @Override
    public void notifyAdapter() {

    }


    @Override
    public void addChannelInList(final ArrayList<ChannelResponse> channelResponses) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Menu menu = navigationView.getMenu();
                menu.clear();
                for(ChannelResponse response : channelResponses){
                    menu.add(response.getChannelName());
                }
            }
        });
    }

    @Override
    public void showProgressbar() {
        navProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                navProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void showLoginScreen() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    public void clearMessageText() {
        messageEt.setText("");
        messageEt.setHint("Enter you message...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        if(isNetworkAvalable())
            presenter.getAllChannels();
        else
            presenter.getAllChannelsOffline();
        presenter.getMessage();
        presenter.loadAddedChannels();
    }



    private boolean isNetworkAvalable(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() !=null;
    }
}
