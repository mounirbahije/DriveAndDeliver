For non-blocking solution, I would follow these steps:

1. **Switch to WebFlux**: I'd start by migrating from Spring MVC to Spring WebFlux to utilize its non-blocking reactive streams. This change would help my application handle more concurrent requests efficiently without tying up resources in thread-per-request setups.

2. **Adopt Reactive Database Access**: I would replace traditional JDBC database access with R2DBC (Reactive Relational Database Connectivity). This ensures that database operations are non-blocking and can be integrated smoothly with the rest of my reactive stack.

3. **Update the Frontend**: If my frontend is making synchronous API calls, I'd consider adapting it to handle asynchronous data streams, possibly using Angular with RxJS, which supports reactive programming.

