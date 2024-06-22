package ru.narfu.cafeteria.ui.cafeteria

import ru.narfu.cafeteria.db.model.Building

interface CafeteriaCallback {
    fun onBuildingSelected(building: Building)
}