# Learniverse Connect

**Learniverse Connect** is the full stack web application developed as part of the IDATA2301 Web Technologies
and IDATA2306 Application development courses at NTNU. It simulates an online course marketplace, enabling users to 
explore, compare, and engage with a variety of online courses provided by third-party platforms.


>**Disclaimer**: This website is a result of a university project for the courses IDATA2301 Web Technologies and 
IDATA2306 Application development at NTNU. All information presented is fictional. Any resemblance to real companies, 
products, or services is purely coincidental. All content is intended for non-commercial educational use.

---

##  Features

-  **Search Functionality**: Powerful keyword-based search across all available courses.
-  **Course Details**: Detailed descriptions, pricing, certifications, and providers for each course.
-  **Favorite Courses**: Users can bookmark and manage favorite courses.
-  **Shopping Cart**: Add courses to a cart and proceed to mock checkout.
-  **User Authentication**: Register, login, and session-based user roles using Spring Security.
-  **Category Pages**: Organized views for IT, Business, Marketing, and Data Science.
-  **Admin Dashboard**: (optional) Manage content and view mock metrics.
-  **Mock Payment Page**: Fake checkout for user flow simulation.

---

##  Tech Stack

**Frontend:**
- HTML, CSS, JavaScript
- Thymeleaf Templates

**Backend:**
- Java 17
- Spring Boot (MVC, Security, Data JPA)
- SQL (Dbeaver)

---

##  Folder Structure

```
src
├── main
│   ├── java/no/ntnu/webapp
│   │   ├── controllers        # MVC controllers (Page, API, Auth, etc.)
│   │   ├── models             # Entity models (Course, User, etc.)
│   │   ├── repositories       # Spring Data JPA interfaces
│   │   └── services           # Business logic and service layer
│   └── resources
│       ├── static/css         # All CSS stylesheets
│       └── templates          # HTML templates (Thymeleaf)
│           └── components     # Navbar, Footer, CourseCard fragments
└── test
    └── java/...               # JUnit test cases
```
---

##  Getting Started

### Prerequisites
- Java 17+
- Maven 3+

### Run the App

```bash
# Clone the project
git clone https://github.com/bidha001/Web-App.Project.git
cd learniverse-connect

# Build and run the app
./mvnw spring-boot:run
```

Then open `http://localhost:8080` in your browser.

---

##  User Flow

- Sign Up / Login
- Explore courses
- Search by keyword
- View course detail
- Add to favorites or cart
- Proceed to mock checkout
- View your enrolled courses

---

##  User Roles

| Role     | Permissions                          |
|----------|--------------------------------------|
| Guest    | Browse and search courses            |
| User     | Bookmark, add to cart, and checkout  |
| Admin    | (Optional) Access dashboard          |

---

## Screenshots
## can anyone add screenshots here?

---


##  License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

##  Authors

This project was developed by NTNU students from Group 1 in the **IDATA2301 Web Technologies** and **IDATA2306 Application 
development** courses.
