# Test gecksoft

This application its a test for a job.

## Prerequisites

Before you begin, make sure you have the following software installed:

- [Docker](https://www.docker.com/get-started)
- [Java 21+](https://adoptium.net/) (if you want to run the application locally outside of Docker *)
- [Maven](https://maven.apache.org/install.html) (if you want to build the project locally *)

* I will only describe how to run with docker. To run locally you can get an IDE and try with it, isnt hard.

## Steps to Set Up and Run the Application

### 1. Clone the Repository

Clone the repository to your local machine using the command:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
```

### 2. Build and Run Docker
Build the Docker image using the command:

```bash
docker build -t gecksoftware-app .
```

Run the application using the command:

```bash
docker run -p 8080:8080 gecksoftware-app
```

If you want, you can see the uploaded images using the linked directory by adding this command:

```bash
-v /path/to/your/local/images:/app/src/main/resources/images
```

In a simple way, just use:

```bash
docker run -v "$(pwd)"/src/main/resources/images/:/app/src/main/resources/images -p 8080:8080 gecksoftware-app
```

It will save the files into the folder "images" inside "resources". Remember that you must be at "gecktest" folder.

### 3. Endpoints
The endpoints are available at:

```bash
http://localhost:8080/swagger-ui/index.html
```


### About Tests:

I have written some tests but not many. More tests could be added, both integration and unit tests,
especially to check alternative paths. In any case, the tests run during the image build.

### About Database:

I did not include a database in the application, but one could be created to store image information or similar data,
including user information, etc. The images themselves would be better stored in an S3 bucket or a similar system.
The database could also be used to assist in processing and informing the status of the image resizing process,
indicating the stage of the process.

### About Architecture:
I used a simple one just to separate the concepts. Could use something like the "onion architecture"
[ I forgot the real name of it, but this is the nickname]. Anyway, the principal process are separated in the system,
and they talk with each other by interfaces. I tried to desacople the max possible of the code, putting it in another
class/method so later its easier to understand and to do test and maintenance.
In a sumary, the architecture its divided by "controllers", having the controller class and interface [ better to separate the code from the swagger/contract description].
Domains: the classes of the system, basicaly there are only the request class to simplify but would be better create DTOs and specific classes for busines or other division on the architecture. 
UseCases: where the logic of the system is. There are the useCases and the works, a special kind that can be used to run in a separeted thread. 
Repository: there isnt this but could be created; Gateways: there arent too but could be created to do the adapter pattern between the system and some external one, like other APIs or database.
The "utils" folder its for functions or other things that should be of common use in the System. Also could have some "configuration" folder configs and security classes. 

### About the service:
Its simple, there aren't much checking about the data and this could be improved. Example, if you try to resize some
image that aren't there you will not receive an error. I did this to simplify and not consume too much of my time.
There are the 4 endpoints that was needed: create, delete, listAll and resize. All of them works fine but, as I told,
there aren't checks, if you upload a file you will see an error. If you create a file with a name ending as the type of
the image, this one will be a "fileName.imageType.png". Also, the creation of exception handler and better logs could be
greate to do too.

I didn't do a WebHock, I thought about it but I didn't know what to do or how to do it in a simple way
[ like, where should I send the mesasge?]
I thought about an email, something simple, but also for that would be needed some email to send the message ... not so
simple to just "run this code and it's fine"

I do a queue for the image processing, something simple with a worker and a thread queue.

### About What libraries/dependencies/tools did you choose and why?
I choose lombok to simplify the life with Java. Also spring-boot because its very common and simple to use,
but with docker would be better grallVm or quarkus. Also some libs for swagger and tests with java. I didnt chose any
lib for the image resize because java has some library's for it. 


