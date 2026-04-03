# Fightkeep API

## What is this?

This is a small, simple, and not serious at all project intended to show what a simple API developed for Spring boot and deployed to AWS might look like, but also be a fun way to practice coding w/ AI or LLMS.

![Lets Rock!](https://www.grabthegames.com/assets/images/reviews/ss_0f29aebacb81b972782382dbf4c0079f0d37b330.jpg)


In my spare time, I like to play fighting games, and the current one I'll be playing is Guilty Gear Strive. The function of the API is to allow me to track everytime I lose an online ranked match with some details helping describe how i lost and how badly. This information will be stored in a DynamoDB table where I will slowly collect the data. And then I hope to eventually code it to aggregate the data, feed it to AI, and then get insight on what I should focus on to improve. Its not a perfect product, but it's fun and silly!


## Data Model:

`fightResult:
{
"player1" : string,
"player2" : string,
"howBadDidILose" : string,
"reasonIlost" : string,
"secondReasonILost" : string,
"fightTIme" : Instant
}`


## Endpoints:

* root : /api/fight-results
* get / - retrieve all fight results
* get /{id} - retrieve fight result with {id}
* post / - create fight result
* put /{id} - update fight result

## Tech Stack:

### Development:
* IntelliJ
* Amazon Q
* GitHub copilot

### Backend:
* Docker Container 
* Java 21
* Gradle 9.x
* Spring boot

### Hosting:
* AWS ECS
* AWS EDB
* GitHub CI/CD ( Actions )

### Test harness:
* Postman



## Author:
Christopher Blanchard
Christopher.blanchard12@gmail.com
4328132634

 