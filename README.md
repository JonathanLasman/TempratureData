# TempratureData

The following program implements a server that collects data via HTTP requests from different sensors in a building.
The server listens to port 8021, and only post requests are supported. Each request contains a .json with two fields: the sensor ID and the sampled temprature.

SAMPLE REQUEST FROM A UNIX TERMINAL:
"
curl --header "Content-Type: application/json" -d "{\"id\":\"1\", \"temp\":\"100\"}" http://localhost:8021/add_sample
"

Min, Max and Avg are calculted and stored in a .json created (or modified) upon the arrival of data for a new sensor in a folder titled "Measurment".
The name of the file is the sensor ID.

This implementation prioritizes time complexity over space complexity (finding min, max, and avg is always O(1).)

Executing the Main will prompt a user console. The user needs to enter 0 to show metrics for total sensors; -1 to exit the program; or the sensor ID for a specific sensor. 
