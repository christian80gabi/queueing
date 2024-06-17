# Queueing
A Client-Server queueing simulator with Kotlin

### Scenario

- **client**: requesting for a party
- **server**: collecting the client request. 
> **Conditions**: 
>   - if a _first client request_, initiate a party and awaiting others until a threshold met before a set time;
>   - if not _first client request_, collect the request and awaiting others until a threshold is met before a set time;
>   - else, if a threshold is met before a set time, start the initiated party;
>   - else, if a threshold is never met before a set time, fill the bucket of clients with "bot clients" and start the initiated party.


