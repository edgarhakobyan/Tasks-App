package com.example.edgar.tasksapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by edgar on 1/28/18.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Task> tasks;
    private ArrayList<Task> filteredTasks;

    public TaskAdapter (Context context, ArrayList<Task> tasks){
        this.context = context;
        this.tasks = tasks;
        this.filteredTasks = tasks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new TaskViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.taskTitle.setText(tasks.get(position).getTitle());
        holder.taskStatus.setText(tasks.get(position).getStatus());
    }

    public static ArrayList<Task> filterByPriority(ArrayList<Task> allTasks, String priority) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task currentTask: allTasks) {
            if (priority.equals(currentTask.getStatus())) {
                filteredTasks.add(currentTask);
            }
        }
        return filteredTasks;
    }


    @Override
    public int getItemCount() {
        return filteredTasks.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    filteredTasks = tasks;
                } else {
                    ArrayList<Task> filteredList = new ArrayList<>();
                    for (Task task: tasks) {
                        if (task.getTitle().toLowerCase().contains(charString.toLowerCase()) || task.getDescription().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(task);
                        }
                    }
                    filteredTasks = filteredList;
                    System.out.println("======= filteredTasks====== = " + filteredList.size() + " : " + filteredTasks.toString());
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredTasks;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredTasks = (ArrayList<Task>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskTitle;
        TextView taskStatus;


        public TaskViewHolder(final View itemView) {
            super(itemView);

            taskTitle = (TextView) itemView.findViewById(R.id.titleView);
            taskStatus = (TextView) itemView.findViewById(R.id.status);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int pos = MainActivity.myView.indexOfChild(view);
                    Intent intent = new Intent(context, TaskActivity.class);
                    intent.putExtra("position", pos);
                    context.startActivity(intent);
                }
            });
        }
    }
}
