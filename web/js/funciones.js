/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






function Ej2(){
    
    
    let tabla = document.getElementById("tablaProductos");
    let tr = document.getElementsByTagName("tr")[0];
    
    
   
    alert("entro ");
    
    
};

function annadirLista(x){
    table = document.getElementById("tablaProductos");
    rows = table.rows;
    
    a = rows[x.rowIndex].getElementsByTagName("TD")[1];
    
    
    
    
    //location.href = "/TrabajoFinal/ItemsControler/annadirCesta?var=" + a.innerHTML;
    location.href = "/TrabajoFinal/ItemsControler/elegirItem?var=" + a.innerHTML;
}








function sortTable(i){
    
    var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("tablaProductos");
  switching = true;
  
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[0];
      y = rows[i + 1].getElementsByTagName("TD")[0];
      // Check if the two rows should switch place:
      if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
        // If so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
    
    
    
};

function validarRegistro(){
    
    let nombre = document.forms["registro"]["nombre"].value;
    //let nombre = document.getElementsByName("nombre").value;
    let contrasenna = document.forms["registro"]["contrasenna"].value;
    
    
    
    if (nombre == "" || nombre.toString().length < 4 || nombre.toString().length > 20){
        alert("Los nombres de usuario no pueden tener menos de 4 caracteres o mas de 20")
        return false
    }
    
    if (contrasenna == "" ||contrasenna.toString().length < 4 || contrasenna.toString().length > 20){
        alert("Las contraseñas no pueden tener menos de 4 caracteres o mas de 20")
        return false}
    
    
    
}








var xhr;

function init_ajax(){
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }   
    
}

function peticionSincrona(){
    
    init_ajax();
    
    var url = "prueba";
    xhr.open("POST",url,true);
    
    xhr.onreadystatechange = recibirMensaje();
    
    
    var correoE = document.getElementById("correoE");
    
    
    var datos = "correoE=" + correoE.value;
    
     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   xhr.send(datos);
    
};

function recibirMensaje(){
    
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            document.getElementById("mensaje").innerHTML = xhr.responseText; //aqui tengo que introducir el mensaje
        }
    }
    
};
