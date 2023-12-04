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

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

</div>

# ClassLynk

An app that automatically creates timetables for you given a list of courses.

The app will also have a login system, so that users can save timtables and create route images based on a timetable.

We will mainly be using the google api through it's java library to get route data, geocode locations, and generate map images(link: https://github.com/googlemaps/google-maps-services-java#readme). For data storage, we will be using Firestore.


# Installation
Begin by installing the gcloud cli which can be found at: https://cloud.google.com/sdk/docs/install#installation_instructions

Then setup the application default by running the following command
```shell
gcloud auth application-default login
```
You should have access to a serviceAccountKey.json which should be placed under
```shell
cd ./src/main/resources/config/serviceAccountKey.json
```
You should also have access to the ```MAPS_API_KEY``` environment variable, which should be stored under
```
Run -> Edit Configuartions -> Environment Variables
```
# Running the Application
To run the application, head to ```./src/main/java/ai/classlynk/app/ClassLynkApplication``` and run the application which will boot up the program.