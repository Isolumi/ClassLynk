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
ClassLynk offers an innovative way of creating the most optimal timetables for university students. Given a set of courses, ClassLynk will generate an optimized timetable based on distance, as well as show routing to get to every class. ClassLynk also offers a user authentication system which allows users to store their timetables and come back to view them whenever they like.

ClassLynk uses the ```Google Maps API``` to retrieve route data, geocode locations, and generate map links (link:  https://github.com/googlemaps/google-maps-services-java#readme). We will also be using Firebase as our primary database to store user information, and course informations.


# Installation
Begin by installing the gcloud CLI which can be found at: https://cloud.google.com/sdk/docs/install#installation_instructions

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
