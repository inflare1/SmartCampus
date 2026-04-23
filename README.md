## Instructions
1. Clone the repository.
2. Open NetBeans and select **File > Open Project**.
3. Choose the cloned project folder and open it.
4. Right-click the project and select **Clean and Build** before running it.
5. Make sure Apache Tomcat is installed and configured.
6. Start the Tomcat server.
7. Run the project.

 

1. In my project, JAX-RS resources use a per-request lifecycle, 
meaning a new instance of classes like SensorRoom or SensorResource is created for every incoming request rather than being a singleton.
Because of this, I cannot store data in normal instance variables, 
as it would be lost after each request. That is why I used shared in-memory structures in SmartCampusDatabase 
and accessed them through my GenericDAO so the data persists across requests.
However, since these lists are shared, multiple requests can access them at the same time, which can 
lead to race conditions if not handled carefully.

2. Hypermedia is considered advanced REST design because the API 
includes links in its responses that guide the client on what actions can be done next, instead of the client needing to know all endpoints beforehand.
This benefits client developers because they can follow the links provided by 
the API instead of relying on static documentation. It makes the system more flexible and easier to use, especially if endpoints change.
 
3. Returning only IDs uses less network bandwidth because the response is smaller and 
faster to send. However, it increases client-side processing because 
the client would need to make extra requests to get the full room details.
Returning full room objects uses more bandwidth because the response is larger, but it 
makes things easier for the client because all the room information is already included in one request.

4. The DELETE operation in this project is idempotent because sending the same 
DELETE request multiple times results in the same system state as sending it once. 
The first DELETE request removes the room from the system. If the same request is sent again, 
the room no longer exists, so no further changes occur. Therefore, repeated DELETE requests do not affect the system
after the initial deletion.

5.@Consumes(MediaType.APPLICATION_JSON) is explicitly used on @POST in my implementation, which means JAX-RS will only accept input in JSON format.
If a client tries to send data in any other format such as text/plain or application/xml, JAX-RS will not able to
match the request to the method. JAX-RS will automatically return a HTTP 415 Unsupported Media Type. 

6.In my implementation, I used @QueryParam like /api/v1/sensors?type=CO2 to filter sensors.
This keeps /sensors as the main collection and treats type as an optional filter.
Using a path like /sensors/type/CO2 makes it look like a separate resource 
instead of a filter. Query parameters are better because they are more flexible and allow multiple filters 
(e.g., ?type=CO2&status=ACTIVE) without changing the structure of the URL, making the API easier to use and extend.

7. Using the Sub-Resource Locator pattern improves structure by splitting logic into smaller, 
separate classes instead of putting everything in one large controller.
This makes the code easier to read, maintain, and scale. Each class handles its 
own responsibility, which reduces complexity and keeps the API organised.

8. HTTP 422 is more accurate because the request itself is valid (correct JSON and endpoint), but 
the data inside it is wrong, such as a roomId that does not exist.
A 404 means the resource itself is not found, but in this case the API endpoint exists and works. So 422 better 
describes that the server understands the request but cannot process it due to invalid input.

9. Exposing Java stack traces is risky because it reveals internal details about the system.
An attacker could learn class names and package structure, file paths and server setup, libraries and frameworks used and 
exact points where errors occur. This information can help attackers find weaknesses and plan targeted attacks on the system.

10. Using JAX-RS filters is better because 
logging is handled in one place instead of being repeated in every method.
This makes the code cleaner, easier to maintain, and avoids duplication. It also ensures 
all requests and responses are logged consistently without missing anything.


