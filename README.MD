# Android CRUD Application

## Overview
This repository contains an Android CRUD (Create, Read, Update, Delete) application designed to manage user data. The application allows users to perform various operations such as adding, viewing, updating, and deleting user information stored in an SQLite database.

## Purpose
The purpose of this project is to showcase proficiency in Android app development by creating a functional CRUD application. The main objectives include:

- Understanding the fundamentals of Android development.
- Implementing database operations using SQLiteOpenHelper.
- Designing user interfaces with XML layouts.
- Integrating user input with database operations.
- Developing a scrollable layout to handle large amounts of data efficiently.

## Features
- **Create**: Add new users with their name, age, height, weight, and marital status.
- **Read**: View the list of all users along with their details.
- **Update**: Modify existing user information.
- **Delete**: Remove users from the database.

## Technologies Used
- Android SDK
- Java
- XML
- SQLite


## Setup Instructions
To set up the project locally, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/Hetawk/android-crud-application.git
    ```
   2. Open the project in Android Studio.
   3. Build and run the application on an Android device or emulator.

## Usage
- Launch the application to access the CRUD interface.
- Utilize the provided input fields and buttons to perform CRUD operations on user data.
- View the list of users and their details.
- Update or delete existing users as needed.

## NOTE
This project served as an excellent learning opportunity to enhance skills and understanding of Android development. By successfully implementing CRUD functionalities and designing a user-friendly interface, valuable experience in database-driven application development was gained.

# Sample Output

<div style="display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 10px; justify-content: center;">

<!-- First Row -->
<div style="margin: 5px;">
    <img src="results/1-create_database.png" alt="Create Database" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/2-add_user_to_database.png" alt="Add User to Database" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/2.1-user_save_successfully.png" alt="User Saved Successfully" style="max-width: 100%; height: auto;">
</div>

<!-- Second Row -->
<div style="margin: 5px;">
    <img src="results/3.1-adding_a_user_with_married_field_check.png" alt="Adding a User with Married Field Checked" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/3.2-user_save_successfully.png" alt="User Saved Successfully" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/4.1-query_user_show_a_toast_of_all_users_and_their_id.png" alt="Query User" style="max-width: 100%; height: auto;">
</div>

<!-- Third Row -->
<div style="margin: 5px;">
    <img src="results/5.1-show_table_data_shows_the_list_of_all_users_and_their_info.png" alt="Show Table Data" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/6.1-delete_user_by_id.png" alt="Delete User by ID" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/6.2-user_deleted_successfully.png" alt="User Deleted Successfully" style="max-width: 100%; height: auto;">
</div>

<!-- Fourth Row -->
<div style="margin: 5px;">
    <img src="results/6.3-show_table_data_and_user_1_is_no_longer_available.png" alt="Show Table Data and User 1 is No Longer Available" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/6.4-query_user_and_there_is_no_user_with_the_id_1.png" alt="Query User and There is No User with the ID 1" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/7.1-writing_update_for_user2.png" alt="Writing Update for User 2" style="max-width: 100%; height: auto;">
</div>

<!-- Fifth Row -->
<div style="margin: 5px;">
    <img src="results/7.2-enter_user_id_to_update_user.png" alt="Enter User ID to Update User" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/7.3-User_updated_successfully.png" alt="User Updated Successfully" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/7.4-show_table_data_with_updated_user2.png" alt="Show Table Data with Updated User 2" style="max-width: 100%; height: auto;">
</div>

<!-- Sixth Row -->
<div style="margin: 5px;">
    <img src="results/8.1-delete_database_successfully.png" alt="Delete Database Successfully" style="max-width: 100%; height: auto;">
</div>

<div style="margin: 5px;">
    <img src="results/8.2-query_user_and_no_users_available_since_database_was_deleted.png" alt="Query User and No Users Available Since Database Was Deleted" style="max-width: 100%; height: auto;">
</div>

</div>
