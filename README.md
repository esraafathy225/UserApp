# Plants Paging App

## Overview

This project demonstrates a modern Android application using **Clean Architecture**, **Room**, **Navigation Component**, and the **Hilt** . The app fetches user data from the first screen and caches it locally in a Room database, ensuring consistent data management.

## Features

- **Room Database**: Local caching of user data.
- **Hilt**: Dependency Injection.
- **Clean Architecture**: Separation of concerns with multiple layers (Data, Domain, and Presentation).

## Architecture Overview

### Layers:

1. **Data Layer**: 
   - Utilizes Room for persistence requests.

2. **Domain Layer**:
   - Contains business logic, models, and repository interfaces.

3. **Presentation Layer**:
   - Handles UI (e.g., activities, fragments) and the view-model layer, responsible for managing UI state and interacting with the domain layer.

## Technologies & Libraries

- **Kotlin**: Main language used.
- **Room**: For local storage and caching.
- **Coroutines & Flow**: For asynchronous data processing and stream handling.
- **Hilt** : Dependency Injection.
- **Navigation Component** : Navigating between fragments.

## Setup Instructions

**App Video Demo**:
https://drive.google.com/file/d/1Jhz2WjVb9DYtd4hBiER-tb2RSp4ys4dL/view?usp=sharing

**Clone the Repository**:
   ```bash
   git clone [https://github.com/esraafathy225/PlantsApp.git](https://github.com/esraafathy225/UserApp)
