private void getWorkouts()
   {

       String cancel_req_tag = "workout";
       Log.d("TEST", "Made it this far");
       StringRequest strReq = new StringRequest(Request.Method.POST,
               URL_FOR_LOGIN, new Response.Listener<String>()
       {

           @Override
           public void onResponse(String response)
           {
               Log.d("RESPONSE", response.toString());
               Toast.makeText(getBaseContext(), response,
                       Toast.LENGTH_LONG).show();
               try {
                   JSONArray jsonarray = new JSONArray(response);

                   boolean error = false;
                   // The boolean object error gets set to false in PHP which indicates the database call -
                   // found the user attempting to login
                   if (!error)
                   {

                       String muscle = "Failure";
                       String WorkID = "Failure";
                       String Workout = "Failure";
                       String Description = "Empty";

                       for (int i = 0; i < jsonarray.length(); i++) {
                           JSONObject jsonobject = jsonarray.getJSONObject(i);
                           muscle = jsonobject.getString("MuscleGroupName");
                           WorkID = jsonobject.getString("WorkoutID");
                           Workout = jsonobject.getString("WorkoutName");
                           Description = jsonobject.getString("WorkoutDescription");

                           View view  = inflater.inflate(R.layout.row, linearLayout, false);

                           // Edit contents of the new view
                           TextView workout = (TextView) view.findViewById(R.id.workoutTitle);
                           TextView describe = (TextView) view.findViewById(R.id.workoutDescription);
                           workout.setText(Workout);
                           describe.setText(Description);

                           // Add the view to the ViewGroup and it should show up
                           linearLayout.addView(view);


                           Log.d("CHECK", WorkID);
                       }
                       // Get the Username from the user's input
/*                        Toast.makeText(getBaseContext(), muscle,
                               Toast.LENGTH_LONG).show();
*/
/*
                       for (String work : arrayList) {

                           // Create new view (your own xml layout file, ViewGroup to be the root, boolean of whether to attach to root)
                           View view  = inflater.inflate(R.layout.row, linearLayout, false);

                           // Edit contents of the new view
                           TextView workout = (TextView) view.findViewById(R.id.texts);
                           workout.setText(work);

                           // Add the view to the ViewGroup and it should show up
                           linearLayout.addView(view);
                       }*/

                   }
                   else
                   {
                       // If the error boolean is set to true in the PHP code, it means the user authentication -
                       // failed, obtain errors message to display from the JSON object encoded

                       Toast.makeText(getApplicationContext(),
                               "Broken", Toast.LENGTH_LONG).show();
                   }
               } catch (JSONException e)
               {
                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener()
       {
           @Override
           public void onErrorResponse(VolleyError error)
           {
               Log.e(TAG, "Retrieval Error: " + error.getMessage());
               Toast.makeText(getApplicationContext(),
                       error.getMessage(), Toast.LENGTH_LONG).show();

           }
       });
       // Adding request to request queue
       //Toast.makeText(getBaseContext(), "Added",
               //Toast.LENGTH_LONG).show();
       AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
   }
