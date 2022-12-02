# AOC Combined Leaderboard


There is a limit of 200 people in most Advent of Code leaderboards

This service allows you combine multiple leaderboards into a single one.

## How to use:

-  Advent of code has a leaderboard api that is protected by your cookie which expires after about a month.  Example: https://adventofcode.com/2022/leaderboard/private/view/{leaderboardId}.json
- You can find this url by clicking on **Api** in your leaderboard and then **Json** in the resulting page.
- Go to the url and copy the **cookie header** in the request from your browser.
- Add the leaderboard id and cookie to `resources/application.conf` file in this repository.
-       aocConfig {  
          leaderboardIds = ["leaderboard_id_1", "leaderboard_id_2"...]  
          cookies = ["cookie_1", "cookie_2"]  
          }




- Clone project and From the project root folder run
xk
  	`gradlew assemble`  

  if permission is denied, run  
  `chmod 755 gradlew` first  before `gradlew assemble  `

  this would build a .jar file that in the build folder that you can then run using

  `java -jar /build/libs/com.ozioma.aocleaderboard-all.jar
`

get the combined leaderboard by visiting

http://127.0.0.1:8080/leaderboard/2022

