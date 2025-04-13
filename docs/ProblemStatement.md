Software Developer Coding Challenge 
 
Congratulations on getting to this stage and thank you for taking the time to solve the following exercise. 
 
The assignment should take less than 5-6 hours of your time, and you can submit a partial or incomplete solution. 
 
Here are some ground rules: 
 
●	We would like to see the solution implemented in Java 
●	You can use any library and/or framework that you see fit. Our preference would be Springboot and Postgresql db.
●	You don’t need to deal with deployment, CI/CD, etc. 
●	Make sure to provide instructions on how to run your deliverable and any dependencies it might have. 
●	Write code as you would expect if you were working as part of a team and deploying to production
●	Code comments are more than welcome, so we can understand your process
 
NOTE: Keep in mind that you should keep your repository private. You can use https://github.com to create an account with free private repos. Once you are done you can share your repo with Logifuture People & Talent (hrgroup@logifuture.com ) on GitHub. 
 
Should you need any further information, please do not hesitate to contact us. 
 
   Best of luck! 
                                                                    
 
 ----
 
 
The Task: Building A Wallet System                                                                      
Background

The player logs into one of our websites to play some games. The first thing they do is check their current balance, which happens to be 1000; next they load a game and start playing. Each time the user plays the game they place a Bet for the amount of money they choose, our system processes this request, and the user gets a response telling them if they won or lost. At any time during this flow the user can check what their new balance is. 
On each Bet the users balance gets reduced by the amount they wager, this is called a Debit. If the user is lucky enough to win a Bet they will be awarded their winning amount, increasing their balance by the win amount; this is called a Credit.
 
Task 
 
In a platform that processes Bets of any kind there needs to be a place where we can store all the transactions our users make, which we call a Wallet. We would like you to design and implement a service that is responsible for storing these transactions and provide the functionality for Debiting, Crediting and retrieving the user’s balance. 
 
Requirements: 
●	Each Bet has only one Debit transaction and one Credit transaction.
●	All transactions should only be processed once.
●	The user should be able to request their current balance at any point.
●	We should be able to get a list of transactions and their types by user id.
●	In a Bet, a player cannot receive a Credit unless they have first performed a Debit. 
●	The system should be able to handle a large number of concurrent users and should be fault-tolerant, meaning that it should continue to operate even if some of the nodes in the system fail. 
●	The service should provide a REST API.
●	Don’t worry about authentication in your test service.
 
Try to keep the code simple and design/write the code as you would expect if you were writing it for a real lift scenario. Feel free to add any extra features or designs to the wallet if you deem it necessary.