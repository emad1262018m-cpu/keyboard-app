# Senior-Friendly Keyboard App

A customizable Android Input Method Editor (IME) designed specifically for seniors, featuring large keys, adjustable font sizes, and high-contrast color options.

## Project Overview

This keyboard application provides a simplified, accessible typing experience with:
- Large, easy-to-read keys (default 32sp font size)
- 5×3 QWERTY layout optimized for visibility
- Customizable colors (text, background, borders)
- Bold text option for enhanced readability
- Support for both English and Arabic layouts (RTL-aware)
- Minimum 48dp touch targets for ease of use

## Architecture

The project follows a multi-module architecture for better separation of concerns and team development:

```
keyboard-app/
├── app/                    # Main application and IME service
├── core/
│   ├── keyboard/          # Keyboard UI and layout logic (Compose)
│   ├── input/             # Text input handling and composition
│   └── ui/                # Shared UI components
├── data/
│   └── settings/          # Settings data models and persistence
└── feature/
    └── settings_ui/       # Settings screen UI (scaffold)
```

### Module Descriptions

#### `app/`
Main application module containing:
- `MyKeyboardService` - InputMethodService implementation
- `KeyboardApplication` - Hilt application entry point
- `MainActivity` - Setup screen for enabling the keyboard
- AndroidManifest configuration for IME service

#### `core:keyboard`
Keyboard UI layer built with Jetpack Compose:
- `KeyboardView` - Main keyboard composable rendering the full layout
- `KeyCell` - Individual key composable with styling and interaction
- `LayoutManager` - Defines QWERTY and Arabic layouts
- `KeyboardLayout` & `KeyData` - Data models for keyboard structure

#### `core:input`
Text input handling:
- `TextComposer` - Manages InputConnection for text operations
- RTL support flags for future Arabic implementation

#### `core:ui`
Reusable UI components:
- `FontSizeControl` - Font size slider (scaffold)
- `ColorPicker` - Color selection component (scaffold)
- `BoldToggle` - Bold text toggle switch (scaffold)

#### `data:settings`
Settings data layer:
- `KeyboardSettings` - Data class for keyboard preferences
- `SettingsRepository` - Interface for settings persistence
- DataStore and Room setup (for future implementation)

#### `feature:settings_ui`
Settings UI (scaffold for Task 4):
- `SettingsScreen` - Composable settings interface

## Technical Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose with Material 3
- **Dependency Injection:** Hilt
- **Persistence:** DataStore (Preferences) & Room (planned)
- **Async:** Kotlin Coroutines
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 34 (Android 14)

## Current Features (Phase 1)

✅ Functional IME service that can be enabled in system settings  
✅ 5×3 QWERTY keyboard layout with 47 keys  
✅ Compose-based UI with Material Design  
✅ Hardcoded styling defaults:
  - Font size: 32sp
  - Text color: Black (#000000)
  - Background: Light gray (#F0F0F0)
  - Border: Dark gray (#CCCCCC)
  - Bold: Disabled  
✅ Key press logging (text insertion in Task 3)  
✅ Shift key state tracking  
✅ Backspace and Space keys  
✅ Multi-module Gradle setup with proper dependencies  

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK with API 34
- Gradle 8.2+

### Building the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd keyboard-app
   ```

2. Open the project in Android Studio

3. Sync Gradle files (File → Sync Project with Gradle Files)

4. Build the project:
   ```bash
   ./gradlew build
   ```

### Running the App

1. Connect an Android device (API 26+) or start an emulator

2. Run the app:
   ```bash
   ./gradlew installDebug
   ```

3. Enable the keyboard:
   - Open the app
   - Tap "Enable Keyboard"
   - In Settings → System → Languages & input → On-screen keyboard
   - Enable "Senior Keyboard"

4. Select the keyboard:
   - In any text field, long-press and select "Input method"
   - Choose "Senior Keyboard"

### Testing Key Presses

Open any text input field (Notes, Messages, etc.) and test the keyboard. Key presses will be logged to Logcat with tag `MyKeyboardService`.

To view logs:
```bash
adb logcat -s MyKeyboardService
```

## Development Guidelines

### Adding a New Key

1. Edit `LayoutManager.kt` and add the key to the appropriate row:
   ```kotlin
   KeyData("!", '!'.code)
   ```

2. Keys with special functions should use negative keyCode values:
   ```kotlin
   KeyData("⌫", KEYCODE_BACKSPACE, width = 1.5f)
   ```

### Customizing Key Appearance

Modify parameters in `KeyboardView.kt`:
```kotlin
KeyboardView(
    fontSize = 36,  // Increase font size
    textColor = Color.White,
    backgroundColor = Color.DarkGray,
    borderColor = Color.Gray,
    isBold = true
)
```

### Adding a New Layout

1. Create a new function in `LayoutManager.kt`:
   ```kotlin
   fun getSpanishLayout(): KeyboardLayout {
       return KeyboardLayout(rows = listOf(/* define rows */))
   }
   ```

2. Update `KeyboardView` to accept layout parameter

## Known Limitations (Phase 1)

- Settings are hardcoded (no UI for customization yet)
- Text is not actually inserted (logged only - Task 3)
- No haptic feedback
- No sound feedback
- No auto-capitalization
- No predictive text
- Shift key doesn't auto-reset after one character

## Roadmap

**Task 2:** Shift/Caps Lock visual states and auto-reset  
**Task 3:** Actual text insertion via InputConnection  
**Task 4:** Settings UI with live customization  
**Task 5:** Settings persistence with DataStore  
**Task 6:** Drag-and-drop key reordering  
**Task 7:** Haptic and sound feedback  
**Task 8:** Arabic layout with full RTL support  

## Contributing

When contributing to this codebase:
1. Follow existing Kotlin code conventions
2. Use Compose for all UI (no XML layouts)
3. Keep modules independent and focused
4. Add unit tests for business logic
5. Test on multiple screen sizes and Android versions

## License

[To be determined]

## Support

For issues or questions, please open a GitHub issue or contact the development team.
