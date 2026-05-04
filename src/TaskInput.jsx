import { useState } from "react";

function TaskInput({ onAdd }) {
  const [input, setInput] = useState("");

  const handleAdd = () => {
    if (input.trim() === "") return;

    onAdd(input);
    setInput("");
  };

  return (
    <div className="input-container">
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Escribe una tarea"
        onKeyDown={(e) => {
          if (e.key === "Enter") handleAdd();
        }}
      />

      <button onClick={handleAdd}>Agregar</button>
    </div>
  );
}


export default TaskInput;
