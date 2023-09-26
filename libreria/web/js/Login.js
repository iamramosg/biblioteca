async function encriptar(texto) {
    const encoder = new TextEncoder(); //Invocamos la clase q convierte un String en bytes
    const data = encoder.encode(texto);//Hace la conversión
    const hash = await crypto.subtle.digest('SHA-256', data); //crypto toma los bytes y los encripta, devuelve un buffer
    const hashArray = Array.from(new Uint8Array(hash)); // convierte el buffer en un arreglo de bytes
    const hashHex = hashArray.map((b) => b.toString(16).padStart(2, '0')).join(''); // convierte los bytes en string
    return hashHex;
}

function login() {
    let user = document.getElementById("txtCorreo").value;
    let contrasenia = document.getElementById("txtPassword").value;


    encriptar(contrasenia).then((textoEncriptado) => {
    alert(textoEncriptado.toString());
    let usuario = {"datosUsuario": JSON.stringify({"usuario": user, "contrasenia": textoEncriptado})};

    const url = new URLSearchParams(usuario);
    fetch('http://localhost:8081/libreria/api/restlibreria/login',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: url
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else if (data.idEmpleado) {
                    //localStorage.setItem('currentUser', JSON.stringify(data));
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Acceso Concedido' + data.persona.nombre,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    setTimeout(() => {
                        console.log("2 segundos esperado");
                    }, 2000);

                    //window.location.href = "http://localhost:8081/libreria/principal.html";

                } else {
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: 'uyyyy...',
                        text: 'Ha ocurrido un error'
                    });
                }
                JSON.stringify(data);
                //limpiarForm();
            });
    });

}