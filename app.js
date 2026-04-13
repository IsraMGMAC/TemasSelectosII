document.addEventListener('DOMContentLoaded', () => {
    const selectCountry = document.getElementById('country');
    const form = document.getElementById('registroForm');
    const avgInput = document.getElementById('avg');

    // 1. Cargar catálogo de países
    fetch('https://restcountries.com/v3.1/all?fields=name,flags')
        .then(res => res.json())
        .then(data => {
            selectCountry.innerHTML = '<option value="">-- Seleccione un país --</option>';
            // Ordenar alfabéticamente
            data.sort((a, b) => a.name.common.localeCompare(b.name.common));
            
            data.forEach(c => {
                const opt = document.createElement('option');
                opt.value = c.name.common;
                opt.textContent = c.name.common;
                selectCountry.appendChild(opt);
            });
        })
        .catch(err => console.error("Error al obtener países:", err));

    // 2. Enviar Formulario (POST)
    if (avgInput) {
        // Evitar ingresar signo negativo, plus o notación exponencial
        avgInput.addEventListener('keydown', (ev) => {
            if (ev.key === '-' || ev.key === '+' || ev.key === 'e' || ev.key === 'E') ev.preventDefault();
        });

        // Normalizar y limitar el valor en tiempo real
        avgInput.addEventListener('input', () => {
            // Cambiar comas por punto
            if (avgInput.value.includes(',')) {
                avgInput.value = avgInput.value.replace(/,/g, '.');
            }
            // Quitar signos negativos o '+' si los hubiera
            if (avgInput.value.startsWith('-')) {
                avgInput.value = avgInput.value.replace(/-/g, '');
            }
            if (avgInput.value.includes('+')) {
                avgInput.value = avgInput.value.split('+').join('');
            }
            const parsed = parseFloat(avgInput.value);
            if (!isNaN(parsed)) {
                if (parsed < 0) avgInput.value = '0';
                else if (parsed > 10) avgInput.value = '10';
            }
        });
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();



        // crear el objeto FormData a partir del formulario HTML
        const dataToSend = new FormData(form);

        try {
            const response = await fetch('https://masksoft.com.mx/register', {
                method: 'POST',
                body: dataToSend // enviar el FormData directamente
            });

            const result = await response.json();

            if (result.status === "success") {
                alert(`Éxito: ${result.message}`);
                form.reset();
            } else {
                alert("Hubo un problema al registrar.");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Error de conexión con el servidor.");
        }
    });
});