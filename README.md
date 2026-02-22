# 🚀 Career Compass – Cosine Similarity Search Engine

A Spring Boot based backend application that recommends career roles using **TF-IDF vectors** and **Cosine Similarity** with Top-K matching.

---

## ▶️ How To Run The Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/AtharvaJD03/career-compass-cosine-search.git
```

### 2️⃣ Open in IntelliJ IDEA

Open the project as a Maven project.

### 3️⃣ Run the Application

Run: CareerCompassBackendApplication.java

The server will start at: http://localhost:8080

---

## 📌 Project Overview

This backend system:

- Loads career vectors from a JSON dataset
- Accepts an input vector
- Calculates cosine similarity
- Returns Top-K most relevant job roles
- Provides a clean REST API response

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Maven
- REST API
- Cosine Similarity Algorithm
- TF-IDF Vector Representation
- Git & GitHub

---

## ⚙️ How It Works

1. Career vectors are loaded at application startup.
2. A user sends a vector input via REST API.
3. Cosine similarity is calculated against all stored vectors.
4. Results are sorted in descending order.
5. Top-K matches are returned with rounded similarity scores.

---

