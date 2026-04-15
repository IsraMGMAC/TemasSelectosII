// asegurar de que el DOM esté completamente cargado antes de ejecutar el código
document.addEventListener("DOMContentLoaded", function() {
    
    // 1. Obtener los botones por su ID
    const btnSaludo = document.getElementById("btn-saludo");
    const btnColor = document.getElementById("btn-color");

    // 2. Función para mostrar el saludo
    btnSaludo.addEventListener("click", function() {
        alert("¡Hola! Bienvenido al sistema de gestión de Alquiler de Eventos. ¡Explora nuestro catálogo!");
    });

    // 3. Función para cambiar el color de fondo al azar
    btnColor.addEventListener("click", function() {
        // Generar colores RGB al azar entre 150 y 255 (para mantener tonos claros/pastel y que el texto oscuro se lea bien)
        const r = Math.floor(Math.random() * 105) + 150;
        const g = Math.floor(Math.random() * 105) + 150;
        const b = Math.floor(Math.random() * 105) + 150;
        
        // Formar el string rgb(r, g, b)
        const colorAleatorio = `rgb(${r}, ${g}, ${b})`;
        
        // Aplicar el color al body
        document.body.style.backgroundColor = colorAleatorio;
        
        // Opcional: Mostrar en consola el color generado
        console.log("Color de fondo cambiado a: " + colorAleatorio);
    });

});