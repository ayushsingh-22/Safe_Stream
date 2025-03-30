# Sales Studio TV Launcher

Sales Studio is a cutting-edge Android TV launcher developed using **Kotlin** and **Jetpack Compose**, providing a streamlined, secure, and user-friendly interface. Engineered for effortless navigation, it features robust overlay permission management and a PIN-protected exit mechanism, ensuring both accessibility and security.

## 📌 Key Features

- **Custom Android TV Launcher**: Designed specifically for television screens to enhance usability and performance.
- **PIN-Protected Exit Mechanism**: Implements a secure authentication system to prevent unauthorized app termination.
- **Automated Overlay Permission Handling**: Dynamically requests system-level permissions for seamless operation.
- **Material Design 3 for TV**: Utilizes `androidx.tv.material3` for a visually cohesive and user-friendly interface.

## 📂 Project Architecture

```
kotlin+java/
│── com.example.salesstudio
│   ├── ui.theme/             # Theming & Styling Components
│   │   ├── Color.kt         # Centralized color scheme management
│   │   ├── Permission.kt    # Manages overlay permission requests and validation
│   │   ├── Theme.kt         # Defines global app-wide styling
│   │   ├── Type.kt          # Manages typography configurations
│   ├── AppInfo.kt           # Stores application metadata and constants
│   ├── ExitDesign.kt        # Implements PIN-based exit dialog logic
│   ├── MainActivity.kt      # Core entry point handling app lifecycle and navigation
│   ├── design.kt            # UI component definitions
```

## 🚀 Deployment Guide

### System Requirements

- **Android Studio Flamingo** or later
- **Android TV Emulator** or a compatible **Android TV device**
- **Kotlin** and **Jetpack Compose** development environment

### Installation Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/sales-studio-tv.git
   ```
2. Open the project in **Android Studio**.
3. Deploy the application on an **Android TV device or emulator**.

## 🎯 Functional Overview

- Upon initialization, the application automatically requests **overlay permissions** if not previously granted.
- Users can **navigate seamlessly** through the launcher interface.
- Pressing **Back or Home** triggers an **ExitDialog**, requiring a valid **PIN authentication (Default: 1234)** to proceed with exit.

## 🛠️ Technology Stack

- **Kotlin** & **Jetpack Compose** for UI and logic implementation
- **Android TV Material 3** for an optimized television interface
- **Overlay Permissions API** for system-level overlay handling

## 👨‍💻 Developer

Engineered by Ayush Kumar.
