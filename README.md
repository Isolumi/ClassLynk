<div align="center">
<pre>
██████╗██╗      █████╗ ███████╗███████╗██╗  ██╗   ██╗███╗   ██╗██╗  ██╗
██╔════╝██║     ██╔══██╗██╔════╝██╔════╝██║  ╚██╗ ██╔╝████╗  ██║██║ ██╔╝
██║     ██║     ███████║███████╗███████╗██║   ╚████╔╝ ██╔██╗ ██║█████╔╝
██║     ██║     ██╔══██║╚════██║╚════██║██║    ╚██╔╝  ██║╚██╗██║██╔═██╗
╚██████╗███████╗██║  ██║███████║███████║███████╗██║   ██║ ╚████║██║  ██╗
╚═════╝╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚═╝   ╚═╝  ╚═══╝╚═╝  ╚═╝
--------------------------------------------------------------------------
optimized timetable generator
</pre>
</div>

# ClassLynk

An app that automatically creates timetables for you given a list of courses.

The app will also have a login system, so that users can save timtables and create route images based on a timetable.

We will mainly be using the google api through it's java library to get route data, geocode locations, and generate map images(link: https://github.com/googlemaps/google-maps-services-java#readme). For data storage, we will be using Firestore.


# USAGE
Begin by installing the gcloud cli which can be found at: https://cloud.google.com/sdk/docs/install#installation_instructions

Then setup the application default by running the following command
```shell
gcloud auth application-default login
```

To use this application, you will need to have a local google credentials file set up using the gcloud CLI by running the command "gcloud auth application-default login" in a cmd window. You'll need access to the Firebase, Google Maps API keys, and you will also need the login to the google account containing the project in firebase.
Once you have downloaded and opened the project, you will need to add the firebase api key into the resources file in a subfolder config, with filename serviceAccountKey.json. Then you will need to add the Maps API key in the run configuration by going into the configuration menu in the top right -> Edit configurations -> Modify Options, and tick the environment variables file and add the API key as an environment variable with name MAPS_API_KEY.
