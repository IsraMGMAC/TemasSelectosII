function Task({ task, onDelete, onToggle }) {
  return (
    <li>
      <span
        className={`task-text ${task.completed ? 'completed' : ''}`}
        onClick={() => onToggle(task.id)}
      >
        {task.text}
      </span>

      <button className="delete-btn" onClick={() => onDelete(task.id)}>
        ❌
      </button>
    </li>
  );
}

export default Task;
