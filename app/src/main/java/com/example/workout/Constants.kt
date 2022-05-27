package com.example.workout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseArrayList = ArrayList<ExerciseModel>()
        val jumpingJack = ExerciseModel(1,"Jumping Jack",R.drawable.img_2,
            isCompleted = false,
            isSelected = false
        )
        exerciseArrayList.add(jumpingJack)
        val plank = ExerciseModel(2,"Plank ",R.drawable.img_3,
            isCompleted = false,
            isSelected = false
        )
        exerciseArrayList.add(plank)
        val pushup = ExerciseModel(3,"Pushups",R.drawable.img_4,false,false)
        exerciseArrayList.add(pushup)
        val sitUps = ExerciseModel(1,"Sit Ups",R.drawable.img_5, isCompleted = false, isSelected = false
        )
        exerciseArrayList.add(sitUps)
        val climbing = ExerciseModel(1,"Climbing",R.drawable.img_9,
            isCompleted = false,
            isSelected = false
        )
        exerciseArrayList.add(climbing)
        return exerciseArrayList
    }
}