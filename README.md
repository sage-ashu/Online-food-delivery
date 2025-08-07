# 🍽️ Food Delivery App

A full-stack food delivery web application built with **React** (frontend) and **Spring Boot** (backend). The app allows customers to browse dishes, manage carts, place orders, and track them. Restaurants can manage their listings, dishes, and reviews. Optional support for delivery partners can also be integrated.

---

## 📌 Features

### 👤 Customer
- Browse restaurants and dishes
- Add dishes to cart
- Checkout and place orders
- View order history and status
- Login and registration with authentication
- Toast notifications for success/error messages

### 🏪 Restaurant
- Register and log in as a restaurant
- Create and manage own restaurant
- Add, update, or remove dishes
- View and respond to customer reviews
- Dashboard to track performance

## 🧑‍💻 Tech Stack

### Frontend
- React (with Context API for Auth and Cart)
- React Router
- React Toastify
- Axios

### Backend
- Spring Boot (REST APIs)
- Spring Security (for authentication/authorization)
- JPA/Hibernate (for persistence)
- MySQL/PostgreSQL (configurable)

---

---

## 🌐 Backend API Endpoints

### 🥘 Dish
- `GET /api/dish/getalldishes`
- `POST /api/dish/add`
- `DELETE /api/dish/delete/{id}`

### 👤 Customer
- `POST /api/customer/login`
- `POST /api/customer/register`
- `GET /api/customer/orders`
- `POST /api/customer/checkout`

### 🏪 Restaurant
- `POST /api/restaurant/login`
- `POST /api/restaurant/register`
- `GET /api/restaurant/dashboard`
- `POST /api/restaurant/add-dish`
- `GET /api/restaurant/my-dishes`
- `POST /api/restaurant/review`

_(More endpoints are modular and role-specific)_

---

## 👥 Team Responsibilities

| Developer   | Responsibilities                                      |
|-------------|--------------------------------------------------------|
| **Anmol**   | Cart & Checkout                                        |
| **Maithili**| Orders Page & Order History                           |
| **Ashutosh**| Restaurant Dashboard, Add Restaurant, Reviews          |
| **Tanmay**  | Login/Register, Home Page, AuthContext, CartContext    |
| **Tarun**   | Owned Restaurants Page, Add Dish Feature               |

---

## 🚧 Setup Instructions

### 🔧 Backend
1. Clone the backend repo.
2. Configure `application.properties` for DB credentials.
3. Run the Spring Boot app.

### 💻 Frontend
1. Clone the frontend repo.
2. Run `npm install`.
3. Start app with `npm start`.

---

## 🔒 Authentication

- JWT-based login system
- Role-based access (Customer, Restaurant)
- Secure APIs using Spring Security

---

## 📈 Future Enhancements

- Add Delivery Partner module
- Payment gateway integration
- Advanced filtering and search
- Real-time order tracking with WebSockets

---


## 🙌 Contributions

Feel free to open pull requests or issues if you’d like to contribute or report bugs.

---

