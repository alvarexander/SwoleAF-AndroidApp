package cop4600group9.swoleaf;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;



import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoutinesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    SharedPreferences sp;
    private static final String URL_FOR_LOGIN = "http://cop4331.hosted.nfoservers.com/getroutines.php";
    private static final String TAG = "RoutinesActivity";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        setContentView(R.layout.activity_routines);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        // Get the relevant user information from our sharedpreference
        sp = getSharedPreferences("login", MODE_PRIVATE);

        String name = sp.getString("name", "user");

        String userID = sp.getString("id", "0");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        getRoutines();

        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profileName);


    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.routines, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout)
        {
            Intent lintent = new Intent(getApplicationContext(), LoginActivity.class);

            startActivity(lintent);

            // Edit shared preference to indicate user is no longer logged in
            sp.edit().putBoolean("logged", false).apply();

            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void getRoutines()
    {

        String cancel_req_tag = "routine";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

              //  Log.d("RESPONSE", response.toString());
                Toast.makeText(getBaseContext(), response,
                        Toast.LENGTH_LONG).show();
                try
                {
                    JSONArray jsonarray = new JSONArray(response);
                    Toast.makeText(getBaseContext(), response,
                            Toast.LENGTH_LONG).show();

                    boolean error = false;
                    // The boolean object error gets set to false in PHP which indicates the database call -
                    // found the user attempting to login
                    if (!error)
                    {
                        String RoutineID = "Empty";
                        String RoutineName = "Failure";
                        String RoutineDescription = "Failure";
                        String RoutineDifficulty = "Empty";

                        for (int i = 0; i < jsonarray.length(); i++)
                        {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);

                            RoutineID = jsonobject.getString("RoutineID");
                            RoutineName = jsonobject.getString("RoutineName");
                            RoutineDescription = jsonobject.getString("RoutineDescription");
                            RoutineDifficulty = jsonobject.getString("RoutineDifficulty");


                        }


                    }

                    else
                    {
                        // If the error boolean is set to true in the PHP code, it means the user authentication -
                        // failed, obtain errors message to display from the JSON object encoded

                        Toast.makeText(getApplicationContext(),
                                "Broken", Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e)
                {

                    Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calandar)
        {

            Intent lintent = new Intent(getApplicationContext(), RoutinesActivity.class);

            startActivity(lintent);
        }

        else if (id == R.id.nav_workouts)
        {

            Intent lintent = new Intent(getApplicationContext(), RoutinesActivity.class);

            startActivity(lintent);


        }

        else if (id == R.id.nav_routines)
        {


            Intent lintent = new Intent(getApplicationContext(), RoutinesActivity.class);

            startActivity(lintent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
