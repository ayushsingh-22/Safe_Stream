# Sales Studio TV Launcher

Sales Studio is a custom TV launcher designed for Android TV, built using **Kotlin** and **Jetpack Compose**. It provides a seamless user experience with app navigation, overlay permissions, and PIN-protected exit functionality.

## 📌 Features
- **Custom Android TV Launcher**: A dedicated interface optimized for TV screens.
- **PIN-Protected Exit Dialog**: Secure app exit using a PIN.
- **Overlay Permission Handling**: Automatically requests overlay permissions.
- **Material Design 3 for TV**: Uses `androidx.tv.material3` for UI components.

## Project Structure

```
kotlin+java/
│── com.example.salesstudio
│   ├── ui.theme/             # Theming & Styles
│   │   ├── Color.kt         # Defines app colors
│   │   ├── Permission.kt    # Handles overlay permissions
│   │   ├── Theme.kt         # Defines the app theme
│   │   ├── Type.kt          # Typography settings
│   ├── AppInfo.kt           # Stores application-related data
│   ├── ExitDesign.kt        # PIN-protected exit dialog
│   ├── MainActivity.kt      # Main entry point for the app
│   ├── design.kt            # UI layout components
```

## 🚀 Getting Started
### Prerequisites
- Android Studio Flamingo or later
- Android TV Emulator or Device
- Kotlin with Jetpack Compose

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/sales-studio-tv.git
   ```
2. Open the project in **Android Studio**.
3. Run the app on an **Android TV device or emulator**.

## 🎯 Usage
- Upon launch, the app requests **overlay permission** if not granted.
- Users can **navigate** through the launcher UI.
- Pressing **Back or Home** triggers the **ExitDialog**, requiring a PIN (**Default: 1234**) to exit.

## 🛠️ Tech Stack
- **Kotlin** & **Jetpack Compose**
- **Android TV Material 3**
- **Overlay Permissions API**


## 👨‍💻 Author
Developed by Ayush Kumar.

