# Meal App

## Project Overview

Meal App is a comprehensive Android application designed to provide users with an engaging culinary experience. The app allows users to browse, explore, and interact with a wide variety of meal recipes while offering personalized features.

## 1. Project Purpose and Features

### Key Functionalities
- **Authentication**
  - registration
  - login
  - Logout

- **Meal Browsing**
  - Browse meal collections
  - Filter meals by categories
  - Explore diverse recipes

- **Meal Exploration**
  - Detailed meal information
  - Ingredient lists
  - Step-by-step cooking instructions

- **Personalization**
  - Save favorite meals
  - Offline access to saved recipes

- **User Interface**
  - Multi-language support
  - Day/Night theme toggle

## 2. User Interaction Flow

### Authentication Journey
1. New users register with email/password
2. Existing users log in to their accounts
3. meal browsing experience
4. Option to log out when desired

### Meal Discovery
1. Browse meal categories
2. Tap on meals for detailed recipes
3. View ingredients and cooking instructions
4. Save favorite meals for quick future access

### Profile Management
1. View personal information
2. Modify language preferences
3. Switch between light and dark themes

## 3. Technical Attributes and Architecture

### Technologies and Frameworks
- **Programming Language**: Kotlin
- **Architecture**: MVVM with Clean Architecture Principles

### Architectural Layers
1. **Domain Layer**
   - Business logic
   - Use cases
   - Repository interfaces

2. **Data Layer**
   - Repository implementations
   - Local database management
   - Remote data fetching

3. **Presentation Layer**
   - UI components
   - ViewModels
   - State management

### Key Components
- **Jetpack Libraries**
  - Room: Local database storage
  - ViewModel: UI state management
  - Flow: Reactive programming

- **Dependency Injection**: Dagger Hilt
- **Authentication**: Firebase Authentication
- **Asynchronous Programming**: Kotlin Coroutines
- **Meal API**: https://www.themealdb.com/
