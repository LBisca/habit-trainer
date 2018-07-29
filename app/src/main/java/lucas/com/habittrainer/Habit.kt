package lucas.com.habittrainer

data class Habit(val title: String, val desc: String, val image: Int)

fun getSampleHabits(): List<Habit> {
    return listOf(
            Habit("Go for a Walk",
                    "You will go for a nice walk",
                    R.drawable.walk),

            Habit("Drink Water",
                    "You will have a nice glass of water",
                    R.drawable.water),

            Habit("Sleep",
                    "You will sleep for some time",
                    R.drawable.sleep
            )
    )
}
