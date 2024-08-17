package com.example.todoappmvvmjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoappmvvmjava.databinding.TodoItemBinding;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.ViewHolder> {



    public NoteAdapter(){
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getNoteItem(position);

        holder.binding.txtTitle.setText(note.getTitle());
        holder.binding.txtDescription.setText(note.getDescription());
    }

    public Note getNoteItem(int position){
        return  getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TodoItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = TodoItemBinding.bind(itemView);
        }
    }

}
