## Musfer

**Musfer** is a playlist transfer service that allows users to move their music playlists between streaming platforms, such as **Spotify** and **YouTube Music**.

The project is under active development and serves as a space to experiment with system design, scalability, and modern backend technologies while building something genuinely useful.

---

## Features (Planned & In Progress)

- Transfer playlists between music streaming services  
- Secure user authentication and authorization  
- Asynchronous processing for long-running transfers  
- Track transfer progress and status  
- Extensible design to support additional platforms in the future  

> Note: Some features are still under development and may be incomplete or subject to change.

---

## Tech Stack

- **Backend:** Java, Spring Boot 3
- **Frontend:** Vue.js
- **Database:** PostgreSQL
- **Messaging:** RabbitMQ
- **Containerization:** Docker, Docker Compose

---

## Architecture (WIP)

Even though this is a personal low scale project, the architecture is designed with **scalability, fault tolerance, and extensibility** in mind.

Key ideas (subject to change):
- Separation of concerns between API, business logic, and integration layers  
- Asynchronous playlist transfer using message queues  
- Stateless services to support horizontal scaling  
- Containerized deployment for consistency across environments  

A more detailed architecture diagram and explanation will be added as development progresses.

---

## Usage (WIP)

At the moment, Musfer is under active development and does not yet provide a fully stable user flow.

Planned usage:
1. Authenticate with supported streaming services  
2. Select source and destination platforms  
3. Choose playlists to transfer  
4. Monitor transfer progress  

Detailed setup and usage instructions will be added once the core functionality is stable.

---

## Project Goals

- Learn and apply **modern backend architecture patterns**
- Gain hands-on experience with **Spring Boot, distributed systems, and containers**
- Create a useful tool that I personally use

---

## Project Status

**Work In Progress (WIP)**  
This project is actively developed and not intended for production use at this time.

---

## Repository Changes
This project started as a monorepo, later split into independent repositories
under a GitHub Organization to simulate real-world microservice ownership.
For better visibility and easier evaluation, it is now moved again
as a monorepo while preserving service boundaries.
