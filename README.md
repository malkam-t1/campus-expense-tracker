<div align="center">

# Campus Expense Tracker

### A simple Kotlin Android app for students to track expenses, manage budgets
![Android](https://img.shields.io/badge/Android-App-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-Kotlin%20DSL-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

</div>

---

## Overview

**Campus Expense Tracker** is a beginner-friendly Kotlin Android application built to help students record daily expenses, set a monthly budget, view spending summaries, and manage money more clearly.

The app is designed around real student needs such as food, travel, books, recharge, shopping, rent, hostel expenses, and other daily spending categories.

This project is also useful for learning Android development because it covers forms, local storage, multiple screens, data handling, filtering, calculations, and clean project organization.

---

## Repository

**GitHub:** [campus-expense-tracker](https://github.com/malkam-t1/campus-expense-tracker)

---

## Key Highlights

- Built with **Kotlin**
- Native Android app
- Simple and clean UI
- Local data storage
- Student-focused categories
- Monthly budget tracking
- Spending summary
- Expense filtering
- Shareable reports
- Beginner-friendly project structure

---

## Features

| Feature | Description |
|---|---|
| Dashboard | Shows total spending, monthly budget, and balance overview |
| Add Expense | Add title, amount, category, date, and note |
| Expense List | View all saved expenses in one place |
| Category Filter | Filter expenses based on spending category |
| Delete Expense | Remove unwanted expense records |
| Monthly Budget | Set and update monthly budget |
| Budget Balance | View remaining budget after expenses |
| Budget Warning | Helps identify when spending crosses the limit |
| Spending Summary | View category-wise and overall expense summary |
| Share Report | Share expense details or summary with others |
| Local Storage | Saves data on the device using local storage |

---

## App Screens

The app contains the following main screens:

| Screen | Purpose |
|---|---|
| `MainActivity` | Dashboard and quick overview |
| `AddExpenseActivity` | Add a new expense |
| `ExpenseListActivity` | View, filter, and delete expenses |
| `BudgetActivity` | Set monthly budget |
| `SummaryActivity` | View spending summary |

---

## Expense Categories

The app uses student-friendly spending categories:

| Category | Example |
|---|---|
| Food | Canteen, snacks, meals |
| Travel | Bus, train, auto, fuel |
| Books | Textbooks, notebooks, stationery |
| Recharge | Mobile recharge, internet packs |
| Shopping | Clothes, accessories, daily items |
| Rent / Hostel | Hostel fee, room rent |
| Other | Any extra expense |

---

## Tech Stack

| Technology | Usage |
|---|---|
| Kotlin | Main programming language |
| Android SDK | Android application development |
| Native Android Views | User interface |
| SharedPreferences | Local data storage |
| Intent | Sharing expense reports |
| Gradle Kotlin DSL | Build configuration |

---

## Project Structure

```text
campus-expense-tracker/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── kotlin/
│           │   └── com/
│           │       └── tarunmalkam/
│           │           └── campusexpensetracker/
│           │               ├── AddExpenseActivity.kt
│           │               ├── BudgetActivity.kt
│           │               ├── BudgetStore.kt
│           │               ├── Category.kt
│           │               ├── DateUtils.kt
│           │               ├── Expense.kt
│           │               ├── ExpenseListActivity.kt
│           │               ├── ExpenseStore.kt
│           │               ├── MainActivity.kt
│           │               ├── MoneyUtils.kt
│           │               ├── SummaryActivity.kt
│           │               └── Ui.kt
│           └── res/
│               └── values/
│                   ├── strings.xml
│                   └── styles.xml
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── LICENSE
├── README.md
└── settings.gradle.kts
```

---

## File Details

| File | Description |
|---|---|
| `MainActivity.kt` | Main dashboard screen of the app |
| `AddExpenseActivity.kt` | Handles adding new expenses |
| `ExpenseListActivity.kt` | Displays saved expenses and category filter |
| `BudgetActivity.kt` | Allows user to set monthly budget |
| `SummaryActivity.kt` | Shows expense summary and spending breakdown |
| `Expense.kt` | Data model for an expense item |
| `Category.kt` | Stores available expense categories |
| `ExpenseStore.kt` | Handles saving, loading, and deleting expenses |
| `BudgetStore.kt` | Handles monthly budget storage |
| `DateUtils.kt` | Date helper functions |
| `MoneyUtils.kt` | Money formatting helper functions |
| `Ui.kt` | Common UI helper functions |
| `AndroidManifest.xml` | Declares app activities |
| `strings.xml` | Stores app text values |
| `styles.xml` | Stores app theme styles |

---

## How the App Works

```text
Open App
   |
   |-- View Dashboard
   |
   |-- Add Expense
   |      |
   |      |-- Enter title
   |      |-- Enter amount
   |      |-- Select category
   |      |-- Add note
   |      |-- Save expense
   |
   |-- View Expense List
   |      |
   |      |-- Filter by category
   |      |-- Delete expense
   |
   |-- Set Monthly Budget
   |      |
   |      |-- Save budget
   |      |-- Track remaining amount
   |
   |-- View Summary
          |
          |-- Total spending
          |-- Category-wise spending
          |-- Share report
```

---

## Installation

### Requirements

Before running the project, make sure you have:

- Android Studio
- Android SDK
- Kotlin support
- Android emulator or physical Android device

---

### Clone the Repository

```bash
git clone https://github.com/malkam-t1/campus-expense-tracker.git
```

---

### Open in Android Studio

1. Open **Android Studio**
2. Click **Open**
3. Select the `campus-expense-tracker` folder
4. Wait for Gradle sync to finish
5. Run the app on an emulator or Android device

---

## How to Use

1. Open the app.
2. Check the dashboard for budget and spending overview.
3. Tap **Add Expense**.
4. Enter expense title, amount, category, date, and note.
5. Save the expense.
6. Open the expense list to view all records.
7. Use category filter to find specific expenses.
8. Set a monthly budget.
9. View remaining budget and total spending.
10. Open summary to understand category-wise spending.
11. Share expense report if needed.

---

## Example Use Case

A student can use this app to track monthly spending like this:

| Expense | Category | Amount |
|---|---|---|
| Lunch | Food | ₹80 |
| Bus Ticket | Travel | ₹30 |
| Notebook | Books | ₹60 |
| Mobile Recharge | Recharge | ₹199 |
| Hostel Fee | Rent / Hostel | ₹3000 |

The app calculates total spending and compares it with the monthly budget.

---

## Data Storage

This app stores data locally on the device.

| Data | Storage |
|---|---|
| Expense records | Local storage |
| Budget amount | Local storage |
| Category filters | App data |
| Summary calculation | Generated from saved expenses |

No online database is required for the current version.

---

## Clean Repository Guide

The repository includes important source files only.

### Included

```text
app/
.gitignore
build.gradle.kts
gradle.properties
settings.gradle.kts
LICENSE
README.md
```

### Ignored

```text
.gradle/
build/
app/build/
.idea/
local.properties
*.iml
*.zip
```

These ignored files are generated by Android Studio or Gradle and are not required in GitHub.

---

## Learning Outcomes

This project helps beginners learn:

| Concept | Practice Area |
|---|---|
| Kotlin basics | Data classes, functions, lists |
| Android Activities | Multiple screen navigation |
| UI development | Native Android views |
| Local storage | Saving and reading app data |
| Form handling | Expense input screen |
| Filtering | Category-based expense filtering |
| Calculations | Budget and summary logic |
| Intents | Sharing reports |
| Project structure | Clean Android folder organization |
| GitHub documentation | Professional README writing |

---

## Future Improvements

Planned improvements for future versions:

- Edit expense feature
- Date range filter
- Weekly and yearly summary
- Dark mode
- Charts for spending summary
- CSV export
- PDF report export
- Income tracking
- Savings goal tracker
- Password or PIN lock
- Room database integration
- Backup and restore
- Material Design UI upgrade

---

## Suggested GitHub Topics

Add these topics to the repository:

```text
kotlin
android
expense-tracker
budget-tracker
student-app
android-app
sharedpreferences
gradle-kotlin-dsl
beginner-friendly
portfolio-project
```

---

## Project Status

| Item | Status |
|---|---|
| Basic Android app | Completed |
| Add expense | Completed |
| Expense list | Completed |
| Category filter | Completed |
| Budget tracker | Completed |
| Summary screen | Completed |
| Share report | Completed |
| Dark mode | Planned |
| Charts | Planned |
| Room database | Planned |

---

## License

This project is licensed under the **MIT License**.

See the [LICENSE](LICENSE) file for details.

---

## Developer

**Tarun Malkam**

- GitHub: [malkam-t1](https://github.com/malkam-t1)
- Project: [Campus Expense Tracker](https://github.com/malkam-t1/campus-expense-tracker)
- Focus: Kotlin Android Development

---

## Support

If you like this project, consider giving it a star on GitHub.

<div align="center">

### Built with Kotlin for students who want better control over daily expenses.

</div>
