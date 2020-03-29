# Docker

## Docker overview

Docker is an open platform for developing, shipping, and running applications. Docker enables you to seperate your application from your instructure so you can deliver software quickly. 

## The Docker platform 

Docker provides the ability to package and run an application in a loosely isolated environment called a container. Containers are lightweight because they don’t need the extra load of a hypervisor, but run directly within the host machine’s kernel.

Docker provides tooling and a platform to manage the lifecycle of your containers:

* Develop your application and its supporting components using containers.

* The container becomes the unit for distributing and testing your application.

* When you’re ready, deploy your application into your production environment, as a container or an orchestrated service. This works the same whether your production environment is a local data center, a cloud provider, or a hybrid of the two.

## Docker Engine

*Docker engine* is a client-server application with these major components: 

* A server which is a type of long-term running program called a daemon process (the **docker** command). 

* A REST API which specifies interfaces that programs can use to talk to the dameon and instruct it what to do. 

* A command line interface (CLI) client (the docker command).

![alt text][engine]

[engine]: https://docs.docker.com/engine/images/engine-components-flow.png

## Docker architecture

Docker uses a client-server architecture. The Docker client talks to the Docker daemon, which does the heavy lifting of building, running, and distributing your Docker containers. The Docker client and daemon can run on the same system, or you connnect a Docker client to a remote Docker daemon. The Docker client and daemon communicate using a REST API, over UNIX sockets or a network interface.

![alt text][architecture]

[architecture]: https://docs.docker.com/engine/images/architecture.svg


### The Docker daemon

The docker daemon(dockerd) listens for Docker API requests and manages Docker objects such as images, containers, networks, and volumes. A daemon can also communicate with other daemons to manage Docker services. 

### The Docker client

The docker client(docker) is the primary way that many Docker users interact with Docker. When you use commands such as **docker run**, the client sends these commands to **dockerd**, which carries them out. The **docker** command uses the Docker API. The Docker client can communicate with more than one daemon.

### Docker registries

A docker registry stroes Docker images. Docker hub is a public registry that anyone can use, and Docker is configured to look for images on Docker Hub by default.

When you use the **docker pull** or **docker run** commands, the required images are pulled from your configured registry. When you use the **docker push** command, your image is pushed to configurd registry. 

Reference: 

https://docs.docker.com/engine/docker-overview/
 