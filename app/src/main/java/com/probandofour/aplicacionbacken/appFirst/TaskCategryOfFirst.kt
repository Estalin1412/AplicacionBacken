package com.probandofour.aplicacionbacken.appFirst



sealed class TaskCategryOfFirst(var isSelected: Boolean = true) {
    object objCategory : TaskCategryOfFirst()
    object objPersonal : TaskCategryOfFirst()
    object objOthers : TaskCategryOfFirst()

}