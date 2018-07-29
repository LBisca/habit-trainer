package lucas.com.habittrainer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.single_card.view.*

class HabitsAdapter(var habits: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>(){

    class HabitViewHolder(val card: View) : RecyclerView.ViewHolder(card)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitViewHolder(view)
    }

    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: HabitViewHolder, p1: Int) {
        val habit = habits[p1]
        holder.card.tv_Title.text = habit.title
        holder.card.tv_desc.text = habit.desc
        holder.card.iv_icon.setImageResource(habit.image)
    }
}