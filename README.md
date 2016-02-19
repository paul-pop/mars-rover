# Mars Rover Project

## Summary
This is the implementation of the solution for the Mars Rover problem, using TDD principles.

## Build and run
In order to build the solution and run the tests you must have JDK 8 and Maven 3 installed, simply run:
> mvn clean install

## Notes
I have used TestNG for testing as I found the DataProviders offered by TestNG to be very helpful in avoiding
code duplication within tests. Because of the nature of the problem, I avoided mocking the objects because we do
not have a running application, we just use TDD to prove the solution.

Just to provide a bit of explanation as to why I haven't decoupled things more and why I have such a simple model.
Initially I had split up the execution bit and the instructions that the rover received and I have also created a
separate object for the bounds, but looking at the tests after that, they didn't look right. I feel like with this
current implementation tests look a lot better and are more readable.

Also, as you may have noticed the bounds need to be set for each individual rover. I've done this purely for flexbility,
in case we have different bounds for different rovers. Alternative would have been to have a different object that
stored the list of rovers and the plateau bounds (I did not do this because of time constraints).